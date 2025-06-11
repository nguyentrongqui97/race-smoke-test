# Use official Maven image with Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Clean only the corrupted JAR file first
RUN rm -f /root/.m2/repository/org/aspectj/aspectjweaver/1.9.22.1/aspectjweaver-1.9.22.1.jar

# Fully purge the artifact
RUN mvn dependency:purge-local-repository \
    -DmanualInclude="org.aspectj:aspectjweaver" \
    -DreResolve=true \
    -Dmaven.repo.local=/root/.m2/repository

# Force Maven to re-download all dependencies
RUN mvn dependency:resolve -Dmaven.repo.local=/root/.m2/repository

# Then build
RUN mvn clean install


# Build the project and run tests
RUN mvn clean test

