# Payment Service Spring  Boot with Docker

### Gradle minimal dependencies


Gradle file:

```gradle
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:1.3.3.RELEASE")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'spring-boot'

jar {
    baseName = 'payment-service'
    version =  '0.1.0'
}

repositories {
    mavenCentral()
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

task wrapper(type: Wrapper) {
    gradleVersion = '2.3'
}
```


### Generating a jar by using Gradle

To generate a new jar  use the **gradle build** command. 

Execute the following command:

```bash
$ docker build
```

This will create a jar file named **payment-service.jar** in the **build/libs** directory. Note that this name is from build.gradle file.

```bash
$ build/libs/spring-boot-gradle-docker.jar
```

To execute the Application, you just need to execute the jar file and access the address:

```bash
$ java -jar build/libs/payment-service.jar 
```

### Dockerfile

```docker
FROM java
EXPOSE 8080
ADD ./payment-service-0.0.1.jar payment-service-0.0.1.jar
RUN bash -c 'touch /payment-service-0.0.1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/payment-service-0.0.1.jar"]
```

### Build a Docker Image with Gradle

```gradle
buildscript {
    ext {
        springBootVersion     = "1.5.2.RELEASE"
        gradleDockerVersion   = "1.2"
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath("se.transmode.gradle:gradle-docker:${gradleDockerVersion}")
    }
}

group = "helloravisha"

apply plugin: 'docker'
apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'org.springframework.boot'

jar {
    baseName = 'payment-service'
    version =  '0.0.1'
}

repositories {
    mavenCentral()
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile group: 'junit', name: 'junit', version: '4.12'
	compile group: 'org.mockito', name: 'mockito-all', version: '1.9.5'
	compile group: 'org.hamcrest', name: 'hamcrest-all', version: '1.3'
	compile group: 'org.hibernate', name: 'hibernate-core', version: '5.2.1.Final'
	compile group: 'org.hibernate', name: 'hibernate-validator', version: '5.2.4.Final'
	compile group: 'mysql', name: 'mysql-connector-java', version: '6.0.3'
    compile("org.springframework.boot:spring-boot-devtools")
    compile("org.apache.tomcat.embed:tomcat-embed-jasper")
	    
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

task buildDocker(type: Docker, dependsOn: build) {
  push = true
  applicationName = jar.baseName
  dockerfile = file('docker/Dockerfile')
  doFirst {
    copy {
      from jar
      into stageDir
    }
  }
}

task wrapper(type: Wrapper) {
    gradleVersion = '2.3'
}

```

Note that we are using the **push** variable with a true value. This means that we will **push** the image that has created by the build gradle.

But where that image will be pushed? Note the another variable named **group**. This indicates the name of our remote docker repository, in my case named as **helloravisha** (https://hub.docker.com/r/helloravisha/payment-service).
### Using  Docker Image

After the **gradle buildDocker** command, we pushed our new image to Docker Hub. Now we are able to use it.

Next, we will run a new container using the new Docker Image:

```bash
$ docker run -it -p 8080:8080 helloravisha/payment-servicer 
```

Now we might access the application from browser! 


## Class Diagram

![Alt text](https://github.com/nguyensjsu/su18-202-java-geeks/blob/master/code/services/payment-service/UML/Project_Class_Diagram.png)

## Sequence Diagram

![Alt text](https://github.com/nguyensjsu/su18-202-java-geeks/blob/master/code/services/payment-service/UML/Payment%20Service%20Seq%20Diagram.png)





