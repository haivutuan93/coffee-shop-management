FROM openjdk:11-jre-slim-buster
# Refer to Maven build -> finalName
ARG JAR_FILE=target/user-0.0.1-SNAPSHOT.jar
# cd /opt/app
WORKDIR /opt/app
# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Dserver.port=8085","app.jar"]
