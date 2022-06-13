# BudayAI 
<img src="https://user-images.githubusercontent.com/43193982/173267843-4ee94905-dd63-4b85-b860-9acc3788df8d.png" width="500" height="500">
BudayAI is a tourism cultural app that can provide a piece of information about Indonesia's cultural ornament. Our app use object detection model for the detection of Indonesian Cultural Ornament from an objection to the form of an Image and will show information about the detected object like name, origin, history, etc, and can show location for each ornament object using Google Maps API.
<br/>
<br/>

# The Documentation of Capstone Project BANGKIT 2022

#### Overview Project :
Indonesia is experiencing rapid development in rural tourism. The Central Statistics Agency (BPS) recorded that there were around 1,302 tourist villages in 2014, that number jumped to 1,734 villages with the potential to become tourist villages throughout 2018. Many tourist villages integrated their culture in the form of cultural objects such as sculptures, paintings, carvings, or building architecture. These cultural objects have fascinating hidden meanings that describe the cultural identity of the area. However, It is quite unfortunate that general people do not know the philosophical or historical value of a cultural object. So, the main question is how can we provide information to tourists about the cultural value of a cultural object? BudayAI is a tourism cultural app that can provide a piece of information about Indonesia's cultural ornament. Our app use object detection model for the detection of Indonesian Cultural Ornament from an objection to the form of an Image and will show information about the detected object like name, origin, history, etc, and can show location for each ornament object using Google Maps API. We choose this problem because we believe digitalization of ornament culture will add unique value  and help boost the tourism sector in the village. With the application of CultureAI, it is hoped that it can help the community to know the historical or philosophical value of the objects in the tourist village. An easy way that we do to help digitize culture is by utilizing developing technology to detects cultural objects and display the available information automatically.By building this technology, we have done to this product capstone project detail for each path mentioned below:
- Machine Learning Path: Scrapping dataset from google images & labeling images, building models with TensorFlow object detection API, Build Flask App (Endpoint API).
- Mobile Development Path: UI/UX design with Figma and layouting apps, building apps with Android Studio, using Google Maps API for detecting location, utilizing the camera library for object capture, consuming API from CC and ML learning path with Retrofit library.  
- Cloud Computing Path: Landing Page Website, Build API, Deploy Flask App from ML Path to GCP.
<hr>

# MACHINE LEARNING PATH

