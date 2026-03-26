# ready image that have Java
FROM eclipse-temurin:21-jdk

# in the container, work inside app folder
WORKDIR /app

# copy the jar file (the project file)
COPY build/libs/aitu-events-backend-0.0.1-SNAPSHOT.jar app.jar

# when the container starts, it runs the applicication
ENTRYPOINT ["java", "-jar", "app.jar"]