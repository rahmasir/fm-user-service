# User Service (fm-user-service)

This microservice is the central authority for all user-related operations in the Freelancer Market application.

## Core Responsibilities

* **User Registration**: Handles the creation of new `FREELANCER` and `EMPLOYER` accounts.
* **Authentication**: Manages user login via email and password, and issues JWTs upon success.
* **Authorization**: Validates JWTs for incoming requests to secure endpoints.
* **Profile Management**: Allows users to view and update their profiles.
* **Skill Management**: Provides endpoints to view and manage the global list of skills.

## 💻 Technology Stack

* **Framework**: Spring Boot
* **Database**: PostgreSQL with Spring Data JPA
* **Caching**: Redis
* **Security**: Spring Security with JSON Web Tokens (JWT)
* **API Documentation**: Springdoc OpenAPI (Swagger UI)

## Running the Service

1.  Ensure the development infrastructure is running. From the `fm-hub` root, execute:
    ```bash
    make up-dev
    ```
2.  Ensure the project has been built successfully. From the `fm-hub` root, execute:
    ```bash
    make build
    ```
3.  Run the `FmUserServiceApplication.java` main class from your IDE. The service will start on port `8081`.

## API Documentation

Once the service is running, you can access the interactive API documentation (Swagger UI) at:

[http://localhost:8081/http://localhost:8081/swagger-ui/index.html](http://localhost:8081/http://localhost:8081/swagger-ui/index.html)

You can use this interface to test all the available endpoints, including registration, login, and profile management.
