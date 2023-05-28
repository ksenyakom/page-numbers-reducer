FROM plainid/java-17:latest-master
ADD /target/pagenumbersreducer-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
WORKDIR /
ENTRYPOINT [ "java", "-jar", "pagenumbersreducer-0.0.1-SNAPSHOT.jar"]
