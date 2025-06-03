-- Tạo cơ sở dữ liệu
CREATE DATABASE
IF NOT EXISTS University;
-- Sử dụng cơ sở dữ liệu
USE University;

-- Tạo bảng 'students' (phù hợp với class Student)
CREATE TABLE
IF NOT EXISTS students
(
    student_id INT PRIMARY KEY,
    name VARCHAR
(100) NOT NULL,
    gender ENUM
('Male', 'Female', 'Other') NOT NULL,
    date_of_birth DATE NOT NULL
);

-- Tạo bảng 'lecturers' (phù hợp với class Lecturers)
CREATE TABLE
IF NOT EXISTS lecturers
(
    lecturer_id INT PRIMARY KEY,
    name VARCHAR
(100) NOT NULL,
    gender ENUM
('Male', 'Female', 'Other') NOT NULL,
    date_of_birth DATE NOT NULL
);

-- Tạo bảng 'subjects' (phù hợp với class Subject)
CREATE TABLE
IF NOT EXISTS subjects
(
    subject_id INT PRIMARY KEY,
    subject_name VARCHAR
(255) NOT NULL,
    credit INT NOT NULL,
    lecturer_id INT,
    FOREIGN KEY
(lecturer_id) REFERENCES lecturers
(lecturer_id) ON
DELETE
SET NULL
);

-- Giữ nguyên bảng 'enrollments' từ SQL cũ
CREATE TABLE
IF NOT EXISTS enrollments
(
    student_id INT,
    subject_id INT,
    PRIMARY KEY
(student_id, subject_id),
    FOREIGN KEY
(student_id) REFERENCES students
(student_id) ON
DELETE CASCADE,
    FOREIGN KEY (subject_id)
REFERENCES subjects
(subject_id) ON
DELETE CASCADE
);

-- Tạo bảng 'grades' (phù hợp với class Grade)
CREATE TABLE
IF NOT EXISTS grades
(
    student_id INT,
    subject_id INT,
    score DECIMAL
(5,2) NOT NULL,
    PRIMARY KEY
(student_id, subject_id),
    FOREIGN KEY
(student_id) REFERENCES students
(student_id) ON
DELETE CASCADE,
    FOREIGN KEY (subject_id)
REFERENCES subjects
(subject_id) ON
DELETE CASCADE
);

-- Thêm dữ liệu mẫu
INSERT INTO students
    (student_id, name, gender, date_of_birth)
VALUES
    (1, 'Nguyen Van A', 'Male', '2000-01-01'),
    (2, 'Tran Thi B', 'Female', '2001-02-02'),
    (3, 'Le Van C', 'Male', '1999-03-03');

INSERT INTO lecturers
    (lecturer_id, name, gender, date_of_birth)
VALUES
    (101, 'Professor X', 'Male', '1980-01-01'),
    (102, 'Professor Y', 'Female', '1985-05-15');

INSERT INTO subjects
    (subject_id, subject_name, credit, lecturer_id)
VALUES
    (1001, 'Mathematics', 3, 101),
    (1002, 'Physics', 4, 101),
    (1003, 'Chemistry', 3, 102);

-- Thêm dữ liệu vào bảng enrollments
INSERT INTO enrollments
    (student_id, subject_id)
VALUES
    (1, 1001),
    (1, 1002),
    (2, 1001),
    (3, 1003);

-- Thêm dữ liệu vào bảng grades
INSERT INTO grades
    (student_id, subject_id, score)
VALUES
    (1, 1001, 8.5),
    (1, 1002, 7.5),
    (2, 1001, 9.0),
    (3, 1003, 8.0);