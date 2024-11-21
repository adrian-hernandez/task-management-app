# Taskify

A simple task management application built with Spring Boot (backend) and Angular (frontend). The application allows users to create, read, update, and delete tasks with an in-memory database using MapDB.

## Features

- Create new tasks with title and description
- List all tasks
- Mark tasks as completed
- Delete existing tasks
- In-memory data persistence using MapDB
- RESTful API backend
- Single-page application frontend

## Tech Stack

### Backend
- Java 17
- Spring Boot 3.3.5
- MapDB 3.0.10 (in-memory database)
- Maven

### Frontend
- Angular 17
- TypeScript
- HTML5/CSS3

## Prerequisites

- Java 17 or higher
- Node.js and npm
- Maven

## Setup and Installation

1. Clone the repository:
```bash
git clone https://github.com/your-repo/task-management-app.git
```
2. Install Node.js dependencies: 
```bash
cd taskify
npm install
```
3. Start the frontend development server:
```bash
ng serve frontend
```
4. In a separate terminal, start the backend:
```bash
mvn spring-boot:run
```

The frontend will be available at `http://localhost:4200`
The backend API will be available at `http://localhost:8080`

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/tasks` | Get all tasks |
| POST | `/tasks` | Create a new task |
| PUT | `/tasks/{id}` | Update a task |
| DELETE | `/tasks/{id}` | Delete a task |

### Request/Response Examples

#### Create Task
```json
POST /tasks
{
"title": "Task Title",
"description": "Task Description",
"completed": false
}
```

## Development

### Frontend Development
- The frontend code is located in `taskify/projects/frontend/src/app/`
- Run `ng serve frontend` to start the development server
- Changes will automatically reload

### Backend Development
- The backend code is in `src/main/java/com/example/task_management_app/`
- Run tests using: `mvn test`

## Testing

The project includes unit tests for the service layer. Run the tests using:
```bash
mvn test
```

## Data Persistence

The application uses MapDB for in-memory data storage. Note that data will be lost when the application restarts.