FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /myapp
ARG JAR_FILE=target/book-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} book.jar
EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "book.jar"]