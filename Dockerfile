FROM openjdk:17

LABEL maintaniner = "Dawid Zapotoczny"

EXPOSE 8080

WORKDIR /usr/local/bin

COPY target/ws-java-app.jar webapp.jar

CMD ["java", "-DSpring.profiles.active=docker", "-jar", "webapp.jar"]