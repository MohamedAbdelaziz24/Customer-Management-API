# Customer Management API

A Kotlin REST API for managing customers and their addresses. The project uses Spring MVC, Spring Data JPA, Hibernate, and an embedded Tomcat server, with MySQL for persistence.

## Features

- Create, retrieve, update, partially update, and delete customer records
- Store each customer's address with the customer
- Validate request data and return structured error responses
- Retrieve customers in pages
- Initialize the database with sample customers when it is empty
- Describe the API in the OpenAPI specification at [`openapi.yaml`](openapi.yaml)

## Requirements

- JDK 17 or later
- Maven
- MySQL

## Run locally

1. Create a MySQL database named `customer_db`.
2. Check the connection settings in `src/main/kotlin/config/JpaConfig.kt` and update them for your local MySQL username and password. Before publishing this repository, replace hardcoded credentials with environment-based configuration and never commit real passwords.
3. From the project root, build and start the application:

   ```bash
   mvn clean package
   mvn exec:java
   ```

The embedded server listens on `http://localhost:8080`. On its first start with an empty database, the application inserts ten sample customers.

## API

Customer endpoints are rooted at `/api/customers`. The API supports customer creation, retrieval, full and partial updates, deletion, and paginated retrieval. See [`openapi.yaml`](openapi.yaml) for request and response schemas and the documented routes.

## Technology

- Kotlin
- Spring MVC and Spring Data JPA
- Hibernate
- MySQL
- Embedded Apache Tomcat
- Maven
