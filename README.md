# 🧩 Recordings - websocket based full-stack application

A full-stack application with a WebSocket-based Java backend and a Vue 3 frontend.  
This repository contains two separate parts:

- `server/`: Spring Boot backend with WebSocket support
- `client/`: Vue 3 frontend using Vite

---

## 📚 Table of Contents

- [Requirements](#-requirements)
- [Environment Variables](#-environment-variables)
- [Running the App](#-running-the-app)
  - [Option 1: Manual Startup](#-option-1-manual-startup)
  - [Option 2: Docker Compose](#-option-2-docker-compose)

  ---

## 📦 [Requirements](#-requirements)

Make sure you have the following installed:

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

## ⚙️ [Environment Variables](#-environment-variables)

### Backend (`server/`)

Configuration is handled via `application.properties`.  
A sample config file is available as:  
📄 `server/src/main/resources/application.example.properties`

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
📄 `client/.env.example`

Copy it to `.env` and set the correct backend WebSocket URL:

```properties
VITE_WEBSOCKET_ENDPOINT=ws://localhost:8080/ws
```

## 🚀 [Running the App](#-running-the-app)

You can start the app either manually or using Docker Compose.

Set up the [environment variables](#-environment-variables) for both options.

---

### 🧭 [Option 1: Manual Startup](#-option-1-manual-startup)

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

### 🐳 [Option 2: Docker Compose](#-option-2-docker-compose)

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