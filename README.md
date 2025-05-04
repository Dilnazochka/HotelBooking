# Hotel Booking System

## Description

This is a Hotel Booking system built with Spring Boot. It includes user registration, login (including OAuth2 with Google and GitHub), JWT-based authentication, protected endpoints, Swagger documentation, and a basic frontend with Thymeleaf.

---

## ⚙️ Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- OAuth2 (Google, GitHub)
- JWT
- Thymeleaf
- Swagger (OpenAPI)

---

## 🔐 Security Setup

### ✅ JWT Authentication

**Endpoints:**

- `POST /api/auth/register` – for user registration
- `POST /api/auth/login` – for user login and receiving a JWT token

**Usage:**

After login, include the token in the `Authorization` header for accessing protected endpoints:

```
Authorization: Bearer <your_token>
```

### ✅ OAuth2 Authentication (Google & GitHub)

**Login URLs:**

- `http://localhost:8080/oauth2/authorization/google`
- `http://localhost:8080/oauth2/authorization/github`

After successful login, users are redirected to the homepage `/`.

---

## 🧪 How to Test

### 🔹 1. Run the project locally

```bash
mvn spring-boot:run
```

### 🔹 2. Test JWT authentication

**Register:**

```http
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "12345678"
}
```

**Login:**

```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "12345678"
}
```

You will receive a JWT token. Use it for authenticated requests:

```
Authorization: Bearer <your_token>
```

### 🔹 3. Test OAuth2 authentication

Open in browser:

- [http://localhost:8080/oauth2/authorization/google](http://localhost:8080/oauth2/authorization/google)
- [http://localhost:8080/oauth2/authorization/github](http://localhost:8080/oauth2/authorization/github)

---

## 🌐 Swagger UI

You can access API documentation at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 💻 Frontend

A basic login form is available at:

```
http://localhost:8080/login
```

After login, users are redirected to the homepage.

---

## 👩‍💻 Developer

- Name: Dilnaz Zhumabaeva  
- Email: dilnaz.zhumabaeva@alatoo.edu.kg
- GitHub: [https://github.com/Dilnazochka(https://github.com/Dilnazochka)]
