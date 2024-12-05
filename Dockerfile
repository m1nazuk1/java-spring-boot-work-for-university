FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/seminar4ik-0.0.1-SNAPSHOT.jar seminar4ik.jar

ENTRYPOINT ["java", "-jar", "seminar4ik.jar"]

EXPOSE 8080