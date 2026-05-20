# 🎮 Automated Java Delivery Pipeline: A Multi-Stage CI/CD Integration

<p align="center">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker Badge"/>
  <img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white" alt="Jenkins Badge"/>
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white" alt="GitHub Actions Badge"/>
  <img src="https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17 Badge"/>
  <img src="https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven Badge"/>
</p>

---

## 📌 Project Overview

In modern software engineering, manual building processes and host machine dependencies (such as specific Java versions, Classpath configurations, and Maven dependencies) often lead to environment-related deployment failures—commonly known as the **"it works on my machine"** paradox. 

This project addresses these exact challenges by replacing manual build steps with a structured, automated, zero-touch delivery pipeline. Using a console-driven Java Tic-Tac-Toe game as the core application, this ecosystem ensures that every code modification is automatically validated, compiled, encapsulated within an optimized, ultra-lightweight portable container, and distributed globally to an public artifact registry.

---

## 🍕 Project Analogy: The Automated Pizzeria

To understand the core architecture of this DevOps stack, imagine it as an **Automated Pizzeria** designed to prep, bake, box, and distribute identical high-quality pizzas worldwide without human chef intervention:

* **📜 The Recipe (Source Code):** The developer commits code updates to GitHub.
* **🧪 Dough & Sauce Quality Assurance (GitHub Actions):** An automated kitchen gatekeeper instantly tests the ingredients (compiles code and runs unit tests) to ensure everything is perfect.
* **🕹️ The Kitchen Orchestrator (Jenkins):** Once validated, Jenkins pulls the ingredients, coordinates the assembly line, and manages the lifecycle tracking.
* **📦 The Automated Oven & Standardized Box (Docker Multi-Stage Build):** A heavy commercial oven bakes the food (*Maven Build stage*), and passes only the final product into a tiny, standardized delivery box (*lightweight Alpine JRE runtime*).
* **🛸 The Central Warehouse & Drone Fleet (Docker Hub):** The sealed boxes are cataloged into a global warehouse registry, ready for immediate, instant consumption on any device anywhere.

---

## 🛠️ Tools & Technologies Used

| Layer | Technology | Usage Description |
| :--- | :--- | :--- |
| **🚀 CI Gatekeeper** | **GitHub Actions** | Automated validation runner checking system health on code push. |
| **⚙️ Pipeline Engine** | **Jenkins Server** | Orchestrates downstream multi-stage pipeline logic via `localhost:9090`. |
| **🐳 Virtualization** | **Docker Desktop** | Handles structural isolation using a WSL2 virtualization backend. |
| **📦 Build Automation** | **Apache Maven 3.9.6** | Compiles project files, resolves dependencies, and builds the `.jar` package. |
| **☕ Runtime Image** | **Eclipse Temurin** | Combines clean JDK 17 environments with minimalist JRE 17 Alpine layers. |
| **🌐 Artifact Hosting** | **Docker Hub** | Public distribution layer mapping image endpoints to user account `vansharora09`. |

---

## 🏗️ Architecture & Implementation Pipeline (Step-by-Step)

### 🔹 Phase I: Container Blueprinting (`Dockerfile`)
The runtime image leverages a **Multi-Stage Build strategy**. This isolates the heavy compilation dependencies (Maven tools) to a temporary builder stage, copying only the finalized executable `.jar` file into an ultra-small Alpine Linux JRE layer to minimize the production attack surface and container footprint.

```dockerfile
# Stage 1: Build & Package (Using Maven + Eclipse Temurin JDK 17)
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy configuration and source code
COPY pom.xml .
COPY src ./src

# Compile and package into an executable JAR file
RUN mvn clean package -DskipTests

# Stage 2: Minimalist Runtime Environment (Using clean JRE 17 Alpine)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /workspace

# Copy the final JAR artifact from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Command to execute your console application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 🔹 Phase II: Workflow Automation (.github/workflows/ci-validation.yml)
Acts as the automated gatekeeper. Every time a developer executes a push to GitHub, an external cloud runner instantiates, configures JDK 17, and triggers the test suites. If code logic contains bugs, the build pipeline breaks before reaching production.

```YAML
name: Java CI Gatekeeper Validation

on:
  push:
    branches: [ main, master ]
  pull_request:
    branches: [ main, master ]

jobs:
  validate-and-test:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout Source Code
      uses: actions/checkout@v4

    - name: Set up JDK 17 (Eclipse Temurin)
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: 'maven'

    - name: Build and Run Test Suite with Maven
      run: mvn clean test
```

### 🔹 Phase III: Jenkins Orchestration (Jenkinsfile)
Jenkins manages the internal deployment lifecycle using a structured declarative script ("Pipeline-as-Code"). It structures delivery into four unique visualization swimlanes: checking out code, building artifacts, packaging containers, and executing registry uploads.

```Groovy
pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'tictactoe-pipeline-game'
        REGISTRY_USER = 'vansharora09'
    }
    
    stages {
        stage('Automated Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Maven Packaging') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        
        stage('Docker Blueprint Build') {
            steps {
                bat "docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
                bat "docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('Registry Distribution') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    bat "docker login -u ${USER} -p ${PASS}"
                    bat "docker push ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
                }
            }
        }
    }
}
```

### 🚀 Phase IV: System Validation (How to Run Globally)
Because the final package is completely containerized and stored on the public cloud registry, any client machine in the world can instantly pull and execute this game with a single command without needing Java, Maven, or project source code installed locally.

To Run the Interactive Game:
```Bash
docker run -it --rm vansharora09/tictactoe-pipeline-game:latest
```
