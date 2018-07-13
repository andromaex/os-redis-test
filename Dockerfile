FROM library/openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ARG REDIS_HOSTNAME
EXPOSE 8080 8081
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-Djava.security.egd=file:/dev/./urandom","-Dredis.hostname=${REDIS_HOSTNAME}","-jar","/app.jar"]