## About Dataset
At first we searched for datasets on google images for [21 classes](https://drive.google.com/drive/folders/1mpqvmKqy0pM11oeYM4NWM_WAav9COSMo?usp=sharing) and [42 classes](https://drive.google.com/drive/folders/11wr-06EueoWIsNSKoOEuv9QzDI6L6uJ2?usp=sharing), but after conducting mentoring, we had to limit it to a few things, the scope of solutions (Datasets) for Indonesian culture is only (**limited to**) based on the Javanese, Sundanese, Dayak, Batak, and Minang ethnic groups. Also based on some kinds of cultural ornaments like “Alat musik”, “Atribut kepala”, “Baju”, “Benda seni”, “rumah adat”, “senjata khas”. And then just only **30 classes**. Link of the dataset you can see here [30 classes dataset](https://drive.google.com/drive/folders/1kKkL3ZAacjgmfQHSjVG-2fQDi32vapRa?usp=sharing)

The dataset consists of training data and test data, where the training data consists of 1243 images and the test data consists of 317 images in 30 different labels/objects. In 1 label/class/object consists of ± 50 images where 80% for training data and 20% for test data. We labeled the image using labelImg [labelImg](https://tzutalin.github.io/labelImg/).

**DISCLAIMER** 

Copyright images from google images or the uploader of the images. Our team only collected and gathered the images and then labeling for training model object detection.

## Approach / Model Architecture for object detection

### Workflow
![ML flow](https://user-images.githubusercontent.com/43193982/173262113-063d858b-21e3-4eb0-bc22-d4fd1e5666c7.jpg)

Our team used [tensorflow api object detection](https://github.com/tensorflow/models/tree/master/research/object_detection) to build our model. 
We trained several models from the pretrained-models available in the [TensorFlow 2 Detection Model Zoo](https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/tf2_detection_zoo.md)

Because of limited resource to training model, Some of the existing models (at the most) are trained in a work environment with the following specifications:
- Intel(R) Core(TM) i7-9750H CPU @2.60GHz (12CPUs), ~2.6GHz
- 16GB RAM
- NVIDIA GeForce GTX 1650

## Eval Metrics for the models we have trained
| No | Model Name | Changed Config | Training time |
| --- | --- | --- | --- |
| 1 | custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_1 | BATCH_SIZE = 12 | 2h 29m 41s
| 2 | custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_2 | BATCH_SIZE = 14 | 10h 34m 37s (error in tensorboard, actually 3-4h) |
| 3 | custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_3 | BATCH_SIZE = 12, gamma: 2.25, alpha: 0.5 | 2h 43m 46s |
| 4 | custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_4 | BATCH_SIZE = 12, min_depth = 8 | 2h 41m 0s |
| 5 | custom_ssd_mobilenet_v2_fpnlite_320x320_V2 | BATCH_SIZE = 8
| 6 | custom_ssd_mobilenet_v2_fpnlite_320x320_V3 | BATCH_SIZE = 12
| 7 | custom_ssd_mobilenet_v2_fpnlite_640x640_1 | BATCH_SIZE = 4
| 8* | custom_centernet_resnet101_v1_fpn_512x512 | BATCH_SIZE = 2
| 9* | faster_rcnn_resnet50_v1_640x640 | BATCH_SIZE = 2 | 
| 10 | 30C_1_custom_ssd_mobilenet_v2_fpnlite_320x320 | BATCH_SIZE = 16, min_depth = 8 | 3h 30m 30s |
| 11 | 30C_2_custom_ssd_mobilenet_v2_fpnlite_320x320 | BATCH_SIZE = 16, min_depth = 8 | 3h 28m 11s |
| 12 | 30C_3_custom_ssd_mobilenet_v2_fpnlite_320x320 | BATCH_SIZE = 8 | 1h 26m 22s |

**Average Precision**

| No | mAP | mAP @.50IOU | mAP @.75IOU | mAP (small) | mAP (medium) | mAP (large) | steps | total_classes | 
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.410 | 0.621 | 0.454 | 0.101 | 0.294 | 0.425 | 15000 | 42 | 
| 2 | 0.459 | 0.660 | 0.526 | 0.151 | 0.303 | 0.473 | 15000 | 42 | 
| 3 | 0.427 | 0.623 | 0.471 | 0.101 | 0.294 | 0.442 | 15000 | 42 | 
| 4 | 0.457 | 0.667 | 0.547 | 0.050 | 0.321 | 0.480 | 15000 | 42 |
| 5 | 0.446 | 0.678 | 0.518 | -1 | 0.264 | 0.449 | 20000 | 21 |
| 6 | 0.453 | 0.677 | 0.498 | -1 | 0.209 | 0.459 | 15000 | 21 |
| 7 | 0.320 | 0.551 | 0.346 | -1 | 0.223 | 0.322 | 15000 | 21 |
| 8 | 0.355 | 0.524 | 0.385 | -1 | 0.148 | 0.358 | 4200  | 21 |
| 9 | 0.282 | 0.494 | 0.278 | 0.000 | 0.008 | 0.298 | 15000 | 30 |
| 10 | 0.494 | 0.724 | 0.541 | 0.500 | 0.248 | 0.500 | 15000 | 30 |
| 11 | 0.451 | 0.666 | 0.506 | 0.700 | 0.190 | 0.459 | 15000 | 30 |
| 12 | 0.442 | 0.683 | 0.457 | 0.2 | 0.160 | 0.454 | 15000 | 30 |

**Average Recall**

| No | AR@1 | AR@10 | AR@100 | AR@100 (small) | AR@100 (medium) | AR@100 (large) | steps | total_classes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.446 | 0.540 | 0.550 | 0.100 | 0.433 | 0.549 | 15000 | 42 |
| 2 | 0.467 | 0.584 | 0.591 | 0.150 | 0.443 | 0.600 | 15000 | 42 |
| 3 | 0.473 | 0.562 | 0.568 | 0.100 | 0.456 | 0.574 | 15000 | 42 |
| 4 | 0.481 | 0.593 | 0.604 | 0.050 | 0.441 | 0.628 | 15000 | 42 |
| 5 | 0.455 | 0.570 | 0.577 | -1| 0.390 | 0.574 | 20000 | 21 |
| 6 | 0.450 | 0.562 | 0.575 | -1| 0.300 | 0.578 | 15000 | 21 |
| 7 | 0.375 | 0.514 | 0.536 | -1| 0.313 | 0.536 | 15000 | 21 |
| 8 | 0.459 | 0.625 | 0.634 | -1| 0.295 | 0.640 | 4200 (initial steps 15000) | 21 |
| 9 | 0.342 | 0.457 | 0.463 | 0.000 | 0.030 | 0.485 | 15000 | 30 |
| 10 | 0.497 | 0.615 | 0.623 | 0.500 | 0.393 | 0.630 | 15000 | 30 |
| 11 | 0.480 | 0.594 | 0.607 | 0.700 | 0.294 | 0.617 | 15000 | 30 |
| 12 | 0.458 | 0.593 | 0.605 | 0.400 | 0.312 | 0.620 | 15000 | 30 |

**loss**

| No | localization_loss | classification_loss | regularization_loss | total_loss | steps | total_classes |
| --- | --- | --- | --- | --- | --- |  --- |
| 1 | 0.257 | 0.577 | 0.123 | 0.958 | 15000 | 42 |
| 2 | 0.239 | 0.581 | 0.117 | 0.938 | 15000 | 42 |
| 3 | 0.258 | 0.800 | 0.138 | 1.196 | 15000 | 42 |
| 4 | 0.233 | 0.520 | 0.125 | 0.878 | 15000 | 42 |
| 5 | 0.281 | 0.588 | 0.110 | 0.978 | 20000 | 21 |
| 6 | 0.290 | 0.602 | 0.110 | 1.001 | 15000 | 21 |
| 7 | 0.341 | 0.557 | 0.141 | 1.039 | 15000 | 21 |
| 8* | loss_box_offset = 0.506 | loss_box_scale = 1.168 | loss_object_center = 2.327 | 4.000 | 4200 (initial steps 15000) | 21 |
| 9* | Loss/BoxClassifierLoss/localization_loss = 0.113, Loss/RPNLoss/localization_loss = 0.334 | Loss/BoxClassifierLoss/classification_loss = 0.189 |  Loss/regularization_loss = 0, Loss/RPNLoss/objectness_loss = 0.230| Loss/total_loss = 0 | 15000 | 30 |
| 10 | 0.207 | 0.573 | 0.106 | 0.886 | 15000 | 30 |
| 11 | 0.227 | 0.608 | 0.108 | 0.942 | 15000 | 30 |
| 12 | 0.244 | 0.511 | 0.143 | 0.899 | 15000 | 30 |


## Implementation 

### Run Online through browser
Click [here](https://budayai-7zcxfbjfqq-uc.a.run.app/)

### How to run local flask app
#### Way 1
Using Python Package
- Clone this repository, but you just need budayai-flask-docker-gcloudrun folder for run flask app locally
- Open CMD/Terminal and go to your application directory OR Change directory to /budayai-flask-docker-gcloudrun
- Type` python -m venv <your-venv-name>` or `conda create --name <yourenv>` and enter
- If using python env: In Linux, `source .venv/bin/activate` or in Windows `.<your-venv-name>\Scripts\activate`
- If using conda env: in anaconda prompt `conda activate <yourenv>`
- Compile protos: `protoc object_detection/protos/*.proto --python_out=.`
- Install TensorFlow Object Detection API: `cp object_detection/packages/tf2/setup.py . ` then, `python -m pip install .` or `python -m pip install --use-feature=2020-resolver .`
- Then in your env, type `pip install -r requirements.txt`
- Run flask app with `flask run` OR `python app.py`
- If NoModule, install that module

#### Way 2 
Using Dockerfile from [here](https://github.com/tensorflow/models/blob/master/research/object_detection/dockerfiles/tf2/Dockerfile)

build and run this command in your terminal/command prompt
- `docker build -f research/object_detection/dockerfiles/tf2/Dockerfile -t od .`
- `docker run -it od`

#### Way 3 
Using Dockerfile from budayai-flask-docker-gcloudrun folder
Inside that folder 
- run `docker build -f Dockerfile -t od .`
- then, run `docker run -it od`


## Result of work

The **Machine Learning path** has the following results:
1. [Models](https://github.com/muhfadh/BudayAI/tree/main/ML/models)
2. [Flask](https://github.com/muhfadh/BudayAI/tree/main/ML/budayai-flask-docker-gcloudrun) app (endpoint/url API)

| URL | Method | Request Body | Response |
| --- | --- | --- | --- |
| / | GET | None | Web View |
| /detect | POST | `image` as `file` (must be a valid image file, max size 5MB) | JSON |


### Via Browser

If you run flask app locally or online, the view is still simple like this
- you can input/upload image (Only JPG, JPEG, PNG are allowed)
- choose/select an available models

![Capture0](https://user-images.githubusercontent.com/43193982/173258798-61d9f67f-4436-43e8-95fc-fa34b6508e9c.PNG)


Flask app will return some JSON data through the browser
![Capture1](https://user-images.githubusercontent.com/43193982/173258903-8a2d3471-6188-473c-b03b-b566c64c472d.PNG)

![Capture2](https://user-images.githubusercontent.com/43193982/173259904-69253e1b-4ea2-4289-b196-9e4680342466.PNG)


### Via Postman

Flask app also will return some JSON data through the browser
![Capture3](https://user-images.githubusercontent.com/43193982/173259943-50388532-1eea-410b-b549-7ed01488834b.PNG)

> We set if detection_scores have not reached the minimum threshold score, we just send JSON data that contain image_url without detections
![Capture4](https://user-images.githubusercontent.com/43193982/173260494-435570dd-8481-405c-974b-3bd9bb2c7377.PNG)  


## References
- https://github.com/nicknochnack/TFODCourse
- https://github.com/wingedrasengan927/Tensorflow-2-Object-Detection-API-Flask-Application
- https://github.com/VictorRancesCode/FlaskObjectDetection
- https://colab.research.google.com/github/tensorflow/hub/blob/master/examples/colab/tf2_object_detection.ipynb#scrollTo=2O7rV8g9s8Bz

<br/>
<hr>

# MOBILE DEVELOPMENT PATH

## OnBoarding dan Login Pop Up Page
<p align="center">
    <img src="https://user-images.githubusercontent.com/79825517/173267797-d07ca202-47c2-43f2-9255-3c49cf7f325c.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267806-cfc61ea5-78a1-4803-9318-e17c01d8da0d.jpg" style="width: 175px;height: auto;border-radius:5px" />
<p>

## Home Page
<p align="center">
    <img src="https://user-images.githubusercontent.com/79825517/173267811-9115e7e4-ef65-49f7-a2d5-86ef8649902d.jpg" style="width: 175px;height: auto;border-radius:5px" />
<p>

## Detail Page
<p align="center">
    <img src="https://user-images.githubusercontent.com/79825517/173267824-860f913d-d38f-40a5-95cc-9243643614b6.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267834-ecbc70a0-a62b-4a98-a259-ca71a5ca0703.jpg" style="width: 175px;height: auto;border-radius:5px" />
<p>

## Location and Maps Page
<p align="center">
    <img src="https://user-images.githubusercontent.com/79825517/173267854-368486e2-f138-4da4-bfc3-9125f5cec86b.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267866-0f158cc5-fc63-494c-bb49-d32de3427e35.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267876-f05a4f44-10a2-4445-b4a1-1712d9a819be.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267890-b5519c73-ba17-4afb-926b-5c9aabd067fb.jpg" style="width: 175px;height: auto;border-radius:5px" />
<p>

## Analys Page and Results
<p align="center">
    <img src="https://user-images.githubusercontent.com/79825517/173267903-d02f0960-2f86-402f-a82f-66af2f0d4647.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267915-9f0c2fd9-023f-4623-8716-1f77b67f49f0.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267934-f054ff70-cd8b-4b96-909c-a84be0323c74.jpg" style="width: 175px;height: auto;border-radius:5px" />
    <img src="https://user-images.githubusercontent.com/79825517/173267946-df8ee760-db03-40c4-9a91-714f0e4b5168.jpg" style="width: 175px;height: auto;border-radius:5px" />-->
<p>
    
## Features
   * Detect Location: Detect location for ornamen with data latitude and langitude from CC API
   * Image Clasification: Use machine learning technology for identification and clasify ornament by image object

## Links
   * APK File: 
    https://drive.google.com/file/d/1qKLrQP7eWxCVyNHAbeco6tStx_uxichz/view
   * Demo Video:
    https://drive.google.com/file/d/1srDBI3lUyK8Oy8_8R2O6VZ0Lud0mZ0FY/view?usp=sharing
   * Figma Design File: 
    https://www.figma.com/file/G0z5wz2LUkFri7D2qho8XQ/BudayAI?node-id=0%3A1

## Tech Stack
   * Figma:
    https://www.figma.com/
   * Recycler View: 
    https://developer.android.com/jetpack/androidx/releases/recyclerview?hl=id
   * Navigation: 
    https://developer.android.com/jetpack/androidx/releases/navigation?hl=id
   * View Pager:
    https://developer.android.com/jetpack/androidx/releases/viewpager2?hl=id
   * Glide:
    https://github.com/bumptech/glide/releases
   * CameraX:
    https://developer.android.com/jetpack/androidx/releases/camera
   * Google Play Service:
    https://developers.google.com/android/guides/releases
   * Data Store:
    https://developer.android.com/jetpack/androidx/releases/datastore
   * Retrofit2:
    https://square.github.io/retrofit/
   * OkHttp:
    https://square.github.io/okhttp/

<br/>
<hr>

# CLOUD COMPUTING PATH
> - Build an our Landing Page Website
> - Build a REST API for Mobile Development Learning Path

## Landing Page Website
> Use HTML Semantic, Vanilla CSS and JavaScript.
> This is a link for our website : [BudayAI-Web](https://buday-ai-vickyadri29.vercel.app/)

## Endpoint
> The Main Endpoint is [Here](https://us-central1-budayai-c22-ps195.cloudfunctions.net/app/).

### Home
- URL
  - `/api/v1/home`
- Method
  - GET
  - POST
  - PUT
  - DELETE
- Request Body
  - `id` as `int`
  - `ethnic_name` as `string`
  - `type` as `string`
  - `photo_url` as `string`
- Response
  - `"message: data created successfully!"`
  - 
### Class
- URL
  - `/api/v1/home`
- Method
  - GET
  - POST
  - PUT
  - DELETE
- Request Body
  - `id` as `int`
  - `type` as `string`
  - `detail` as `string`
  - `history` as `string`
  - `photo_url` as `string`
  - `lat` as `string`, to get a latitude from Google Maps API to show the maps
  - `long` as `string`, to get a longtitude from Google Maps API to show the maps
- Response
  - `"message: data created successfully!"`

### Cultural
- URL
  - `/api/v1/cultural`
- Method
  - GET
  - POST
  - PUT
  - DELETE
- Request Body
  - `id` as `int`
  - `photo_url` as `string`
  - `type` as `string`
  - `description` as `string`
- Response
  - `"message: data created successfully!"`

### Detail
- URL
  - `/api/v1/detail`
- Method
  - GET
  - POST
  - PUT
  - DELETE
- Request Body
  - `id` as `int`
  - `photo_url` as `string`
  - `type` as `string`
  - `detail` as `string`
- Response
  - `"message: data created successfully!"`<br/>

## Our Project Using Google Cloud Platform
### Description
For Design Architecture, we use Cloud Function and Cloud Run to deploy the application. For Cloud Function using Express JS for the local server, and the database using Firestore Database (NoSQL). There are 4 Endpoints that can be used (consume) by Mobile Development. These endpoints are used to display the cultural data set.
- `/home`
- `/class`
- `/cultural`
- `/detail` <br/>
The Cloud Functions Architecture is here :
![Cloud Functions](https://storage.googleapis.com/budayai-datasets/RestAPI.png)

The environment we set up in Cloud Run was:
- Build Python App with Flask
- Push Python App to Github
- Create an Image with Docker
- Build in Google Cloud Platform :
  - Clone the app from Github
  - Build the app
  - Push the app 

The Cloud Run Architecture is here :
![Architecture Cloud Run](https://storage.googleapis.com/budayai-datasets/FlaskApp.png)

## References
- [Cloud Functions Architecture](https://www.fiverr.com/arslan_96/write-google-cloud-function-with-firebase)
- [Build REST API](https://www.youtube.com/watch?v=3dFT7QaVSZ8&list=PLJetLDY7yKupm5WTx02ylh1I25rJLPvXe)
- [Fix Error while Deploy](https://stackoverflow.com/questions/48602833/eslint-error-while-trying-to-deploy-firebase-functions)
- [Push Image Docker to Container Registry](https://www.youtube.com/watch?v=_XAk5T_4-O0&t=376s)

#### Ourteam :
- (ML) M2258G2239 - Muhammad Fadhlan - Universitas Muhammadiyah Malang
- (ML) M7299J2575 - Muhammad Akmalul Iman Liari - Universitas Pendidikan Indonesia
- (MD) A2258F2262 - Bella Dwi Mardiana - Universitas Muhammadiyah Malang
- (MD) A2258F2261 - Dinda Arinawati Wiyono - Universitas Muhammadiyah Malang
- (CC) C2323J2840 - Vicky Herdiansyah Adri - Universitas Tadulako
- (CC) C7172G1703 - Mhd. Taufan Hidayat  - Universitas Mikroskil
