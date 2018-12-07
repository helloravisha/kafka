FROM java
EXPOSE 8081
ADD ./kafka-consumer-service-0.0.1.jar kafka-consumer-service-0.0.1.jar
RUN bash -c 'touch /kafka-consumer-service-0.0.1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kafka-consumer-service-0.0.1.jar"]
