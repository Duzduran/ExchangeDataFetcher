# Narodowy Bank Polski Data Fetcher

Project provides a simple runnable local server that exposes endpoints to query 
data from the Narodowy Bank Polski's public APIs and return relevant information 
from them. The server is built using Java with Spring Boot, uses JUnit for testing, and can be run in a Docker container.

# Getting Started
These instructions will help you get the project up and running 
on your local machine for development and testing purposes.


# Prerequisites
- Java 11 or later
- Spring Boot
- Docker 

# Installing
1. Clone the repository to your local machine.
2. Build the project using Maven or your preferred build tool.
3. Run the project locally using the following command:
```java -jar target/DynatraceTask-0.0.1-SNAPSHOT.jar```

# Running with Docker

1. Build the Docker image using the following command:
```docker build -t springapi .```
Note: Make sure you are in the project's root directory where the Dockerfile is located.
2. Run the Docker container using the following command:
```docker run -p 8080:8080 project-name```
   Note: This maps the container's port 8080 to the host's port 8080. You can change the host's port number if needed.

# Pull the Docker Image

```docker pull djshadows/dynatrace-assesment```
Once the image is pulled, it can be used to create Docker containers and run applications within those containers.


docker pull command:
``` docker pull djshadows/dynatrace-assesment ```

# Testing
The project uses ```@WebMvcTest``` annotation to enable web-related 
testing features, and the ```@MockBean``` annotation to create a mock bean for testing purposes. The tests cover different scenarios for the BasicController class, 
which is the controller being tested, including testing the ```averageExchangeRates()```, ```diffBuySell()```, and ```maxMinAverage()``` methods with valid input. The tests use assertions from the JUnit library, such as ```assertEquals()```, ```assertNotNull()```, and ```assertFalse()```, to verify expected behavior and outcomes.

# EndPoints 
The project provides the following endpoints:

### 1. Get Average Exchange Rate by Date and Currency Code
- Endpoint: /exchanges/{currencyCode}/{date}
- Method: GET
- Description: Returns the average exchange rate for the given currency code and date.
- Example: ```curl -X GET "http://localhost:8080/averageExchangeRates/{currency}/{date}?currency=GBP&date=2023-01-02" -H "accept: */*"```


### 2. Get Max and Min Average Value by Currency Code and Number of Quotations
- Endpoint: /exchanges/{currencyCode}/max-min/{numOfQuotations}
- Method: GET
- Description: Returns the max and min average value for the given currency code and number of quotations.
- Example: ```curl -X GET "http://localhost:8080/maxMinAverage?currency=USD&no=20" -H "accept: */*"```

### 3. Get Major Difference between Buy and Ask Rate by Currency Code and Number of Quotations
- Endpoint: /exchanges/{currencyCode}/major-difference/{numOfQuotations}
- Method: GET
- Description: Returns the major difference between the buy and ask rate for the given currency code and number of quotations.
- Example: ```curl -X GET "http://localhost:8080/diffBuySell?currency=PLN&no=20" -H "accept: */*"```


# Additional Features
The project includes the following additional features:

- Unit tests: The project includes JUnit tests for each endpoint to ensure correct behavior.
- Docker image: The project can be built into a Docker image for easy deployment.
- Swagger UI: The project includes Swagger UI for API documentation and testing. You can access it at http://localhost:8080/swagger-ui.html after running the project.

# Contributing
- If you would like to contribute to the project, please open an issue or submit a pull request.
