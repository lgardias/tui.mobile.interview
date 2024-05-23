
# Tui Mobile Interview

Project created for interview purposes.

## Getting Started


### Prerequisites

Required software:

    Java 17
    Gradle 8.5 or higher
    Docker (optional)
    Jenkins (optional)

### Installing

To build project run:

    gradle build

and next run built .jar file (example):

    java -jar build/libs/TuiMobileInterview-1.0-SNAPSHOT.jar


## Running the tests

To run the test use command:

    gradle test

## Deployment - build container

If you want to create project container use docker:

Create new image using dockerfile:

    docker build -t <image_name>:<image_version>

Run image on container exposed on port 8080:

    docker run -p 8080:8080  -d <image_name>:<image_version>

You can check if container running without errors using:

    docker container ls

## Documentation 

For api documentation you can check swagger_documentation.yaml file.
