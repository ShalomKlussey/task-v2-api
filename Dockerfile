FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/task-1.0.1-SNAPSHOT.jar task.jar
ENTRYPOINT ["java","-jar","/task.jar"]
