# Library Management System

A Spring Boot based Library Management System for managing students, books, library cards, and book issue/return transactions.

## Features

- Manage students
- Manage books
- Manage library cards
- Issue and return books
- Track transaction details
- REST API based backend
- MySQL database integration
- Spring Data JPA for database operations

## Tech Stack

- Java 17
- Spring Boot 3.5.16
- Spring Web
- Spring Data JPA
- MySQL
- Lombok
- Maven

## Project Structure
```text
src/main/java/com/sojoteki/library_management_system
├── controller
├── exception
├── service
├── repository
├── model
├── response
├── request_dto
└── enums
```
## Database Configuration

The application uses MySQL.

Default configuration:
```properties
server.port=7777
spring.datasource.url=jdbc:mysql://localhost:3306/library_management_system
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Prefer environment variables for local credentials:
```bash
export DB_URL=jdbc:mysql://localhost:3306/library_management_system
export DB_USERNAME=root
export DB_PASSWORD=your_password
```
See `.env.example` for all supported local variables.

## How to Run

1. Clone the repository
```bash
git clone <repository-url>
cd library-management-system
```
2. Create a MySQL database
```sql
CREATE DATABASE library_management_system;
```
3. Set database credentials with environment variables, or update `src/main/resources/application.properties` for local-only use.
4. Run the application
```bash
./mvnw spring-boot:run
```
The application will start on:
```text
http://localhost:7777
```
## Tests

Tests use an in-memory H2 database through the `test` profile, so they do not require MySQL.
```bash
./mvnw test
```

## Postman

The Postman collection is in `postman/collections/library-management-system`. Requests use the global `{{baseUrl}}`, which defaults to:
```text
http://localhost:7777
```

## API Modules

- Book APIs
- Student APIs
- Card APIs
- Transaction APIs

## Description

This project is a backend application designed to handle core library operations. It provides service-layer logic and REST endpoints for managing books, students, library cards, and transactions such as issuing and returning books.
