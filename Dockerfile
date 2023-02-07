FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY target/demo-spring-boot-app-0.0.1-SNAPSHOT.jar demo-spring-boot-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "/demo-spring-boot-app-0.0.1-SNAPSHOT.jar" ]