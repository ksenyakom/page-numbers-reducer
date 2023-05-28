# Page Numbers Reducer
Reduces given page numbers. For example:

>original pages: 1,4,5,7,8,50
> 
>reduced pages: 1,4-5,7-8,50

## Built With
* Java 17
* Maven
* Spring boot 3
* OpenApi 3.0


## Getting Started
To get a local copy up and running follow these simple example steps.

1. Install required packages:
* [JDK 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
* [Maven](https://maven.apache.org/download.cgi?Preferred=ftp%3A%2F%2Fmirror.reverse.net%2Fpub%2Fapache%2F)


2. Clone the repo

```sh
   git clone https://github.com/ksenyakom/page-numbers-reducer.git
```

3. Build the project 
```sh
   $ mvn clean install -DskipTests=true
```

4. Run the project
```sh
   $ mvn spring-boot:run
   or
   $ java -jar target/pagenumbersreducer-0.0.1-SNAPSHOT.jar
```

## Usage
Application provides API for reducing page numbers.

_For examples, please refer to the [Swagger documentation](http://localhost:8080/swagger-ui/index.html )_



## Publish Docker images in Docker Hub

Create repository (eg. myrepo) in Docker Hub (//hub.docker.com).
Enter your username instead [username] in the next line in pom.xml.
```xml
<repository>registry.hub.docker.com/[username]/${project.artifactId}</repository>
```
Run the command to build the image:
```sh
mvn dockerfile:build
or
$ mvn clean package
```

To push the image to Docker Hub you need to create a settings.xml under ~/.m2 path 
and add the Docker Hub credentials. 
Here is an example that you just need to replace username, password, and email.
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
https://maven.apache.org/xsd/settings-1.0.0.xsd">
<servers>
<server>
<id>registry.hub.docker.com</id>
<username>USERNAME</username>
<password>PASSWORD</password>
<configuration>
<email>EMAIL_ADDRESS</email>
</configuration>
</server>
</servers>
</settings>
```
Run the command to push image to the Docker Hub repository:
```sh
$ mvn dockerfile:push
or	
$ mvn clean deploy
```


**Pull Image** 
You can now try to pull the image:

```sh
$ docker pull kseniya1/pagenumbersreducer
```



