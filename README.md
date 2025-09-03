# Offline-First Task Management API

A robust backend API for a personal task management application, specifically designed with an offline-first architecture. This project addresses the challenge of providing a seamless user experience for individuals in areas with intermittent internet connectivity, such as on trains or in remote locations in India.

The application allows users to create, update, and delete tasks while offline. When connectivity is restored, the app efficiently syncs these changes to the server, intelligently handling potential data conflicts.

# Core Features
1. Standard REST API: Full CRUD (Create, Read, Update, Delete) functionality for managing tasks when online.

2. Batch Synchronization: A single, powerful endpoint (/api/sync/batch) to process all offline changes at once, minimizing network requests.

3. Offline-First Design: Built on the principle that the client is the source of truth. The client generates its own UUIDs and timestamps, which is critical for offline functionality.


# Technology Stack
1. Backend: Java 17, Spring Boot 3.5.5

2. API: Spring Web (REST)

3. Database: Spring Data JPA, Hibernate

4. Development DB: H2 In-Memory Database

5. Build Tool: Apache Maven

5. Utilities: Lombok



# API Endpoints Documentation
**Standard Task API (/api/tasks)**

These endpoints are for online usage

| Method | Endpoint | Description | Sample Body |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/tasks` | Create a new task. | `{ "id": "uuid", "title": "New Task", ... }` |
| `GET` | `/api/tasks` | Get all non-deleted tasks. | (None) |
| `GET` | `/api/tasks/{id}` | Get a single task by its UUID. | (None) |
| `PUT` | `/api/tasks/{id}` | Update an existing task. | `{ "id": "uuid", "title": "Updated Task", ... }` |
| `DELETE` | `/api/tasks/{id}` | Soft delete a task. | (None) |


# Offline Sync API (/api/sync)

This is the core endpoint for offline functionality.

POST /api/sync/batch

Processes a batch of changes made by the client while offline.

URL: http://localhost:8080/api/sync/batch

Description: The client sends an array of all tasks that were created, updated, or deleted offline. The server processes each one, resolving conflicts as it goes.


# Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── TaskManager
│   │           ├── Controller   # API Endpoints
│   │           ├── DTO          # Data Transfer Objects
│   │           ├── Exception    # Global Exception Handling
│   │           ├── Model        # JPA Database Entities
│   │           ├── Repository   # Spring Data JPA Interfaces
│   │           └── Service      # Business Logic
│   └── resources
│       └── application.properties # Configuration
└── test
```






