FROM openjdk:8-jre-alpine
MAINTAINER rostyslavpaliuha@gmail.com
EXPOSE 8761
RUN mkdir -p -m 777 /var/log
ADD target/discovery-service-0.0.1.jar /discovery-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "discovery-service-0.0.1.jar"]

