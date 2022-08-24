FROM openjdk:8
EXPOSE 8100
ADD target/*.jar ProcessPensionMicroservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar /ProcessPensionMicroservice-0.0.1-SNAPSHOT.jar"]