# Recordings - websocket based full-stack application

A full-stack application with a WebSocket-based Java backend and a Vue 3 frontend.  
This repository contains two separate parts:

- `server/`: Spring Boot backend with WebSocket support
- `client/`: Vue 3 frontend using Vite

---

## Table of Contents

- [Requirements & Used Technologies](#-requirements--used-technologies)
- [Environment Variables](#-environment-variables)
- [Running the App](#-running-the-app)
  - [Option 1: Manual Startup](#-option-1-manual-startup)
  - [Option 2: Docker Compose](#-option-2-docker-compose)
- [Technical Architecture & Design Decisions](#-technical-architecture--design-decisions)
- [Running Tests](#-running-tests)

## Requirements & Used Technologies

To run or contribute to this project, make sure you have the following installed.  
This list also reflects the core technologies used in the application.

| Tool        | Version         |
|-------------|------------------|
| Git         | Any recent version |
| Java        | 17               |
| Spring Boot | 3.4.4            |
| Node.js     | 18+              |
| NPM         | latest compatible with Node 18 |
| Vite        | 6.2.1            |
| Vue.js      | 3.5.13           |

---

## Environment Variables

### Backend (`server/`)

Configuration is handled via `application.properties`.  
A sample config file is available as:  
`server/src/main/resources/application.example.properties`

You can copy it to `application.properties` and customize it as needed:

```properties
# Logging config
logging.level.org.springframework=INFO

# H2 database connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (optional)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Frontend (`client/`)

The frontend requires a WebSocket endpoint to communicate with the backend.

A sample environment file is available as:
`client/.env.example`

Copy it to `.env` and set the correct backend WebSocket URL:

```properties
VITE_WEBSOCKET_ENDPOINT=ws://localhost:8080/ws
```

## Running the App

You can start the app either manually or using Docker Compose.

Set up the [environment variables](#-environment-variables) for both options.

---

### Option 1: Manual Startup

**Backend (Spring Boot)**  
Navigate to the `server/` directory and run:

- `./mvnw spring-boot:run`  
  *or* run `Application.java` from your IDE.

The backend will be available at:  
[http://localhost:8080](http://localhost:8080)

If the H2 console is enabled, access it at:  
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

**Frontend (Vue 3 + Vite)**  
Navigate to the `client/` directory and run:

- `npm install`  
- `npm run dev`

The frontend will be available at:  
[http://localhost:5173](http://localhost:5173)

---

### Option 2: Docker Compose

Make sure you have Docker and Docker Compose installed.

From the project root, run:
```bash
docker-compose up --build
```

This will:
- Build and start the backend (Spring Boot)
- Build and start the frontend (Vite)

The app will be available at:  
- Frontend: [http://localhost:5173](http://localhost:5173)  
- Backend: [http://localhost:8080](http://localhost:8080)

To stop the containers:  
```bash
docker-compose down
```

To also remove volumes (e.g. if switching DB setup):  
```bash
docker-compose down -v
```

## Technical Architecture & Design Decisions

This project was designed with simplicity and demonstrability in mind. Below are the key architectural choices:

### WebSocket-based Communication

The UI (Vue 3 frontend) communicates with the backend (Spring Boot) using **WebSocket**, utilizing the **STOMP protocol** on top.

- This enables real-time, bidirectional communication.
- STOMP provides a lightweight message format and supports topics/subscriptions.
- _(Note: In most cases — including this one — REST would have been a simpler and more straightforward choice.)_

### In-Memory H2 Database

The backend uses an **H2 in-memory database**, which is:

- Lightweight and requires no external setup.
- Automatically populated with a small amount of test/demo data on startup.
- Ideal for quick demos or proof-of-concept implementations.
- _(Note: All data is lost when the application stops — it's not intended for production use.)_

### No Authentication or Authorization

To keep the architecture simple and focused on core features, **authentication and authorization were deliberately left out**.

- In a real-world scenario, user access control would be essential.
- This project focuses on technical interactions rather than security layers.

### UI Framework: PrimeVue

The frontend uses **[PrimeVue](https://primevue.org/)** as its component framework.

- Provides a wide range of pre-built, accessible UI components.
- Accelerates development by reducing the need for custom styling.
- Used primarily for status tags, buttons, layout elements, and form controls.

### Frontend State Management

The frontend uses **local state management** to:

- Track and organize domain entities.
- Optimize ongoing processes (e.g., workflows or interactive states).
- Avoid redundant API/WebSocket calls and improve responsiveness.

## Running Tests

This project includes both backend and frontend unit tests.

---

### Backend (JUnit + Mockito)

To run backend tests locally:

```bash
./mvnw test
```

---

### Frontend (Vitest)

Navigate to the `client/` directory and run:

```bash
npm install
npm run test
```