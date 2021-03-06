# Book_cms

book_cms is a simple  document and book management tool. For those who like reading or have to deal with books and documents frequently, it is very helpful. This app is developed by using Spring Boot, Thymeleaf, Spring Data JPA, H2 (also MySQL) .


## Usage

To use this app locally, you can package it to an executable jar file using maven and then run it. 

```cmd
$ cd project
$ mvn clean package
$ java -jar target/book-0.0.1-SNAPSHOT.jar
```
Default configuration file use H2 as database. When the app run successfully, you can access the database via http://localhost:8080/h2 and connect it using the following 
```
username: root
password: 123456
```
A default account is also stored in the H2 database to login the app. 
```
username: admin
password: admin
```


Dockerfile is included, therefore you can also  run the app using docker in container. First, you need to build an docker image using following command:

```cmd
$ cd project
$ docker build -t book-cms:1.0 .
```

After the image is successful builded, you start a docker container to run it using the following command:

```cmd
 $ docker run --rm -d -p 8080:8080 -t book-cms:1.0
```





