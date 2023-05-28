# Page Numbers Reducer
***

Reduces given page numbers. For example:

original pages: 1,4,5,7,8,50

reduced pages: 1,4-5,7-8,50

## Built With
***
* Java 17
* Maven
* Spring boot 3
* OpenApi 3.0


## Getting Started
***
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
***
Application provides API for reducing page numbers.

_For examples, please refer to the [Swagger documentation](http://localhost:8080/swagger-ui/index.html )_


