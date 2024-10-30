# Use an official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build files and project source code to the container
COPY . /app

# Copy the built JAR file to the container
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose the port that the app will run on
EXPOSE 8081

# Run the JAR file with the specified port
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8081"]
