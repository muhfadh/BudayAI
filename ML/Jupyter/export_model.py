#!/usr/bin/env python
# coding: utf-8

# In[57]:


def freeze_graph(FREEZE_SCRIPT, PIPELINE_CONFIG, CHECKPOINT_PATH, OUTPUT_PATH):
    command = "python {} --input_type=image_tensor --pipeline_config_path={} --trained_checkpoint_dir={} --output_directory={}".format(FREEZE_SCRIPT, PIPELINE_CONFIG, CHECKPOINT_PATH, OUTPUT_PATH)
    get_ipython().system('{command}')


# In[58]:


def export_to_tfjs(OUTPUT_PATH_SAVED_MODEL, TFJS_PATH):
    command = "tensorflowjs_converter --input_format=tf_saved_model --output_node_names='detection_boxes,detection_classes,detection_features,detection_multiclass_scores,detection_scores,num_detections,raw_detection_boxes,raw_detection_scores' --output_format=tfjs_graph_model --signature_name=serving_default {} {}".format(OUTPUT_PATH_SAVED_MODEL, TFJS_PATH)
    get_ipython().system('{command}')


# In[59]:


def export_to_tflite(TFLITE_SCRIPT, PIPELINE_CONFIG, CHECKPOINT_PATH, TFLITE_PATH, FROZEN_TFLITE_PATH, TFLITE_MODEL):
    try:
        command = "python {} --pipeline_config_path={} --trained_checkpoint_dir={} --output_directory={}".format(TFLITE_SCRIPT, PIPELINE_CONFIG, CHECKPOINT_PATH, TFLITE_PATH)
        get_ipython().system('{command}')
        command = "tflite_convert         --saved_model_dir={}         --output_file={}         --input_shapes=1,300,300,3         --input_arrays=normalized_input_image_tensor         --output_arrays='TFLite_Detection_PostProcess','TFLite_Detection_PostProcess:1','TFLite_Detection_PostProcess:2','TFLite_Detection_PostProcess:3'         --inference_type=FLOAT         --allow_custom_ops".format(FROZEN_TFLITE_PATH, TFLITE_MODEL, )
        get_ipython().system('{command}')
    except ValueError:
        raise ValueError("Can't export model to tflite")
    except:
        print("Something else went wrong")

