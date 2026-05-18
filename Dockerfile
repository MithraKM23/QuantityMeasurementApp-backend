FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR file from target folder
COPY target/*.jar app.jar

# Expose backend port
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
