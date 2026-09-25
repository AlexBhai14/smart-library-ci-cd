# Smart Library Management System - CND


A Java/Spring Boot microservices starter project for a cloud-native library system.

## Features
- Public landing page: search/view books and availability
- Admin login
- Admin manages books and members
- Admin issues/returns books
- Return due date is automatically 7 days after issue
- Docker Compose for local container orchestration
- Kubernetes manifests
- GitHub Actions CI/CD workflow
- PostgreSQL database per service (Compose uses separate databases)

## Prerequisites
- Java 17+
- Maven 3.9+
- Docker + Docker Compose
- Kubernetes (`kubectl`) for deployment
- Docker Hub account for CI/CD image publishing

## Local run without Docker
Build all services:
```bash
mvn -q -DskipTests package
```

Run individual Spring Boot jars as needed.

## Docker Compose
```bash
docker compose up --build
```

Frontend: http://localhost:8090
API Gateway: http://localhost:8091

Demo admin:
- username: admin
- password: admin123

The demo authentication is intentionally simple for an academic starter. For production, replace it with Spring Security/JWT and externalized secrets.

## Kubernetes
Update image names in `k8s/*.yaml` to your Docker Hub repository, then:
```bash
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/secrets.yaml
kubectl apply -f k8s/
```

For a local cluster such as Minikube:
```bash
minikube service library-frontend -n smart-library
```

## GitHub Actions
Set these repository secrets:
- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`

The workflow builds, tests, publishes six images, and applies Kubernetes manifests when configured with a cluster context. The deployment step is guarded by `KUBE_CONFIG_DATA`; if absent, CI still completes through image publishing.

## Services
- frontend-service
- api-gateway
- auth-service
- book-service
- member-service
- issue-return-service
