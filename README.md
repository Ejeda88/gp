# gp
Microservice developed in Java 8 with Spring Boot v2.7.7-SNAPSHOT to solve the following problems:

## Technical evaluation test:
### The problem - Car rental system
For a car rental system, we want to create a piece of software for managing the rental administration with three primaries functions:
+ Have an inventory of cars
+ Calculate the price for rental
+ Keep the track of the customer loyalty points
 
#### Price
The price of rentals is based in the type of the car and how many days it has been rented for.
The users will say when renting the car for how many days they want to rent it and they will pay up front.
If the car is returned late, then rent for extra days will be charged when returning.

The system has three types of cars:
#### Premium cars- 
+ Price is premium price times number of days rented.
+ Extra day: premium price + 20% of <premium price>

#### SUV cars- 
Price is:
+ For the first 7 days: SUV price per day.
+ Between 7 and 30 days: 80% of SUV price per day.
+ More than 30 days: 50% of SUV price per day
+ Extra day: SUV price + 60% of small price per day

#### Small cars- 
Price is:
+ For the first 7 days: small price per day.
+ More than 7 days: 60% of <small price per day
+ Extra day: small price + 30% of small price

#### Prices:
+ premium price: 300€
+ SUV price: 150€
+  small price: 50€
                                  
The program should expose a rest-api HTTP API (you could use any framework, if you don’t have a personal preference, you could use
Spring Boot)

The API should (at least) expose the following operations:
+ Rent one or several cars and calculate the price.
+ Return a car and calculate surcharges (if exist)

Example of price calculations
+ BMW 7 (Premium) 10 day -> 3000€
+ Kia Sorento (SUV) 9 days -> 1290€
+ Nissan Juke (SUV) 2 days -> 300€
+ Seat Ibiza (small) 10 days -> 440€

When returning cars late:
+ BMW 7 (Premium) 2 extra days -> 720€
+ Nissan Juke (SUV) 1 day extra -> 180€

Loyalty points
Customers get loyalty points when renting a car, regardless of the time.
+ Premium car: 5 points.
+ SUV car: 3 points.
+ Small car: 1 point.


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
 
 ## Installation

* Default active profile is **`dev`**. When the application is running, the necessary tables and system data (defined in the *`data.sql`*) will be created. Normally in **`test`** profiles, applications use the **H2** database (data in memory). For this test I have decided to use a H2. In a normal project, for the development profile, I would have opted for a database such as **Postgres**, **MySql** or similar.

<details open="open">
	<ul>
		<li><a href="#running-the-application-with-ide">Running the application with IDE</a></li>
		<li><a href="#running-the-application-with-maven">Running the application with Maven</a></li>
		<li>
			<a href="#running-the-application-with-executable-jar">Running the application with Executable JAR</a>
			<ul>
				<li>
					<a href="#accessing-data-in-h2-database">Accessing Data in H2 Database</a>
					<ul>
						<li><a href="#h2-console">H2 Console</a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li><a href="#running-the-application-via-docker-container">Running the application via docker container</a></li>
	</ul>
</details>

#### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.globalpay.demoDemoApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

#### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/Ejeda88/gp.git
$ cd demo
$ mvn spring-boot:run
```

#### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command 

```shell
$ git clone https://github.com/Ejeda88/gp.git
$ cd demo
$ mvn package -DskipTests
$ java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **jcmd** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command

##### Accessing Data in H2 Database

###### H2 Console

URL to access H2 console: **http://localhost:8080/h2-console/login.jsp** or **https://192.168.99.102:8080/h2-console/login.jsp** if **SSL** is enabled.

Fill the login form as follows and click on Connect:

* 	Saved Settings: **Generic H2 (Embedded)**
* 	Setting Name: **Generic H2 (Embedded)**
* 	Driver class: **org.h2.Driver**
* 	JDBC URL: **jdbc:h2:mem:sbat;MODE=MySQL**
* 	User Name: **sa**
* 	Password:

<img src="images\h2db\h2-console-login.PNG"/>

<img src="images\h2db\h2-console-main-view.PNG"/>

#### Running the application via docker container

* 	Refer to [DOCKER.md](./DOCKER.md) for details.
