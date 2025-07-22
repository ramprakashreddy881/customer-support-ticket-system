# customer-support-ticket-system

# 📩 Customer Support Ticket System

A microservices-based **Customer Support Ticket System** built with Spring Boot, following **Hexagonal Architecture** and the **CQRS pattern**.

---

## 🏗️ Architecture Overview

- **Hexagonal Architecture**: Clean separation between business logic and external systems.
- **CQRS Pattern**: Command Query Responsibility Segregation for scalable read/write operations.
- **Microservices**:
  - 🎫 **Ticket Service** – Manages support tickets.
  - 👤 **User Service** – Handles user registration, authentication, and profile management.
  - 🌐 **API Gateway** – Routes requests and manages JWT authentication.

---

## 🛠️ Setup Instructions

### ✅ Prerequisites

1. **Java JDK 21**[Download JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
2. **Oracle Database**Install Oracle DB (preferably Oracle 19c or higher) locally or use a cloud instance.
3. **Maven**Ensure Maven is installed:
   ```sh
   mvn -v
   ```
4. **IDE**
   Use any IDE (VS Code, IntelliJ, STS) for running the services.

---

## ⚙️ Database Setup

1. **Install Oracle DB** and start the service.
2. **Create schemas/users** for each service .
3. **Configure each service** with the following in `application.yml`:

   ````yaml
   spring:
     datasource:
       url: jdbc:oracle:thin:@localhost:1521:XE # Replace XE with your Oracle SID
       username: YOUR_DB_USERNAME 
       password: YOUR_DB_PASSWORD
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   ````
4. **Manually Create Tables** (if not auto-generated)

```
-- USERS Table
CREATE TABLE USERDB.USERS (
  USER_ID   VARCHAR2(255) PRIMARY KEY,
  NAME      VARCHAR2(255),
  EMAIL     VARCHAR2(255) UNIQUE,
  PASSWORD  VARCHAR2(255),
  ROLE      VARCHAR2(50)
);-- TICKETS Table
CREATE TABLE DEMOREPLICA.TICKETS (
  TICKET_ID   VARCHAR2(255) PRIMARY KEY,
  TITLE       VARCHAR2(255),
  USER_ID     VARCHAR2(255),
  DESCRIPTION VARCHAR2(1000),
  STATUS      VARCHAR2(50),
  CREATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UPDATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE DEMO.TICKETS (
  TICKET_ID   VARCHAR2(255) PRIMARY KEY,
  TITLE       VARCHAR2(255),
  USER_ID     VARCHAR2(255),
  DESCRIPTION VARCHAR2(1000),
  STATUS      VARCHAR2(50),
  CREATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UPDATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 🚀 Running the Services

1. **Clone the repository** and open in your IDE.
2. **Build each service**:

   ```sh
   mvn clean install
   ```
3. **Run each service** (from IDE or terminal):

   ```sh
   mvn spring-boot:run
   ```

   - Ticket Service (default port: `8081`)
   - User Service (default port: `8082`)
   - API Gateway (default port: `8080`)

---

## 📚 API Documentation

Swagger UI is available for each service:

- **Ticket Service**: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **User Service**: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

---

## 🧪 API Testing (Sample Postman Collections)

### Ticket Service

| Action                 | Method | Endpoint                                         |
| ---------------------- | ------ | ------------------------------------------------ |
| Create Ticket          | POST   | `http://localhost:8080/ticket`                 |
| Get Ticket by ID       | GET    | `http://localhost:8080/ticket/{ticketId}`      |
| Get Tickets by User ID | GET    | `http://localhost:8080/ticket?userId={userId}` |
| Update Ticket Status   | PATCH  | `http://localhost:8080/ticket/{ticketId}`      |
| Get Tickets by Status  | GET    | `http://localhost:8080/ticket/status/{status}` |

**Sample Create Ticket Request:**

````json
{
  "title": "Task - ticket service",
  "description": "Design the CQRS and Hexagonal architecture",
  "status": "OPEN"
}
````

### 📌 Ticket Status

The `status` field in the Ticket entity supports the following values from the `TicketStatus` enum:

* `OPEN` – The ticket has been created and is awaiting processing.
* `IN_PROGRESS` – The ticket is currently being worked on.
* `CLOSED` – The ticket has been successfully resolved and closed.
* `REJECTED` – The ticket has been reviewed and rejected.

### User Service

| Action                  | Method | Endpoint                                                      |
| ----------------------- | ------ | ------------------------------------------------------------- |
| Register                | POST   | `http://localhost:8080/users/register`                      |
| Login                   | POST   | `http://localhost:8080/users/login`                         |
| Get Profile             | GET    | `http://localhost:8080/users/profile`                       |
| Admin: Get All Users    | GET    | `http://localhost:8080/admin/users`                         |
| Admin: Update User Role | PATCH  | `http://localhost:8080/admin/users?id={userId}&role={role}` |
| Admin: Delete User      | DELETE | `http://localhost:8080/admin/users/{userId}`                |

**Sample Register Request:**

````json
{
  "name": "test",
  "email": "test@gmail.com",
  "password": "test@123",
  "role": "admin"
}
````

**Sample Login Request:**

````json
{
  "email": "test@gmail.com",
  "password": "test@123"
}
````

---

### 👤 User Roles

The `role` field supports the following values from the `Role` enum:

* `ADMIN`
* `CUSTOMER`
* `SUPPORT`

## 🔐 Authentication

- All endpoints (except registration and login) require a **JWT Bearer Token**.
- Obtain the token via the `/users/login` endpoint and use it in the `Authorization` header:
  ```
  Authorization: Bearer <token>
  ```

---

### 🧪 Postman Collections

You can use the following Postman collections to test the API services:

#### 📁 Collection Files

* 👉 [Download User Service Collection](./User-Service.postman_collection.json)
* 👉 [Download Ticket Service Collection](./Ticket-Service.postman_collection.json)

#### 📌 How to Import

1. Download the `.json` files using the links above.
2. Open  **Postman** .
3. Click on  **`Import`** .
4. Choose **`Upload Files`** and select the collection file.
5. The endpoints will be available in your Postman workspace.

---



## 📝 Notes

- Update the database connection details in each service's `application.yml`.
- Ensure all services are running before testing APIs.
- Use Swagger UI or Postman for API exploration and testing.

---

## 🙋 Support

For any questions or issues, please open an issue in the repository.

---

**Happy Coding! 🚀**
