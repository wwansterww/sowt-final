# SoftFinalProject – University / Student Management System (Backend)

SoftFinalProject is a Spring Boot backend for a simple University system.  
It provides REST APIs for authentication, users, student profiles and grades.

---

## Tech Stack
- Java 17
- Spring Boot
- Spring Web (REST)
- Spring Data JPA (Hibernate)
- Spring Security (Basic Auth, Roles)
- Validation (Jakarta Validation)
- Liquibase
- MySQL
- MapStruct
- Lombok
- JUnit 5 + Mockito

---

## Main Features
### Authentication / Users
- User registration (`/api/auth/register`)
- Login with **Basic Auth**
- Roles:
  - `ADMIN`
  - `TEACHER`
  - `USER`
- User status:
  - Active users can login
  - Blocked users cannot login

### Student Profile
- Create a student profile for a user (1 user = 1 profile)
- Store: major, group, study year, student number

### Grades
- Teacher can add grades
- Users can view grades (with access rules)

---

## Role Access (Examples)
- `POST /api/auth/register` → **PUBLIC**
- `POST /api/grades` → **TEACHER only**
- `GET /api/grades/student/{studentProfileId}` → **TEACHER / ADMIN / USER**
- All other endpoints → **Authentication required**

---

## How to Run (Local)
### Requirements
- JDK 17
- MySQL running
- Gradle (or use Gradle Wrapper)

### 1) Configure Database
Edit: `src/main/resources/application.properties`

Example:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sowt_final
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=validate
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
