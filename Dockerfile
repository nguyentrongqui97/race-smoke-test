# Use a Java 17 base image (no Maven preinstalled)
FROM eclipse-temurin:17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper files first to leverage Docker layer caching
COPY .mvn/ .mvn/
COPY mvnw .

# Make sure the wrapper is executable
RUN chmod +x mvnw

# Copy the rest of the project files
COPY . .

# Then build using Maven wrapper
RUN ./mvnw clean install

# Run tests
RUN ./mvnw clean test
