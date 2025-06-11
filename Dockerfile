# Use official Maven image with Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .


# Then build
RUN mvn clean install


# Build the project and run tests
RUN mvn clean test

