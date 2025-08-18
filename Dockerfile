# Stage 1: Build the application using Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set the working directory
WORKDIR /app

# Copy the parent pom.xml and the shared library to leverage Docker layer caching
COPY ../pom.xml ./pom.xml
COPY ../fm-shared-lib ./fm-shared-lib

# Copy the user service source code
COPY ./fm-user-service ./fm-user-service

# Build the entire project from the root to ensure fm-shared-lib is available
# The -pl flag builds only the specified module and its dependencies
RUN mvn -f pom.xml clean install -pl fm-user-service -am

# Stage 2: Create the final, lean image
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the builder stage
COPY --from=builder /app/fm-user-service/target/fm-user-service-*.jar app.jar

# Expose the port the application runs on
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
