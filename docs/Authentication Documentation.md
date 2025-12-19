Warp5 Construction - Authentication System Documentation
Overview
This authentication system provides a complete user management solution for the Warp5 Construction application, including registration, login, profile management, and password recovery functionality.
System Architecture
Technology Stack
* Backend: Java Spring Boot
* Database: PostgreSQL
* Security: BCrypt Password Hashing
* HTTP: REST API
Key Components
1. AuthController - REST API endpoints
2. AuthService - Business logic layer
3. UserRepository - Data access layer
4. DTOs - Data Transfer Objects
5. User Model - Entity class
Database Schema
Users Table Structure
sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    address_line1 VARCHAR(100),
    address_line2 VARCHAR(100),
    city VARCHAR(50),
    phone_number VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    reset_token VARCHAR(255),
    auth_token VARCHAR(255)
);
API Endpoints
Base URL
text
http://localhost:8080/api/auth
1. User Registration
Endpoint: POST /register
Request Body (RegisterRequest):
json
{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1990-01-15",
  "addressLine1": "123 Main St",
  "addressLine2": "Apt 4B",
  "city": "New York",
  "phoneNumber": "+1234567890",
  "email": "john.doe@example.com",
  "password": "SecurePass123!"
}
Response (User):
json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "createdAt": "2024-01-15"
}
Validation Rules:
* Email must be unique
* Phone number must be unique
* All required fields must be provided
* Password is encrypted using BCrypt
2. User Login
Endpoint: POST /login
Request Body (LoginRequest):
json
{
  "loginId": "john.doe@example.com",  // OR phone number
  "password": "SecurePass123!"
}
Response: User object with authentication token
Login Options:
* Email-based login
* Phone number-based login
* Auto-detects based on @ symbol presence
3. Update Profile
Endpoint: PUT /update-profile/{userId}
Request Body (UpdateProfileRequest):
json
{
  "firstName": "Jonathan",
  "lastName": "Doe",
  "email": "jonathan.doe@example.com",
  "phoneNumber": "+1234567891",
  "addressLine1": "456 Oak Avenue",
  "city": "Boston"
}
Response: Updated User object
Note: Only provided fields are updated
4. Forgot Password
Endpoint: POST /forgot-password
Request Body:
json
{
  "emailOrPhone": "john.doe@example.com"
}
Response:
text
Reset token: 550e8400-e29b-41d4-a716-446655440000
Process:
1. Generates unique reset token
2. Stores token in user record
3. Returns token for password reset
4. In production, token would be sent via email/SMS
5. Reset Password
Endpoint: POST /reset-password
Request Body (ResetPasswordRequest):
json
{
  "resetToken": "550e8400-e29b-41d4-a716-446655440000",
  "newPassword": "NewSecurePass456!"
}
Response:
text
Password reset successful
Security Features
Password Security
* Uses BCrypt password hashing
* Automatic salt generation
* Secure password comparison
Token-Based Authentication
* JWT-like token generation
* Unique reset tokens for password recovery
* Token expiration management
Data Protection
* Email and phone number uniqueness validation
* Input validation at service layer
* Encrypted password storage
Business Logic
User Registration Flow
1. Validate email/phone uniqueness
2. Encrypt password
3. Create user record
4. Set creation timestamp
5. Return user object
Login Flow
1. Identify login method (email/phone)
2. Retrieve user from database
3. Validate password using BCrypt
4. Generate authentication token
5. Return user with token
Password Reset Flow
1. Generate unique UUID token
2. Store token with user
3. Validate token on reset
4. Encrypt new password
5. Clear reset token
Error Handling
Common Error Scenarios
1. Duplicate Registration: "Email already registered"
2. Invalid Credentials: "Invalid password" or "User not found"
3. Invalid Reset Token: "Invalid reset token"
4. Missing User: "User not found"
Error Responses
All errors return HTTP 400 with descriptive messages
Data Models
User Entity
java
@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String phoneNumber;  // UNIQUE
    private String email;        // UNIQUE
    private String password;     // BCrypt encoded
    private String authToken;
    private String resetToken;
    private LocalDate createdAt;
}
Setup and Configuration
Prerequisites
1. Java 17+
2. PostgreSQL 12+
3. Maven 3.6+
Installation Steps
1. Clone the repository
2. Configure database connection in application.properties
3. Run database migrations (schema provided)
4. Build project: mvn clean install
5. Run application: mvn spring-boot:run
Configuration Properties
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/warp5_db
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
Testing Examples
Using cURL
Register User:
bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phoneNumber": "+1234567890",
    "password": "password123"
  }'
Login:
bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "loginId": "john@example.com",
    "password": "password123"
  }'

