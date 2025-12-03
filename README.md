# 🏢 CRUD API using JDBC

This project is a **CRUD (Create, Read, Update, Delete) API** for managing employees, built with **Java and JDBC**. It demonstrates clean layering with DTO, DAO, Service, Controller, and Database Connection classes.

## 🛠 Tech Stack
- 💻 **Java** (JDK 8+)
- 🔗 **JDBC** for database interaction
- 🗄 **MySQL** (or any relational DB)
- 📦 Maven for project management

## 📂 Project Structure
```
src/
└─ main/
├─ dto/ # Employee data transfer objects
├─ dao/ # Database access objects
├─ service/ # Business logic
├─ controller/ # API endpoints / request handling
└─ connection/ # Database connection setup
pom.xml # Maven config
```

## ✨ Features / API Methods
- ➕ **Add Employee** – Insert a single employee into the database  
- ➕ **Add Multiple Employees** – Insert multiple employees in batch  
- ✏️ **Update Employee** – Update employee details by ID  
- ❌ **Delete Employee** – Remove a single employee by ID  
- ❌ **Delete Multiple Employees** – Remove multiple employees in batch  
- 🔍 **Get Employee by ID** – Retrieve a single employee  
- 🔍 **Get All Employees** – Retrieve all employees

## 🗄 Database Setup
1. Create a database:
```
CREATE DATABASE employee_db;
```
2. Create the employee table:
```
   CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    phone VARCHAR(20),
    doj DATE,
    designation VARCHAR(50),
    gender VARCHAR(10)
);
```
3. Update database credentials in connection class.


## 🚀 How to Run
1. Clone the repository.
2. Open in IDE (Eclipse, IntelliJ, etc.).
3. Build with Maven: mvn clean install
4. Run main class / deploy if using Servlets.
5. Test methods using Postman or any HTTP client.


## 🤝 Contributing
Fork the repository and submit pull requests.

## ⚖️ License
MIT License
