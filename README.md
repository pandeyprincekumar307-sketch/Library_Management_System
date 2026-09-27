# 📚 Library Management System

A **Library Management System** developed using **Spring Boot** that helps manage books, students/users, book issuing, returns, and library records efficiently. The application provides a structured way to automate common library operations and reduce manual record keeping.

## 🚀 Features

* 📖 Add, update, view, and delete books
* 👤 Manage students/users
* 🔍 Search books by title, author, or category
* 📤 Issue books to users
* 📥 Return issued books
* 📋 Track issued and available books
* 🗃️ Store library data in a relational database
* ✅ Input validation
* ⚠️ Error handling
* 🌐 RESTful APIs
* 🔐 Authentication and authorization *(if implemented)*

## 🛠️ Technologies Used

| Technology         | Purpose                          |
| ------------------ | -------------------------------- |
| Java               | Programming Language             |
| Spring Boot        | Backend Framework                |
| Spring MVC         | Web/REST API Development         |
| Spring Data JPA    | Database Operations              |
| Hibernate          | ORM                              |
| MySQL              | Database                         |
| Maven              | Dependency Management            |
| HTML/CSS/Bootstrap | Frontend *(if applicable)*       |
| Thymeleaf          | Server-side UI *(if applicable)* |
| Postman            | API Testing                      |
| Git & GitHub       | Version Control                  |

## 🏗️ Project Structure

```text
library-management-system/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.library/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── entity/
│   │   │       ├── dto/
│   │   │       └── LibraryApplication.java
│   │   │
│   │   └── resources/
│   │       ├── templates/
│   │       ├── static/
│   │       └── application.properties
│   │
├── pom.xml
└── README.md
```

## 🗄️ Database

The application uses **MySQL** for storing library information.

Example database creation:

```sql
CREATE DATABASE library_management;
```

Configure your database in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> Replace `your_password` with your MySQL password.

## 📦 Maven Dependencies

The project may include dependencies such as:

```xml
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

</dependencies>
```

Add Thymeleaf or Spring Security dependencies if your project uses them.

## 🔄 Application Flow

```text
User
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
MySQL Database
```

### Example

When a user issues a book:

```text
User → Issue Book Request
          ↓
      Controller
          ↓
       Service
          ↓
    Check Availability
          ↓
       Repository
          ↓
       Database
          ↓
   Issue Confirmation
```

## 🔗 Main Modules

### 1. Book Management

The administrator can:

* Add new books
* Update book details
* Delete books
* View all books
* Search books
* Check book availability

### 2. User Management

The system can maintain:

* User ID
* Name
* Email
* Phone number
* Address
* Registered date

### 3. Book Issue Management

The system records:

* User information
* Book information
* Issue date
* Due date
* Issue status

### 4. Book Return Management

When a book is returned:

* Return date is recorded
* Book availability is updated
* Issue status is changed
* Fine can be calculated if implemented

## 🌐 Example REST APIs

### Books

```http
GET    /api/books
GET    /api/books/{id}
POST   /api/books
PUT    /api/books/{id}
DELETE /api/books/{id}
```

### Users

```http
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}
```

### Issue & Return

```http
POST   /api/issues
GET    /api/issues
PUT    /api/issues/{id}/return
```

> Update these endpoints according to your actual controller mappings.

## ▶️ How to Run the Project

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-username/library-management-system.git
```

### Step 2: Open the Project

Open the project in:

* IntelliJ IDEA
* Eclipse
* Spring Tool Suite
* VS Code

### Step 3: Configure MySQL

Create the database:

```sql
CREATE DATABASE library_management;
```

Update your MySQL username and password in `application.properties`.

### Step 4: Build the Project

Using Maven:

```bash
mvn clean install
```

### Step 5: Run the Application

```bash
mvn spring-boot:run
```

Or run:

```text
LibraryApplication.java
```

### Step 6: Open the Application

For a web application:

```text
http://localhost:8080
```

For REST APIs, use **Postman** or another API client.

## 🧪 Testing

You can test the REST APIs using **Postman**.

Example request:

```http
POST /api/books
Content-Type: application/json
```

Request body:

```json
{
    "title": "Java Programming",
    "author": "James Gosling",
    "category": "Programming",
    "isbn": "9781234567890",
    "quantity": 5
}
```

## 🔮 Future Enhancements

* 🔐 Spring Security authentication
* 👨‍💼 Admin and user roles
* 📧 Email notifications for due dates
* 💰 Automatic fine calculation
* 📊 Admin dashboard
* 🔎 Advanced book search
* 📱 Responsive UI
* 📚 Book reservation system
* 📈 Library reports and statistics
* ☁️ Cloud deployment

## 👨‍💻 Developer

**Prince Kumar Pandey**

Java Full Stack Developer

### Skills Used

```text
Java
Spring Boot
Spring MVC
Spring Data JPA
Hibernate
MySQL
REST API
HTML
CSS
Bootstrap
Git & GitHub
```

## 📄 License

This project is created for **educational and learning purposes**. You are free to modify and improve it for your own projects.



⭐ If you find this project useful, consider giving the repository a **star** on GitHub.
