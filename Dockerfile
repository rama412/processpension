FROM openjdk:8
EXPOSE 8100
ADD target/processpension-microservice.jar Authorization-Microservice.jar
ENTRYPOINT ["java","-jar","/processpension-microservice.jar"]