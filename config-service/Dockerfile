FROM openjdk:8-jre-alpine
MAINTAINER rostyslavpaliuha@gmail.com
EXPOSE 8345
RUN mkdir -p -m 777 /var/log
ADD target/config-service-0.0.1.jar /config-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/config-service-0.0.1.jar"]