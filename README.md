---

# 🧙 Dungeon Game Solver API

This project is a Spring Boot application that provides a RESTful API to solve the LeetCode **"Dungeon Game"** problem. It measures the execution time of the solution and persists the problem input, solution output, and execution time into a **PostgreSQL** database.

---

## 🚀 Features

* **Dungeon Game Solver**: Implements a dynamic programming solution for the Dungeon Game problem.
* **RESTful API**: Exposes an endpoint (`/api/dungeon/solve`) to receive dungeon matrices and return the calculated minimum health and execution time.
* **Performance Measurement**: Automatically times the execution of the solver.
* **Data Persistence**: Saves each problem request, its solution, and the execution time to a PostgreSQL database.
* **JUnit 5 Tests**: Comprehensive unit tests for the solver logic.

---

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed:

* Java Development Kit (JDK) 21 or higher
* Maven (CLI or via your IDE)
* PostgreSQL database server
* PostgreSQL client (e.g., `psql`, DBeaver)

---

## ⚙️ Setup and Running the Application

### 1. Clone the Repository

```bash
git clone https://github.com/pedrohk/dungeon-game-java.git
cd dungeon-game-java
```

Or create the project structure manually using Spring Initializr and add the provided code.

---

### 2. Database Setup

Connect to your PostgreSQL instance and run:

```sql
CREATE DATABASE dungeon_db;
CREATE USER your_username WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE dungeon_db TO your_username;
```

---

### 3. Configure `application.properties`

Create or update the file at:
`src/main/resources/application.properties`

```properties
# Server Port
server.port=8080

# PostgreSQL Data Source
spring.datasource.url=jdbc:postgresql://localhost:5432/dungeon_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Logging
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

🔒 **Important:** Replace `your_username` and `your_password` with your actual PostgreSQL credentials.

---

### 4. Build the Project

From the root directory:

```bash
mvn clean install
```

---

### 5. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main class directly from your IDE.
The application will be available at:
[http://localhost:8080](http://localhost:8080)

---

## 📡 API Endpoint Usage

### POST `/api/dungeon/solve`

Solves the Dungeon Game problem for a given matrix.

* **Method**: POST
* **URL**: `http://localhost:8080/api/dungeon/solve`
* **Content-Type**: `application/json`

#### 📥 Request Body Example

```json
[
  [-2, -3, 3],
  [-5, -10, 1],
  [10, 30, -5]
]
```

#### 📤 Response Example (Success)

```json
{
  "problemName": "Dungeon Game",
  "solution": 7,
  "executionTimeMillis": 2,
  "status": "Solution calculated and saved to database."
}
```

#### ❌ Response Example (Error)

```json
{
  "error": "Invalid dungeon matrix provided."
}
```

---

### 🔧 Example `curl` Command

```bash
curl -X POST \
  http://localhost:8080/api/dungeon/solve \
  -H "Content-Type: application/json" \
  -d '[[ -2, -3, 3 ],[ -5, -10, 1 ],[ 10, 30, -5 ]]'
```

Or test using Postman or your browser's developer tools.

---

## 🧪 Running Tests

To execute unit tests with Maven:

```bash
mvn test
```

All tests for the solver logic will run and results will be displayed in the console.

---

## 📂 Project Structure (Overview)

```
dungeon-game-Java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/pedrohk/dungeon/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── DungeonGameJavaApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/pedrohk/dungeon/
│               └── solver/
│                   └── DungeonGameJavaTest.java
```
