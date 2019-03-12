FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
COPY inspectit/*.jar agent.jar
ENTRYPOINT ["java","-javaagent:agent.jar","-jar","/app.jar"]
