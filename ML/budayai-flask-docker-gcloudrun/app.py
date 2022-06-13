import os
import tensorflow_hub as hub
from PIL import Image
from flask import Flask, request, redirect, render_template, flash, url_for, jsonify, json
from werkzeug.utils import secure_filename
from werkzeug.exceptions import HTTPException

from my_utilities import *

ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg'}
app = Flask(__name__)
UPLOAD_FOLDER = 'static/uploads/'
RESULT_FOLDER = 'static/result/'
MODELS_FOLDER = 'models/'
LABEL_FOLDER = os.path.join('labels', 'label_map.pbtxt')
app.config['MAX_CONTENT_LENGTH'] = 5 * 1024 * 1024
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['RESULT_FOLDER'] = RESULT_FOLDER
app.config['MODELS_FOLDER'] = MODELS_FOLDER
app.config['LABEL_FOLDER'] = LABEL_FOLDER

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

def list_models(MODELS_FOLDER):
    models_folder_list = os.listdir(MODELS_FOLDER)
    return models_folder_list


@app.route('/')
def index():
    model_list = list_models(app.config['MODELS_FOLDER']) 
    return render_template('index.html', len = len(model_list), model_list = model_list)

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

            # Add or Remove model here
            chosen_model = request.form['select_model']
            model_list = list_models(app.config['MODELS_FOLDER'])
            for model in model_list:
                if model == chosen_model:
                    model_handle = model
                else:
                    model_handle = chosen_model

            return redirect(url_for('detect_image', filename=filename, model=model_handle))
    return render_template('index.html')

category_index = load_label_map(app.config['LABEL_FOLDER'])
@app.route('/detect/<model>/<filename>')
def detect_image(model, filename):
    IMAGE_PATH = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    MAX_BOXES_TO_DRAW = 3
    MIN_SCORE_THRESH = .5
    image_path = Image.open(IMAGE_PATH)
    image_path = image_path.convert('RGB')
    image_np = load_image_into_numpy_array(image_path)
    model_handle = os.path.join(app.config['MODELS_FOLDER'], str(model))
    hub_model = hub.load(model_handle)
    detection_classes, detection_scores, image_np_with_detections = run_inference(hub_model, category_index, image_np, MAX_BOXES_TO_DRAW, MIN_SCORE_THRESH)

    im_path = save_image(image_np_with_detections, filename, app.config['RESULT_FOLDER'])

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

# Generic Exception Handlers from FLASK
@app.errorhandler(HTTPException)
def handle_exception(e):
    """Return JSON instead of HTML for HTTP errors."""
    # start with the correct headers and status code from the error
    response = e.get_response()
    # replace the body with JSON
    response.data = json.dumps({
        "code": e.code,
        "name": e.name,
        "description": e.description,
    })
    response.content_type = "application/json"
    return response

if __name__ == "__main__": 
    app.run(host='0.0.0.0', port=int(os.environ.get("PORT", 5000)))