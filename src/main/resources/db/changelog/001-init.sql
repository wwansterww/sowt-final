--liquibase formatted sql

--changeset ansar:001
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       status VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE student_profiles (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  user_id BIGINT NOT NULL UNIQUE,
                                  student_number VARCHAR(50) NOT NULL UNIQUE,
                                  major VARCHAR(100) NOT NULL,
                                  study_year INT NOT NULL,
                                  CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE teacher_profiles (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  user_id BIGINT NOT NULL UNIQUE,
                                  department VARCHAR(100) NOT NULL,
                                  position VARCHAR(100) NOT NULL,
                                  CONSTRAINT fk_teacher_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE courses (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         code VARCHAR(50) NOT NULL UNIQUE,
                         title VARCHAR(255) NOT NULL,
                         description VARCHAR(1000),
                         credits INT NOT NULL,
                         teacher_id BIGINT NOT NULL,
                         CONSTRAINT fk_course_teacher FOREIGN KEY (teacher_id) REFERENCES teacher_profiles(id)
);

CREATE TABLE grades (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        student_id BIGINT NOT NULL,
                        course_id BIGINT NOT NULL,
                        value_int INT NOT NULL,
                        comment VARCHAR(500),
                        graded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_grade_student FOREIGN KEY (student_id) REFERENCES student_profiles(id),
                        CONSTRAINT fk_grade_course FOREIGN KEY (course_id) REFERENCES courses(id)
);
