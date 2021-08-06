# API Project (Java)

## Description

This is an API that is used to store Recipes and Ingredients,
it is part of a large platform of applications that exist to
create personal recipes.

## Requirements

1. Docker
2. Java 11

## How to run this app

### Docker

To run this application in docker as if it were in production use the following instructions:

1. Create an .env file to run the application. It should have the following format:

    ```env

    # Database Settings
    PG_HOST="0.0.0.0"
    PG_PORT="5432"
    PG_NAME="food"
    PG_USER="root"
    PG_PASSWORD="password"

    # App Settings
    APP_PORT=3000

    ```

2. Run `docker-compose up -d pg_db`

### Local

To run this application locally use the following instructions:

1. Use `.env.example` to create an .env file to run the application. It should have the following format:

    ```env
    # Database Settings
    PG_HOST="0.0.0.0"
    PG_PORT="5432"
    PG_NAME="food"
    PG_USER="root"
    PG_PASSWORD="password"

    # App Settings
    APP_PORT=3000
    ```

2. `docker-compose up -d pg_db`
3. `./mvnw spring-boot:run`

The app will run on a url like this: http://host:port-number, (i.e http://localhost:3000/api)

## Running Tests

To run the application tests use the following instructions:

1. run ./mvnw clean test

## How to access Open API docs

To access Open API docs please go to the following route:

*http://localhost:3000/swagger-ui*
