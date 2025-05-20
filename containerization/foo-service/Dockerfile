FROM amazoncorretto:17
# Set the working directory
WORKDIR /app
# Copy the jar file into the container
COPY target/foo-service-0.0.1-SNAPSHOT.jar foo-service.jar  
# Expose the port the app runs on
EXPOSE 8080
# Run the jar file
ENTRYPOINT ["java", "-jar", "foo-service.jar"]