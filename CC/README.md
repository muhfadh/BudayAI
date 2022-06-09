# The Documentation from Cloud Computing Learning Path
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
- `/detail` (<-- two spaces)
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
