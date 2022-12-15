# gp
Microservice developed in Java 8 with Spring Boot v2.7.7-SNAPSHOT to solve the following problems:

## Technical evaluation test:
### The problem - Car rental system



## Technology Stack

### Overview

|Technology                |Description         |
|--------------------------|--------------------|
|<a href="https://spring.io/projects/spring-boot">Spring Boot</a>  |Framework to ease the bootstrapping and development of new Spring Applications|
|Security Framework                                                |Spring Security, JWT|
|Persistent Layer Framework|Spring Data JPA                        |
|<a href="https://www.h2database.com/html/main.html">H2 Database Engine</a>|Java SQL database. Embedded and server modes; in-memory databases|
|<a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">JDK 8</a>|Java™ Platform, Standard Edition Development Kit |
|<a href="https://maven.apache.org/">Maven</a>   |Dependency Management|
|<a href="https://projectlombok.org/">Lombok</a> |Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.|
|<a href="https://swagger.io/">Swagger</a>       |Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.           |

## Technical improvements to be made
<details open="open">
   <ul>
      <li>[ ] Multiple Databases, to separate users and passwords from the rest of the car information.</li>
      <li>[ ] Unit Tests, Integration Tests.</li>
      <li>[ ] Multitenancy, to provide support to different companies.</li>
      <li>[ ] <a href="https://www.javainuse.com/spring/springboot-microservice-elk">Add ELK.</a></li>
      <li>[ ] <a href="https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html">Spring Feign. </a></li>
      <li>[ ] <a href="https://spring.io/projects/spring-security">Spring Security</a> RBAC, Session Timeout.</li>
      <li>[ ] <a href="https://docs.spring.io/spring-data/jpa/docs/2.7.x/reference/html/#jpa.auditing">JPA Auditing via AuditorAware Interface.</a></li>
      <li>[ ] <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-profiles">Spring Profiles</a> (production, qa, staging, test).</li>
      <li>[ ] <a href="https://www.docker.com/">Docker.</a></li>
      <li>[ ] <a href="https://docs.spring.io/spring-boot/docs/2.7.7-SNAPSHOT/reference/html/io.html#io.caching">Caching.</a></li>
      <li>[ ] <a href="https://www.jsonwebtoken.io/">JSON Web Token</a> based authentication.</li>
      <li>[ ] <a href="https://docs.spring.io/spring-cloud-config/docs/current/reference/html/">Spring Cloud Config Server</a>, to centralize microservices configuration.</li>
      <li>[ ] <a href="https://spring.io/projects/spring-cloud-gateway">Spring Cloud Gateway.</a></li>
      <li>[ ] <a href="https://spring.io/projects/spring-cloud-netflix">Spring Cloud Netflix.</a></li>
  </ul>
</details>

## EER Diagram

### Table cliente 
| Idx | Field Name | Data Type |
|---|---|---|
| *🔑 ⬋ | <a name='PUBLIC.cliente_id'>id</a>| INTEGER  |
|  | <a name='PUBLIC.cliente_apellidos'>apellidos</a>| VARCHAR&#40;50&#41;  |
|  | <a name='PUBLIC.cliente_nombre'>nombre</a>| VARCHAR&#40;30&#41;  |
|  | <a name='PUBLIC.cliente_dni'>dni</a>| VARCHAR&#40;10&#41;  |
|  | <a name='PUBLIC.cliente_points'>points</a>| INTEGER  |

### Table coche 
| Idx | Field Name | Data Type |
|---|---|---|
| *🔑 | <a name='PUBLIC.coche_id'>id</a>| INTEGER  |
|  | <a name='PUBLIC.coche_fx_alquiler'>fx&#95;alquiler</a>| TIMESTAMP  |
|  | <a name='PUBLIC.coche_fx_devolucion'>fx&#95;devolucion</a>| TIMESTAMP  |
|  | <a name='PUBLIC.coche_marca'>marca</a>| VARCHAR&#40;20&#41;  |
|  | <a name='PUBLIC.coche_modelo'>modelo</a>| VARCHAR&#40;20&#41;  |
|  | <a name='PUBLIC.coche_tipo'>tipo</a>| VARCHAR&#40;10&#41;  |
|⬈| <a name='PUBLIC.coche_cliente_id'>cliente&#95;id</a>| INTEGER  |
| Foreign Keys |
|  | fk_coche_cliente | ( cliente&#95;id ) ref [PUBLIC&#46;cliente](#cliente) (id) |

## Files and Directories Structure

### Project Structure

```text
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── com.globalpay.demo
│           │ 
│           ├── com.globalpay.demo.config
│           │  
│           ├──com.globalpay.demo.model
│           ├──com.globalpay.demo.repository
│           │  
│           ├──com.globalpay.demo.rest
│           ├──com.globalpay.demo.rest.util
│           │  
│           ├──com.globalpay.demo.service
│           ├──com.globalpay.demo.service.dto
│           ├──com.globalpay.demo.service.impl
│           ├──com.globalpay.demo.service.impl.util
│          
├── src
│   └── main
│       └── resources
│           ├── application.yml
|           ├── application-dev.yml
│           │  
|           ├── data.sql
|
├── src
│   └── test
├── JRE System Library
├── Maven Dependencies
├── src
├── target
│   └──application-0.0.1-SNAPSHOT
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

### Packages

* 	`config` - app configurations;
* 	`models` - to hold our entities;
* 	`repository` - to communicate with the database;
* 	`rest` - to listen to the client;
* 	`rest.util` - util class to rest controller;
* 	`service` - interfaces to hold business logic;
* 	`service.dto` - dtos to map object;
* 	`service.impl`- to hold business logic;
* 	`service.impl`- util class to our implementation services;
* 	`resources/` - Contains all the static resources, templates and property files.
* 	`resources/application.yml` 
* 	`resources/application-dev.yml` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

* 	`test/` - contains unit and integration tests

* 	`pom.xml` - contains all the project dependencies
