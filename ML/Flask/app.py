import cv2
import os
import numpy as np
import tensorflow as tf
from PIL import Image
from flask import Flask, request, redirect, render_template, flash, url_for, jsonify
from werkzeug.utils import secure_filename
from pathlib import Path
# Using library from tensorflow object detection api https://github.com/tensorflow/models/tree/master/research/object_detection
from object_detection.utils import label_map_util
from object_detection.utils import visualization_utils as viz_utils
from object_detection.builders import model_builder
from object_detection.utils import config_util

ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg'}
app = Flask(__name__)
UPLOAD_FOLDER = 'static/uploads/'
RESULT_FOLDER = 'static/result/'
MODELS_FOLDER = 'models/'
LABEL_FOLDER = os.path.join('labels', 'label_map.pbtxt')
MODEL_PATH = 'models/model_1/saved_model'
app.config['MAX_CONTENT_LENGTH'] = 5 * 1024 * 1024
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['RESULT_FOLDER'] = RESULT_FOLDER
app.config['MODELS_FOLDER'] = MODELS_FOLDER
app.config['LABEL_FOLDER'] = LABEL_FOLDER
app.config['MODEL_PATH'] = MODEL_PATH



PIPELINE_CONFIG_PATH = os.path.join(app.config['MODELS_FOLDER'], 'model_1', 'pipeline.config')
CHECKPOINT_PATH = os.path.join(app.config['MODELS_FOLDER'], 'model_1', 'checkpoint')
configs = config_util.get_configs_from_pipeline_file(PIPELINE_CONFIG_PATH)
detection_model = model_builder.build(model_config=configs['model'], is_training=False)

# Restore checkpoint
# This code from tensorflow object detection api https://github.com/tensorflow/models/tree/master/research/object_detection
ckpt = tf.compat.v2.train.Checkpoint(model=detection_model)
ckpt.restore(os.path.join(CHECKPOINT_PATH, 'ckpt-0')).expect_partial()

@tf.function
def detect_fn(image):
    image, shapes = detection_model.preprocess(image)
    prediction_dict = detection_model.predict(image, shapes)
    detections = detection_model.postprocess(prediction_dict, shapes)
    return detections
# Restore checkpoint end

@app.after_request
def add_header(r):
    #Add headers to both force latest IE rendering engine or Chrome Frame,
    #and also to cache the rendered page for 10 minutes.
    r.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    r.headers["Pragma"] = "no-cache"
    r.headers["Expires"] = "0"
    r.headers['Cache-Control'] = 'public, max-age=0'
    return r


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/detect', methods=['POST'])
def detect():
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            flash('No file part')
            return redirect(request.url)
        file = request.files['file']
        # if user does not select file, browser also
        # submit an empty part without filename
        if file.filename == '':
            flash('No selected file')
            return redirect(request.url)
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            data = os.path.join(app.config['UPLOAD_FOLDER'], filename)
            file.save(data)

            return redirect(url_for('detect_image', filename=filename))
    return render_template('index.html')


category_index = label_map_util.create_category_index_from_labelmap(app.config['LABEL_FOLDER'])
@app.route('/detect/<filename>')
def detect_image(filename):
    IMAGE_PATH = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    MAX_BOXES_TO_DRAW = 3
    MIN_SCORE_THRESH = .5
    img = cv2.imread(IMAGE_PATH)
    image_np = np.array(img)

    input_tensor = tf.convert_to_tensor(np.expand_dims(image_np, 0), dtype=tf.float32)
    detections = detect_fn(input_tensor)

    num_detections = int(detections.pop('num_detections'))
    detections = {key: value[0, :num_detections].numpy()
                  for key, value in detections.items()}
    detections['num_detections'] = num_detections

    # detection_classes should be ints.
    label_id_offset = 1
    detections['detection_classes'] = detections['detection_classes'].astype(np.int64)
    detection_classes = detections['detection_classes']+label_id_offset
    detection_scores = detections['detection_scores']
    
    image_np_with_detections = image_np.copy()

    viz_utils.visualize_boxes_and_labels_on_image_array(
                image_np_with_detections,
                detections['detection_boxes'],
                detection_classes,
                detection_scores,
                category_index,
                use_normalized_coordinates=True,
                max_boxes_to_draw= MAX_BOXES_TO_DRAW,
                min_score_thresh= MIN_SCORE_THRESH,
                agnostic_mode=False,
                line_thickness=2)

    im = Image.fromarray(cv2.cvtColor(image_np_with_detections, cv2.COLOR_BGR2RGB))
    im_path = os.path.join(app.config['RESULT_FOLDER'], filename)
    im.save(im_path)
    if detection_scores[:1] < MIN_SCORE_THRESH:
        data =  {"error": True,
                "image_url": request.url_root + im_path,
                "message": "Detection failed. Detection score less than minimum score threshold"}
    else:
        data =  {"error": False,
                "message": "Success",
                "image_url": request.url_root + im_path,
                "detection_classes": detection_classes[:MAX_BOXES_TO_DRAW].tolist(),
                "detection_scores": detection_scores[:MAX_BOXES_TO_DRAW].tolist(),
                # "list_categories": [{category_index[detection_classes]["name"]: str(int(detection_scores*100)) + "%"} for x in zip(detection_classes[:MAX_BOXES_TO_DRAW], detection_scores[:MAX_BOXES_TO_DRAW])]}
                "list_categories": [category_index[x]["name"] for x in detection_classes[:MAX_BOXES_TO_DRAW]]}

    return jsonify(data)

if __name__ == "__main__": 
    app.run(debug=True)