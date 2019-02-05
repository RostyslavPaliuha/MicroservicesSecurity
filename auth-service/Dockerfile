FROM openjdk:8-jre-alpine
MAINTAINER rostyslavpaliuha@gmail.com
EXPOSE 8081
RUN mkdir -p -m 777 /var/log
ADD target/auth-service-0.0.1.jar /auth-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/auth-service-0.0.1.jar"]