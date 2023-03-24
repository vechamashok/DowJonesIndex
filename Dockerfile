FROM openjdk:11-jre-slim
COPY target/DowJonesIndex-0.0.1-SNAPSHOT.jar DowJonesIndex.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","DowJonesIndex.jar"]
