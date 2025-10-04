# FormulaSAE Telemetry Project 

This repository is the early development stage of a **Formula SAE telemetry system**, built with **Spring Boot** and **modular architecture** to manage different subsystems of the car.

## Current Status
- **Experimental phase** – everything here is still under testing.  
- **Arduino data is mocked** – no real sensors connected yet.  
- **Car rules not finalized** – telemetry logic and data validation rules are still being defined.  

## Project Structure
- **Backend (Spring Boot)**  
  - `entity/` → Database entities (e.g. `PilotEntity`, `Telemetry`, `CoolingTelemetryEntity`, `BrakeTelemetryEntity`).  
  - `repository/` → JPA repositories for database operations.  
  - `service/` → Business logic and telemetry services (e.g. Pilot, Cooling, Brake).  
  - `model/` → Domain models (e.g. `Pilot`, `ComponentStatus`).  

- **Frontend / Integration**  
  - `testWebSocket.js` → Basic test for WebSocket communication.  
  - `package.json` → Node.js dependencies for integration and testing.  

- **Config**  
  - `pom.xml` → Maven dependencies.  
  - `.idea/`, `.gitattributes`, `.gitignore` → IDE and Git configs.  

## Configuration

This project uses **PostgreSQL** as the database.  

### 1. Create the Database
Make sure PostgreSQL is running and create a database named:

```
cp_man_sae_telemetry
```

### 2. Configure `application.properties`
Copy & paste the following configuration into your `src/main/resources/application.properties`:

```properties
spring.application.name=formulaSAE

spring.datasource.url=jdbc:postgresql://localhost:5432/cp_man_sae_telemetry
spring.datasource.username=Your username
spring.datasource.password=Your Password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
spring.jpa.open-in-view=false
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl

logging.level.root=warn
```

🔹 **Notes:**  
- `spring.jpa.hibernate.ddl-auto=create-drop` → Database tables are automatically created at startup and dropped when the app stops. This is only for testing.  
- Default username and password are `postgres` / `697274`. Change these values if your local database uses different credentials.  

## ▶Running the Project

### With Maven
Run the project using the command:

```bash
mvn spring-boot:run
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/formulaSAE-0.0.1-SNAPSHOT.jar
```

### WebSocket Test
There is a simple Node.js script (`testWebSocket.js`) for WebSocket communication tests.  

Install dependencies:

```bash
npm install
```

Run the test script:

```bash
node testWebSocket.js
```

## 🚀 Next Steps
- Define car telemetry rules (what data to capture, thresholds, alerts).  
- Connect Arduino and replace mocks with real sensor data.  
- Expand services for all subsystems (engine, brakes, cooling, suspension, electrical).  
- Build a dashboard for real-time visualization in web.  

⚠️ **Disclaimer**  
This project is a **work in progress** and should not be used in production.  
All current data is mocked and only intended for testing and architecture validation.