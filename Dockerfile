FROM adoptopenjdk/openjdk11-openj9:alpine-slim

WORKDIR /app

COPY target/droneapp-0.0.1-SNAPSHOT.jar /app/droneapp-0.0.1-SNAPSHOT.jar

EXPOSE 8086

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:mydb

CMD [ "java", "-jar", "droneapp-0.0.1-SNAPSHOT.jar" ]
