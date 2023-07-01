# 42 Spring Oauth2 Login


## Description

This project implements the OAuth2 authentication from the 42 school platform in Spring Boot. Using technologies like Java, Spring Boot, and Spring Security, the project enables users to authenticate with their 42 credentials and access profile information. It provides an easy and secure way to integrate 42 authentication into Spring applications.
<br>
This project is heavily inspired in [Belnut](
https://github.com/Belnut/42_springboot_login_example) implementation.

## Technologies

* [Java 20](https://www.oracle.com/java/technologies/javase-downloads.html) - Java SE Development Kit 20
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* [Spring Security 6](https://spring.io/projects/spring-security) - Spring Security is a powerful and highly customizable authentication and access-control framework. 
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](https://www.h2database.com/html/main.html) - Java SQL database
* [Lombok](https://projectlombok.org/) - Java library to reduce boilerplate code
* [Thymeleaf](https://www.thymeleaf.org/) - Java template engine for processing and creating HTML, XML, JavaScript, CSS, and text


## System Requirements

* [Java 20](https://www.oracle.com/java/technologies/javase-downloads.html) - Java SE Development Kit 20
* [Maven](https://maven.apache.org/) - Dependency Management

## Installation

To run this project clone this repository and run the following commands:

```shell
mvn clean install
```

This command will build the project and download the required dependencies.

```shell
mvn spring-boot:run
```

This command will run the project. Navigate to http://localhost:8080/api/v1/callback to see the project running.
you also can use `./mvnw spring-boot:run` to run the project.


## Usage

* Register App in https://intra.42.fr
* Set up `UID42`, `SECRET42` environment variables, used in `resources/application.yaml`
* Run app, check http://localhost:8080/api/v1/callback to authenticate with 42 OAuth2
* Check http://localhost:8080/api/v1/welcome to see user info

## Example


![Captura de tela_2023-07-01_00-19-31](https://github.com/andersonhsporto/42-oauth2-spring/assets/47704550/6f4c9869-945a-4b9d-a76f-5794b1d99d52)




## Contact Information

If you have any questions, suggestions, or critiques, please contact me using [email](mailto:anderson.higo2@gmail.com)
or through [LinkedIn](https://www.linkedin.com/in/andersonhsporto/).

