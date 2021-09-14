FROM adoptopenjdk:11-jre-hotspot

ENV TZ=America/Sao_Paulo
EXPOSE 8080
WORKDIR /app

COPY ./build .

CMD ["java", "-jar", "/app/garbus-backend.war"]
