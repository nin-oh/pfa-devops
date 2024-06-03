# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/revormclass-0.0.1-SNAPSHOT.jar revormclass.jar

# Expose the application port
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "revormclass.jar"]
