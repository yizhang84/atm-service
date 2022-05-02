# atm-service
A demo project for retrieving atm information based on the given identification and an open api url.

# Description
This project expose one GET endpoint [/atms] requiring two parameter: url and identification

e.g http://localhost:8080/atms?url=https://api.lloydsbank.com/open-banking/v2.2/atms&identification=LFFFBC11
response: 
 - status 200, if a matched ATM found for the given identification.
 - status 400, if the required parameters are missing or the given open api url is not correct.

## Setup
To run this project, from terminal:
```
$ ./gradlew clean build
$ java -jar build/libs/atm-service.jar
```

To see the description and documentation of the RESTful APIs via swagger
```
http://localhost:8080/swagger-ui.html
```

The health actuator endpoint has been activated
```
http://localhost:8080/actuator/health
```

if the project started correctly, we should see the following result:
```
{
  "status": "UP"
}
```