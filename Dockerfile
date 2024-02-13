FROM openjdk:21

#RUN apt-get update && apt-get install -y net-tools

COPY ./target/rinha-de-backend-javinha-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]