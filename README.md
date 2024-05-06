## Code Challenge – Digital Factory Peru – Scotiabank

### Description
The project involves the development of a microservice that exposes two reactive REST services to manage student information as follows:

- **Record a student:**
  - A request is expected to record a student with attributes such as id, name, last name, status (active or inactive), and age. The system performs thorough validation of each field to ensure data consistency before proceeding with the student's recording.
  - If the Id is duplicated, an error message is issued indicating that the recording cannot be performed.
  - If the recording is successful, the service responds with an empty message confirming the operation.
- **Get active students:**
    - This service returns a list of all students who have an active status. 
    - The project is developed following a Layered Architecture.

To ensure data persistence, R2DBC is employed along with H2 Database, which functions as an in-memory database. R2DBC allows reactive access to the database, improving the scalability and performance of the application.

Unit tests were included to ensure the reliability of the developed code. Test coverage was thoroughly evaluated to ensure software quality.

### Specifications
- [Spring Boot 2.7.7](https://start.spring.io/)
- JDK 11
- Gradle - Groovy
- Spring WebFlux
- R2DBC + H2

### Reference Documentation
For further reference, please consider the following sections:

* [API Contract](https://eber.stoplight.io/docs/dev/api-student-management)
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#web.reactive)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#data.sql.r2dbc)
* [Validation](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [R2DBC Homepage](https://r2dbc.io)

