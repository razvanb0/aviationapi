# Aviation API Wrapper

## ✈️ Overview
This project is a Spring Boot REST API that retrieves and exposes airport information from the Public Aviation API.

## 📁 Project Structure
```
/src
├── /main
│    ├── /java/com/demo/aviationapi
│    │    ├── /clients
│    │    │     ├── AviationApiClient.java
│    │    │     └── impl/PublicAviationApiClient.java
│    │    ├── /controllers
│    │    │     └── AirportsController.java
│    │    ├── /services
│    │    │     ├── AirportService.java
│    │    │     └── impl/DefaultAirportService.java
│    │    ├── /mappers
│    │    │     └── AirportMapper.java
│    │    ├── /domain
│    │    │     └── Airport.java
│    │    ├── /dtos
│    │    │     └── AirportDto.java
│    │    ├── /config
│    │    │     ├── SecurityConfig.java
│    │    │     └── RestTemplateConfig.java
│    │    ├── /exception
│    │    │     └── handlers/GlobalExceptionHandler.java
│    │    │     └── NotFoundException.java
│    │    └── AviationApiApplication.java
│    └── /resources
│         ├── application.properties
└── /test
     └── /resources
           └── application-test.properties
      ├── /java/com/demo/aviationapi
           └── clients/impl/PublicAviationApiClientTest.java
```

## 🚀 Running the Project Locally

### 1. Clone the repository
```bash
git clone https://github.com/razvanb0/aviationapi.git
cd aviationapi
```

### 2. Build the project
```bash
mvn clean install
```

### 3. Run the project
```bash
mvn spring-boot:run
```

API will run at:

```
http://localhost:8080/api/v1/airports/{ICAO}
```

Example:
```
http://localhost:8080/api/v1/airports/KAVL
```

## 🧪 Running Integration Tests

```bash
mvn test
```