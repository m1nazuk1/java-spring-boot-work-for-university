# java-spring-boot-work-for-university
university task (java, spring, docker, apache kafka)

This work consists of two tasks: "mandatory", which everyone must complete, and "additional", for which you can get extra points for the semester.
Mandatory part:
• Write an application (microservice) using Spring Boot. Your application must have at least three entities of your choice.

• For each of the entities, you must write a RestController containing methods that handle HTTP GET, POST, PUT, DELETE requests.

• To store information, your application must interact with any relational database raised in a docker container.

• Your database schema must be poured using Liquibase.

• To work with the database, the corresponding repositories must be added to your application. In this case, work with the repository must be carried out from a service (a specialized class with the @Service annotation). The controller must not have dependency injection of the repository.

• Add at least three methods to your repositories that will execute queries with conditions. One of them should be written using JPQL.

• Add a transaction method to your application. You need to justify your choice, and not hang a transaction on the first method you come across.

Eligible part:
Goal: set up asynchronous interaction between services using Kafka.
The basis (source microservice) is what you did in the mandatory
part.
• Raise Apache Kafka in Docker or install it locally and everything necessary for its
operation.
• Create a second microservice with the minimum necessary set of classes.
• Set up asynchronous interaction between services via Kafka topics.
• Your source microservice sends a message to the command topic > The new
microservice reads the message and sends a response to the response topic > The source
microservice reads the response and outputs information to the console/lot.
What's the output: Two topics in Kafka, through which your two microservices communicate.
