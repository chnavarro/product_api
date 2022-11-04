# Project Title

Product API

## Description

Develop a service environment that allows exposing a REST API for consumption by a client.

## Stack
    * Spring Boot 2.6
        * Spring Security
        * JPA
        * JWT
        * Flyway DB
        * Swagger (Open API)
        * MS SQL Server

## Getting Started

### Dependencies

* MS SQL Server 2019+
* Java JDK 1.8
* Gradle
* A favorite text editor or IDE
* Insomnia

### DB Instructions

* For initialize the database you need to run the script **sql/init_db.sql**

* On resources/application.properties you need to change the database parameter
```
spring.datasource.url=jdbc:sqlserver://localhost\\SQLEXPRESS:1433;database=product_api_db
spring.datasource.username=apiusr
spring.datasource.password=strongPassword

....

spring.flyway.url = jdbc:sqlserver://localhost\\SQLEXPRESS:1433;database=product_api_db
spring.flyway.schemas = product_api_db
spring.flyway.user = apiusr
spring.flyway.password = strongPassword
```

* The tables and others DB objects will be built by **Flywaydb** on application initialization.

* The data is auto-populated by the application start-up.

### Executing program

* How to run the program
```
gradle bootRun
```

**Note:** The application is running on port **8080**, so you can check on `http://localhost:8080`

### Test program

* Using Insomnia, import the file **Insomnia_product_api** in the root folder.
* Also, you can use **Swagger** to explore and test, just got to [swagger-ui](http://localhost:8080/swagger-ui)

## Authors

Contributors name and contact info

* Carlos Navarro [@cnavarro](https://github.com/chnavarro)

## Version History

* 1.0
    * Initial Release

## Acknowledgments

Inspiration, code snippets, etc.
* [spring-boot](https://spring.io/guides/gs/spring-boot/)
