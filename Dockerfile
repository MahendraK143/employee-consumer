# FROM openjdk:8-jdk-alpine
FROM openjdk:17-ea-32-jdk-slim
VOLUME /tmp
EXPOSE 8080
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
# ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
ENTRYPOINT [ "java" "-jar" "/app.jar" ]