FROM openjdk:14-jdk-alpine
ARG JAR_FILE=target/itemservice-latest.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080/TCP