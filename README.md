# 🧩 Recordings - websocket based full-stack application

A full-stack application with a WebSocket-based Java backend and a Vue 3 frontend.  
This repository contains two separate parts:

- `server/`: Spring Boot backend with WebSocket support
- `client/`: Vue 3 frontend using Vite

---

## 📦 Requirements

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

## ⚙️ Environment Variables

### Backend (`server/`)

Configuration is handled via `application.properties`.  
A sample config file is available as:  
📄 `server/src/main/resources/application.example.properties`

You can copy it to `application.properties` and customize it as needed:

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

### Frontend (`client/`)

The frontend requires a WebSocket endpoint to communicate with the backend.

A sample environment file is available as:
📄 `client/.env.example`

Copy it to `.env` and set the correct backend WebSocket URL:

VITE_WEBSOCKET_ENDPOINT=ws://localhost:8080/ws