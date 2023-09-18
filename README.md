# Coffee Shop Management Microservices System

---

## Overview

This system is designed to manage various aspects of coffee shops, including user authentication, shop details, and order management. It is composed of three main microservices:

- **Authorization Service**: This service is responsible for generating and validating tokens, as well as checking user permissions. It is crucial for securing the application and ensuring only authorized users can access certain functionalities
- **User Service**: Handles user and shop owner/admin authentication. It manages user credentials and roles.
- **Shop Service**: Manages all aspects related to shops, including information details and order management.


## Getting Started

Here are the Swagger documentation links for each service:

- **User-service**: [Swagger UI](http://35.213.152.229:8085/swagger-ui/index.html)
- **Shop-service**: [Swagger UI](http://35.213.152.229:8086/swagger-ui/index.html)
- **Authorization-service**: [Swagger UI](http://35.213.152.229:8086/swagger-ui/index.html)

  API available for test
  ![API](https://github.com/haivutuan93/coffee-shop-management/blob/develop/image/Screenshot%202023-09-18%20at%2022.10.35.png?raw=true)

### Common Library

Before running any service, make sure to build the common library:

```bash
cd common-library
mvn clean install
```


## Kubernetes Deployment

### Using Kubernetes Configuration Files


1. **Apply Configurations**:

    ```bash
    kubectl apply -f authorization-service.yaml
    kubectl apply -f user-service.yaml
    kubectl apply -f shop-service.yaml
    kubectl apply -f ingress
    ```

2. **Authentication**: All incoming requests first pass through the API Gateway, which forwards them, along with an access token, to the Authorization Service to verify permissions

   ```bash
    nginx.ingress.kubernetes.io/auth-url: http://authorization.dev.svc.cluster.local:8087/permission?url=$request_uri&method=$request_method
    ```



## Using Liquibase for Database Migrations

Liquibase is used in this project to manage database migrations. It provides a simple and effective way to version-control your database schema changes and apply them across different environments.
### Sample Data
You can find sample data for the database in the `db/changelog` folder.
- [Sample data Shop](https://github.com/haivutuan93/coffee-shop-management/blob/develop/shop-service/src/main/resources/db/sources/20230918_add_data_shop.sql)
- [Sample data User](https://github.com/haivutuan93/coffee-shop-management/blob/develop/user-service/src/main/resources/db/sources/20230918_add_data_users_roles.sql) 

Preview sample data for User
```java
-- Password for all users is "123"
INSERT INTO users (id, username, password, email, mobile_number, regular_address, total_score, created_by, updated_by)
VALUES (1, 'customer1', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'customer1@example.com', '1234567890', 'Address 1', 0, 'admin', 'admin'),
       (2, 'customer2', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'customer2@example.com', '1234567891', 'Address 2', 0, 'admin', 'admin'),
       (3, 'customer3', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'customer3@example.com', '1234567892', 'Address 3', 0, 'admin', 'admin'),
       (4, 'operator1', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'operator1@example.com', '1234567893', 'Address 4', 0, 'admin', 'admin'),
       (5, 'owner1',    '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'owner1@example.com',    '1234567894', 'Address 5', 0, 'admin', 'admin');

```


## Exception Handling

The system uses a common library to handle exceptions consistently across all microservices. The `BaseExceptionHandler` class in the common library provides methods to handle various types of exceptions.

### BaseExceptionHandler Class

Here is an example of the `BaseExceptionHandler` class:

```java
public abstract class BaseExceptionHandler {
    @Value("${microservice.error.prefix:}")
    String prefix;

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e, Locale locale) {
        return new ResponseEntity<>(buildErrorResponse(e.getErrorCode(), locale), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e, Locale locale) {
        return new ResponseEntity<>(buildErrorResponse(e.getErrorCode(), locale), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException e, Locale locale) {
        return new ResponseEntity<>(buildErrorResponse(e.getErrorCode(), locale), HttpStatus.FORBIDDEN);
    }

    private ErrorModel buildErrorResponse(int errorCode, Locale locale){
        return ErrorModel.builder()
                .errorCode(errorCode)
                .errorMessage(messageSource.getMessage(String.valueOf(errorCode), null, locale))
                .build();
    }
}
```

### Using BaseExceptionHandler in Services

In each service, you can extend the `BaseExceptionHandler` class to handle exceptions specific to that service. Here's how you can do it:

```java
@ControllerAdvice
public class ExceptionHandler extends BaseExceptionHandler {
    public ExceptionHandler() {
    }
}
```

This ensures that all services have a consistent way of handling exceptions.

