
# Up! Framework Spring Boot Demo

## Try it out.

This example is running on [heroku](https://up-demo-spring-boot.herokuapp.com/)

## Getting started

**This project requires Java 8 to run due to the version of [spring boot used](https://stackoverflow.com/questions/52385610/java-class-cast-exception-spring-boot?rq=1)**

* clone this repository
* ```cd up-demo-spring-boot``` 
* ```mvn spring-boot:run``` 
* ```open http://localhost:8080/universities```  

## Explore Up! Test Framework

Take a look at [CourseServiceTest](./src/test/java/com/github/restup/example/CourseServiceTest.java) and the files
it uses for: 
* [database initialization](./src/test/resources/com/github/restup/example/CourseServiceTest/dumps) using dbunit
* Request [bodies](./src/test/resources/com/github/restup/example/CourseServiceTest/requests) posted by Up!
* [response](./src/test/resources/com/github/restup/example/CourseServiceTest/responses) assertions made using json-unit
* [database assertions](./src/test/resources/com/github/restup/example/CourseServiceTest/results) using dbunit

Take a look at [CourseServiceJsonAPITest](./src/test/java/com/github/restup/example/CourseServiceJsonAPITest.java) and its test resources.
Note that the test method name is used as the test naming convention, removing the redundant double book keeping
of test names.
