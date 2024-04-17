# Use an OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application (JAR file) to the docker image
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Set the entry point for the container, specifies that the default command to run when the container starts
ENTRYPOINT ["java","-jar","/app.jar"]