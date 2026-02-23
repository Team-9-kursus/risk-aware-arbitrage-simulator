```md
# Step 2: Deployment (Academic / Manual)

> This deployment is intended for **academic demonstration only**.
> No production guarantees, security hardening, or real trading usage.

---

## Overview

Deployment was done using:
- Docker
- Docker Compose
- A Linux server (Ubuntu)
- Multi-stage Docker builds (no Maven required on server)

All services run as containers:
- Backend (Spring Boot)
- Frontend (React)
- Nginx (reverse proxy)

---

## Server Location

Project is deployed under:

```

/var/www/risk-aware-arbitrage-simulator

```

Structure on the server:
```

/var/www/risk-aware-arbitrage-simulator
├── backend/
│   └── Dockerfile
├── frontend/
│   └── Dockerfile
├── infra/
│   └── nginx/
│       └── default.conf
├── docker-compose.yml
└── *.md (documentation files)

```

---

## Step-by-Step Deployment

### 1. Upload Project to Server

From local machine:
- Project was packaged into `deploy.tar`
- Excluded build artifacts (`backend/target`, `node_modules`, etc.)
- Uploaded via `scp`
- Extracted to `/var/www/risk-aware-arbitrage-simulator`

---

### 2. Backend Docker Setup (Important)

The backend **does NOT require Maven installed on the server**.

`backend/Dockerfile` uses a **multi-stage build**:

**Build stage**
- Uses `maven:3.9.9-eclipse-temurin-17`
- Runs `mvn -DskipTests package`
- Produces JAR inside Docker

**Runtime stage**
- Uses `eclipse-temurin:17-jre`
- Copies built JAR
- Runs Spring Boot app

This ensures:
- Clean builds
- No server-side Java tooling needed

---

### 3. Docker Compose Configuration

Main file:
```

docker-compose.yml

````

Services:
- `backend`:  Spring Boot on port `8080`
- `frontend`: React dev server on port `3000`
- `nginx`: Reverse proxy

Because the server already had system nginx on port 80, **Docker nginx was mapped to port 8081**:

```yaml
nginx:
  ports:
    - "8081:80"
````

---

### 4. Start the Application

From server:

```bash
cd /var/www/risk-aware-arbitrage-simulator
docker compose up --build -d
```

This:

* Builds backend and frontend images
* Starts all containers in background

---

## Verification

### Running containers

```bash
docker ps
```

Expected containers:

* `riskaware-arbitrage-backend`
* `riskaware-arbitrage-frontend`
* `riskaware-arbitrage-nginx`

---

### Backend health check

```bash
curl http://localhost:8080/actuator/health
```

Expected response:

```json
{"status":"UP"}
```

---

### Frontend / Nginx

Open in browser:

```
http://<server-ip>:8081
```

---

## Summary

✔ Backend and frontend built via Docker
✔ No Maven required on server
✔ Nginx port conflict resolved (8081)
✔ Deployment fully reproducible
✔ Suitable for academic environment

---

## Notes

* `version:` field in `docker-compose.yml` is obsolete but harmless
* Port `8081` is intentional due to server constraints
* All changes are documented and scoped to deployment only

```