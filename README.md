# Book_cms

book_cms is a simple  document and book management tool. For those who like reading or have to deal with books and documents frequently, it is very helpful. This app is developed by using Spring Boot, Thymeleaf, Spring Data JPA, H2 (also MySQL) .


## Usage

To use this app locally, you can package it to an executable jar file using maven and then run it. 

```cmd
$ cd project
$ mvn clean package
$ java -jar target/book-0.0.1-SNAPSHOT.jar
```

Dockerfile is included, therefore you can also  run the app using docker in container.

```cmd
$ cd project
# first build docker image
$ docker build -t book-cms:1.0 .
# start a docker container run the image
$ docker run --rm -d -p 8080:8080 -t book-cms:1.0
```


