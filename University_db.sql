-- tạo cơ sở dữ liệu
create database if not exists university;

-- sử dụng cơ sở dữ liệu
use university;

-- tạo bảng 'persons'
create table if not exists persons (
    id int auto_increment primary key,
    name varchar(100) not null,
    date_of_birth date not null,
    gender enum('Male', 'Female') not null
);

-- tạo bảng 'lecturers'
create table if not exists lecturers (
    lecturer_id int auto_increment primary key,
    person_id int not null,
    foreign key (person_id) references persons(id) on delete cascade
);

-- tạo bảng 'students'
create table if not exists students (
    student_id int auto_increment primary key,
    person_id int not null,
    foreign key (person_id) references persons(id) on delete cascade
);

-- bảng subjects với id tự sinh
create table if not exists subjects (
    subject_id int auto_increment primary key,
    subject_name varchar(255) not null,
    credits int not null,
    lecturer_id int,
    foreign key (lecturer_id) references lecturers(lecturer_id) on delete cascade
);

-- tạo bảng 'grades' tham chiếu trực tiếp đến subjects
create table if not exists grades (
    student_id int,
    subject_id int,
    score decimal(3, 2),
    primary key (student_id, subject_id),
    foreign key (student_id) references students(student_id) on delete cascade,
    foreign key (subject_id) references subjects(subject_id) on delete cascade
);

-- tạo bảng 'enrollments' tham chiếu trực tiếp đến subjects
create table if not exists enrollments (
    student_id int,
    subject_id int,
    primary key (student_id, subject_id),
    foreign key (student_id) references students(student_id) on delete cascade,
    foreign key (subject_id) references subjects(subject_id) on delete cascade
);

-- thêm dữ liệu vào bảng persons
insert into persons (name, date_of_birth, gender) values
('doan thi hoa', '1995-06-15', 'Female'),
('phan van tuan', '1993-08-22', 'Male'),
('nguyen thi lan', '1991-12-03', 'Female'),
('tran van minh', '1990-07-10', 'Male'),
('le thi thao', '1994-03-27', 'Female');

-- thêm 2 giảng viên
insert into lecturers (person_id) values
(1), (2);

-- thêm 3 sinh viên
insert into students (person_id) values
(3), (4), (5);

-- thêm 5 môn học
insert into subjects (subject_name, credits, lecturer_id) values
('data structures', 3, 1),
('database systems', 4, 2),
('operating systems', 3, 1),
('network fundamentals', 4, 2),
('software engineering', 3, 1);

-- thêm điểm cho 3 sinh viên (tham chiếu đến subjects)
insert into grades (student_id, subject_id, score) values
(1, 1, 9.0),
(2, 2, 8.0),
(3, 3, 7.5);

-- thêm đăng ký môn học
insert into enrollments (student_id, subject_id) values
(1, 1),
(2, 2),
(3, 3);
