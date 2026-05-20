# Stage 1: Build & Package (Using Maven + Eclipse Temurin JDK 17)
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy configuration and source code
COPY pom.xml .
COPY src ./src

# Compile and package into an executable JAR file
RUN mvn clean package -DskipTests

# Stage 2: Minimalist Runtime Environment (Using clean JRE 17)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /workspace

# Copy the final JAR artifact from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Command to execute your console application
ENTRYPOINT ["java", "-jar", "app.jar"]
