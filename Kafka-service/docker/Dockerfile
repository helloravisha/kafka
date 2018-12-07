FROM java
EXPOSE 8080
ADD ./kafka-service-0.0.1.jar kafka-service-0.0.1.jar
RUN bash -c 'touch /kafka-service-0.0.1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kafka-service-0.0.1.jar"]
