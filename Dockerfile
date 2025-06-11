FROM eclipse-temurin:17

# Create app user
RUN groupadd -r appgroup && useradd -m -g appgroup appuser

# Set working directory
WORKDIR /app

# Copy project files
COPY . /app
RUN chown -R appuser:appgroup /app

# Switch to app user
USER appuser

# Remove corrupted JAR before build
RUN rm -rf /home/appuser/.m2/repository/org/aspectj/aspectjweaver/1.9.22.1

# Run Maven wrapper to clean and install with forced dependency updates
RUN ./mvnw clean install -U

# Run tests
RUN ./mvnw clean test
