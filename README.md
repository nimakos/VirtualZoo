# VirtualZoo : Project Information

IDE: IntelliJ

Language: Java 11

Tools : Spring boot, Lombok, Sl4j, POSTGRESQL

Build Tool : Maven

Container : Docker

Web Service Application

# Build and run project without Docker container
**1.** Go to DataSourceConfig.java, and select the getDatasourceAddress() (You can change the datasource properties from the properties file)

**2.** Build jar file with Maven tool (exclude test cases)
mvn -Dmaven.test.skip=true clean install

**3.** Start Application.
java -jar ./web/target/virtualZoo.jar

**4.** Run Application
Open a web browser and type https://localhost:8888/myZoo/animals/all (You can change the ip address and the port from the properties file)


# Docker -Build and run a container with Sql Server and application Images (with running docker-compose.yml script)
**1.** Go to DataSourceConfig.java, and select the getDatasourceAddress2() (You can change the datasource properties from the properties file)

**2.** Build jar file with Maven tool (exclude test cases)
mvn -Dmaven.test.skip=true clean install

**3.** Start Application. Create Docker Images (Running docker-compose.yml script)
docker-compose up

**4.** Run Application
Open a web browser and type https://localhost:8888/myZoo/animals/all (You can change the ip address and the port from the .yml file)


# Docker -Build and run a container with application Image but with no Sql Server (running all localhost)
**1.**  make primary mySqlDataSource() method and put getDatasourceAddress() (You can change the datasource properties from the properties file)

**2.** Build jar file with Maven tool (exclude tests)
mvn -Dmaven.test.skip=true clean install

**3.** Build Docker Image if not exists
docker build . -t spring-boot-mvc-image-without-sql

**4.** Create and Run the Application container with application Image
docker run -p 8888:8888 --name spring-boot-mvc-container-without-sql -d spring-boot-mvc-image-without-sql