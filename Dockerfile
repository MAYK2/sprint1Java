FROM gradle:8.7-jdk17-alpine

COPY . .

RUN gradle build

EXPOSE 8080


#ojalafunque
ENTRYPOINT ["java", "-jar", "build/libs/homebanking-0.0.1-SNAPSHOT.jar"]