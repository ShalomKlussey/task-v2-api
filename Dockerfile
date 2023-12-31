# Use an official Maven image as the base image for building
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the POM file to download dependencies
COPY ./pom.xml .

# Copy the source code
COPY ./src ./src

# Run Maven clean and package
RUN mvn clean package -DskipTests

# Use an official OpenJDK image as the base image for the final image
FROM openjdk:18

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build image to the final image
COPY --from=build /app/target/task-1.0.1-SNAPSHOT.jar ./app.jar

# Expose the port the app runs on
EXPOSE 8081

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]


