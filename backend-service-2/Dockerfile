FROM openjdk:8-jre-alpine
MAINTAINER rostyslavpaliuha@gmail.com
EXPOSE 8083
RUN mkdir -p -m 777 /var/log
ADD target/backend-service-2-0.0.1.jar /backend-service-2-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/backend-service-2-0.0.1.jar"]