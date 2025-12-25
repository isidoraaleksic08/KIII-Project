# KIII Project-Book Management Application
This is a project, which includes a book management application from EMT course with three main components: frontend, backend, and database.
## Project Structure
- **backend/** - Spring Boot application with REST API
- **frontend/** - React application for the user interface
- **db-init/** - SQL scripts for database creation and initialization
## The application is fully dockerized and can be started using Docker or Docker Compose.
## CI/CD
The project contains a GitHub Actions workflow that automatically builds and uploads Docker images for the backend and frontend.
## Kubernetes
The application is also deployed using Kubernetes in a dedicated namespace.
The Kubernetes setup includes:
- Deployments for backend and frontend
- ConfigMaps and Secrets for configuration
- StatefulSet for the PostgreSQL database
- Services for internal communication
- Ingress for routing traffic to frontend (/) and backend (/api)

All manifests are located in the k8s/ directory and can be applied with:
- kubectl apply -f k8s/

