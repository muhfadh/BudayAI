import numpy as np
import os

from PIL import Image
# Using library from tensorflow object detection api https://github.com/tensorflow/models/tree/master/research/object_detection
from object_detection.utils import label_map_util
from object_detection.utils import visualization_utils as viz_utils

def load_label_map(LABEL_PATH):
    """Load a label map from label path (.pbtext) and return category index.

    Create category index from labelmap (.pbtext)

    Args:
    LABEL_PATH: the file path to the labelmap (.pbtext)

    Returns:
    A category index, which is a dictionary that maps integer ids to dicts
    containing categories, e.g.
    {1: {'id': 1, 'name': 'angklung'}, 2: {'id': 2, 'name': 'beskap_jawa'}, ...}
    """
    # List of the strings that is used to add correct label for each box.
    category_index = label_map_util.create_category_index_from_labelmap(LABEL_PATH)
    return category_index

def load_image_into_numpy_array(image):
    """Load an image from file into a numpy array.

    Puts image into numpy array to feed into tensorflow graph.
    Note that by convention we put it into a numpy array with shape
    (height, width, channels), where channels=3 for RGB.

    Args:
    path: the file path to the image

    Returns:
    uint8 numpy array with shape (img_height, img_width, 3)
    """
    (image_width, image_height) = image.size
    return np.array(image.getdata()).reshape((1, image_height, image_width, 3)).astype(np.uint8)

def run_inference(model, category_index, image_np, MAX_BOXES_TO_DRAW, MIN_SCORE_THRESH):
    """run inference to detect object from an image and returns detection_classes, detection_scores, image_np_with_detections

    Args:
    model: hub model from saved model (.pb)
    category_index: category index from label map
    image_np: image with array numpy
    MAX_BOXES_TO_DRAW: maximum boxes that model can draw
    MIN_SCORE_THRESH: minimum threshold of detection

    Returns:
    list of detection_classes, list of detection_scores, and image_np_with_detections
    """
    
    # running inference
    detections = model(image_np)

    result = {key:value.numpy() for key,value in detections.items()}
    label_id_offset = 0
    image_np_with_detections = image_np.copy()
    detection_classes = (result['detection_classes'][0] + label_id_offset).astype(int)
    detection_scores = result['detection_scores'][0]
    viz_utils.visualize_boxes_and_labels_on_image_array(
                image_np_with_detections[0],
                result['detection_boxes'][0],
                detection_classes,
                detection_scores,
                category_index,
                use_normalized_coordinates=True,
                max_boxes_to_draw=MAX_BOXES_TO_DRAW,
                min_score_thresh=MIN_SCORE_THRESH,
                agnostic_mode=False,
                line_thickness=2)

    return detection_classes, detection_scores, image_np_with_detections

def save_image(image_np_with_detections, filename, RESULT_FOLDER):
    """save image using Image from PIL to path RESULT_FOLDER

    Args:
    image_np_with_detections: image in numpy array that contain detections
    filename: filename that have (JPG, JPEG, or PNG) extensions.
    RESULT_FOLDER: path folder that contain the result of object detection image's

    Returns:
    image path, that will stored image with that path in the {RESULT FOLDER}/{filename}
    """
    im = Image.fromarray(image_np_with_detections.squeeze())
    im_path = os.path.join(RESULT_FOLDER, filename)
    im.save(im_path)
    return im_path
