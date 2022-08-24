FROM openjdk:8
EXPOSE 8100
ADD target/*.jar processpension-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar /processpension-microservice-0.0.1-SNAPSHOT.jar"]