-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS university;

-- Sử dụng cơ sở dữ liệu
USE university;

-- Bảng persons
CREATE TABLE IF NOT EXISTS persons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) NOT NULL
);

-- Bảng lecturers
CREATE TABLE IF NOT EXISTS lecturers (
    lecturer_id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE
);

-- Bảng students
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE
);

-- Bảng subjects
CREATE TABLE IF NOT EXISTS subjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(255) NOT NULL,
    credits INT NOT NULL,
    lecturer_id INT,
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id) ON DELETE SET NULL
);

-- Bảng grades
CREATE TABLE IF NOT EXISTS grades (
    student_id INT,
    subject_id INT,
    score DECIMAL(5, 2),
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE
);

-- Bảng enrollments
CREATE TABLE IF NOT EXISTS enrollments (
    student_id INT,
    subject_id INT,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE
);

-- Thêm dữ liệu mẫu
INSERT INTO persons (name, date_of_birth, gender) VALUES
('Doan Thi Hoa', '1995-06-15', 'Female'),
('Phan Van Tuan', '1993-08-22', 'Male'),
('Nguyen Thi Lan', '1991-12-03', 'Female'),
('Tran Van Minh', '1990-07-10', 'Male'),
('Le Thi Thao', '1994-03-27', 'Female');

INSERT INTO lecturers (person_id) VALUES
(1), (2);

INSERT INTO students (person_id) VALUES
(3), (4), (5);

INSERT INTO subjects (subject_name, credits, lecturer_id) VALUES
('Data Structures', 3, 1),
('Database Systems', 4, 2),
('Operating Systems', 3, 1),
('Network Fundamentals', 4, 2),
('Software Engineering', 3, 1);

INSERT INTO grades (student_id, subject_id, score) VALUES
(1, 1, 9.0),
(2, 2, 8.0),
(3, 3, 7.5);

INSERT INTO enrollments (student_id, subject_id) VALUES
(1, 1),
(2, 2),
(3, 3);