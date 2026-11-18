CommercePlatform — AI Agent Guidelines

1. Project Overview

CommercePlatform is a modular monolith built with:

- Java 21
- Spring Boot 4
- Maven
- Spring MVC
- Spring Data JPA / Hibernate
- MySQL
- JUnit 5
- Mockito
- Springdoc OpenAPI

The project is organized by business capability:

- productService
- userService
- orderService

The application is a single Spring Boot application. These modules are NOT independent microservices.



2. Agent Workflow

For non-trivial tasks, follow this workflow:

Step 1 - Inspect

Inspect the relevant existing code before making changes:

Identify:

 - affected module
 - affected layers
 - dependencies
 - existing patterns
 - relevant tests
