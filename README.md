# The com.jiappo.open.api Project

## About

this is business General  open-api , provide other platform message Ability to interact

## Technical Stack

- Java 1.8+
- Maven 3.5+
- Spring boot 2.1.0.RELEASE+
- Lombok abstraction

## module

- open-api
- open-api-application
- open-api-dao
- open-api-domain
- open-api-integration
- open-api-support
- open-api-test
- open-api-facade

## License

This software is licensed under the [BSD License][BSD]. For more information, read the file [LICENSE](LICENSE).

[BSD]: https://opensource.org/licenses/BSD-3-Clause

## quick start

- Windows
  java -cp "%classpath%" -jar -Dspring.config.location=etc/ lib/open-api-1.0.0-SNAPSHOT.jar
- linux
  java -server -cp $classpath -jar -Dspring.config.location=etc/ lib/open-api-1.0.0-SNAPSHOT.jar 2>&1  &  