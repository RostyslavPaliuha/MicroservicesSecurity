FROM openjdk:8-jre-alpine
MAINTAINER rostyslavpaliuha@gmail.com
EXPOSE 8080
RUN mkdir -p -m 777 /var/log
ADD target/gateway-service-0.0.1.jar /gateway-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/gateway-service-0.0.1.jar"]