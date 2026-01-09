📝 Quiz Management System – Spring Boot

A backend Quiz Management System built using Spring Boot with manual token-based authentication and role-based authorization.
The project focuses on core backend fundamentals by using Hibernate EntityManager directly instead of Spring Data JPA.

🚀 Features

👤 User Module
User registration with BCrypt password hashing
User login with token generation
Token-based authentication (UUID)
Old token invalidation on re-login

🛡 Authorization
Role-based access control:
ADMIN
  Create quizzes
  Add questions
  View user attempts
  Fetch user details
USER
  Attempt quizzes
Authorization handled via custom service (no Spring Security)

📚 Quiz Module
Create quizzes
Add questions to quizzes
Maintain question order
Prevent invalid quiz operations

❌ Exception Handling
Centralized exception handling using @RestControllerAdvice
Custom exceptions:
  Invalid token
  User not found
  Invalid username/password
  Quiz not found
  Quiz attempt not allowed

🧱 Architecture

Controller Layer
   ↓
Service Layer
   ↓
Repository Layer (EntityManager)
   ↓
Database

Controller: Handles HTTP requests and responses
Service: Business logic and validations
Repository: Database access using EntityManager
DTOs: Request/response abstraction
Entities: JPA entities

🛠 Tech Stack
  Java
  Spring Boot
  Hibernate (JPA)
  EntityManager (No Spring Data JPA)
  MySQL
  BCryptPasswordEncoder
  Maven

🔐 Authentication Design

Passwords stored using BCrypt hashing
Login returns a UUID token
Token stored in UserAuth table
Token required in request headers for protected APIs
Token validation done manually in service layer

📦 API Overview

Register User
POST /users/register

Login User
POST /users/login

Quiz Attempts
POST /attempt/start

Create Quiz (ADMIN)
POST /admin/quiz
Header: token

Add Questions (ADMIN)
POST /admin/quiz/{quizId}/question
Header: token

Get User Attempts (ADMIN)
GET /admin/{userId}/attempts
Header: token

Get User Details (ADMIN)
GET /users/{id}

🧪 Error Handling

All errors return a consistent response format:
{
  "timestamp": "2026-01-09T12:30:00",
  "status": 401,
  "success": false,
  "message": "Invalid token",
  "data": null
}

💡 Why EntityManager Instead of Spring Data JPA?

To understand Hibernate internals
To write custom JPQL queries
To avoid abstraction hiding SQL behavior
To gain fine-grained control over persistence

📈 Future Enhancements

JWT-based authentication
Refresh token mechanism
Pagination & filtering
Swagger/OpenAPI documentation

Unit & integration tests

👨‍💻 Author

Balkrishna Naik
Backend Developer | Java | Spring Boot
