# Bugtracker by Dominik Klein

## Building and running the application locally

### Prerequisites

For running the application locally you need to install the following software:

- Docker
- Java 11 or newer

### 1. Starting necessary docker container

This project uses a docker database. To start its container simply run in the root folder:

```docker-compose up -d```

To shut it down simply run:

``docker-compose down``

### 2. Building the application

In the root folder run:

``./mvnw clean install``

### 3. Running the application

The previous step should create a target folder. In this folder you can find a ``.jar`` file. Run it with the following
command:

``mvn -jar target/bugtracker.jar``