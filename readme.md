# Code4Fun Service

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=adinandradrs_codefun-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=adinandradrs_codefun-service)

Code4Fun Service is a library that **used as a core function** to create service application (microservice) based on Java and Spring. Code4Fun Service also provides some of functionalities, configuration, security, and base integration layer that usually needed to create an application.
Here is the minimum requirements to use this library
1. Lombok
1. JDK v11
1. Spring
1. Code4Fun Common

## Installation

Use the maven command [mvn](https://maven.org/) to install Code4Fun.

```xml
<dependency>
    <groupId>id.codefun.service</groupId>
    <artifactId>codefun-service</artifactId>
    <version>${codefun-service.version}</version>
</dependency>
```

## Usage

Environment integration logic class
```java
public class SftpAdaptor implements EnvironmentAdaptor<MyRequest, MyResponse> {

    public MyResponse execute(MyRequest request) {
        return ...
    }

}
```

RESTful integration logic class (pull the result as plain text to parse manually)
```java
// The author will complete the usage soon as possible
```

## Contributing
For major changes, please open an issue first to discuss what you would like to change. Currently the author is trying to complete the full detail documentation on his Confluence for a further development.

## License
[MIT](https://choosealicense.com/licenses/mit/)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)](https://sonarcloud.io/summary/new_code?id=adinandradrs_codefun-service)