FROM python:3.8

COPY . .

RUN apt-get update

RUN apt-get install -y protobuf-compiler

RUN protoc object_detection/protos/*.proto --python_out=. 

RUN cp object_detection/packages/tf2/setup.py . 

RUN python -m pip install . 

COPY requirements.txt .

RUN pip install -r requirements.txt

# Set env variables for Cloud Run
ENV PORT 5000
ENV HOST 0.0.0.0

# Open port 5000
EXPOSE 5000:5000

# Run flask app
CMD ["python","app.py", "gunicorn"]