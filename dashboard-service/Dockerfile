FROM openjdk:8-jre-alpine
MAINTAINER rostyslavpaliuha@gmail.com
EXPOSE 9000
RUN mkdir -p -m 777 /var/log
ADD target/dashboard-service-0.0.1.jar /dashboard-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/dashboard-service-0.0.1.jar"]