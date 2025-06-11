FROM eclipse-temurin:17

# Create a new user and group
RUN groupadd -r appgroup && useradd -m -g appgroup appuser

# Set workdir and change owner
WORKDIR /app
COPY . /app
RUN chown -R appuser:appgroup /app

# Switch to the new user
USER appuser

# Run the Maven wrapper
RUN ./mvnw clean install

# Run tests
RUN ./mvnw clean test
