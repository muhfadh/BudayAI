# The Documentation from Cloud Computing Learning Path
> Build a REST API for Mobile Development Learning Path

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
\
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
\
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
\
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
  - `"message: data created successfully!"`
