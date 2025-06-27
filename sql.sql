use defaultdb;
create table if not exists person (
    person_id varchar(50) primary key,
    name varchar(100),
    address varchar(255),
    email varchar(100) unique,
    date_of_birth date not null,
    gender enum('Nam', 'Nữ') not null
);
insert into person (person_id, name, address, email, date_of_birth, gender) values
('p001', 'Nguyễn Văn A', 'Hà Nội', 'a@student.com', '2003-01-01', 'Nam'),
('p002', 'Trần Thị B', 'HCM', 'b@student.com', '2003-02-02', 'Nữ'),
('p003', 'Lê Văn C', 'Đà Nẵng', 'c@student.com', '2002-03-03', 'Nam'),
('p004', 'Phạm Thị D', 'Huế', 'd@student.com', '2001-04-04', 'Nữ'),
('p005', 'Ngô Văn E', 'Hải Phòng', 'e@student.com', '2002-05-05', 'Nam'),
('p006', 'Phạm Văn F', 'Hà Nội', 'f@lecturer.com', '1980-06-06', 'Nam'),
('p007', 'Lê Thị G', 'HCM', 'g@lecturer.com', '1981-07-07', 'Nữ'),
('p008', 'Trần Văn H', 'Đà Nẵng', 'h@lecturer.com', '1979-08-08', 'Nam'),
('p009', 'Nguyễn Thị I', 'Huế', 'i@lecturer.com', '1982-09-09', 'Nữ'),
('p010', 'Hoàng Văn K', 'Hải Phòng', 'k@lecturer.com', '1983-10-10', 'Nam');

create table if not exists student_class (
    class_id varchar(50) primary key,
    class_name varchar(100)
);

insert into student_class (class_id, class_name) values
('cls01', 'CNTT9'),
('cls02', 'CNTT10'),
('cls03', 'CNTT11'),
('cls04', 'KTPM1'),
('cls05', 'ANM1');
-- student
create table if not exists student (
    student_id varchar(50) primary key,
    person_id varchar(50),
    class_id varchar(50),
	class_name VARCHAR(100),
    foreign key (person_id) references person(person_id) on delete cascade,
    foreign key (class_id) references student_class(class_id)
);

insert into student (student_id, person_id, class_id, class_name) values
('s001', 'p001', 'cls01', 'CNTT9'),
('s002', 'p002', 'cls02', 'CNTT10'),
('s003', 'p003', 'cls03', 'CNTT11'),
('s004', 'p004', 'cls04', 'KTPM1'),
('s005', 'p005', 'cls05', 'ANM1');


-- lecturer
create table if not exists lecturer (
    lecturer_id varchar(50) primary key,
    person_id varchar(50),
    department varchar(100),
    foreign key (person_id) references person(person_id) on delete cascade
);

insert into lecturer (lecturer_id, person_id, department) values
('l001', 'p006', 'Khoa học máy tính'),
('l002', 'p007', 'Hệ thống thông tin'),
('l003', 'p008', 'Công nghệ phần mềm'),
('l004', 'p009', 'Khoa học dữ liệu'),
('l005', 'p010', 'An toàn thông tin');

-- subject
create table if not exists subject (
    subject_id varchar(50) primary key,
    subject_name varchar(255) not null,
    credits int not null,
    lecturer_id varchar(50),
    foreign key (lecturer_id) references lecturer(lecturer_id) on delete cascade
);

insert into subject (subject_id, subject_name, credits, lecturer_id) values
('sub01', 'Lập trình Java', 3, 'l001'),
('sub02', 'Cơ sở dữ liệu', 2, 'l002'),
('sub03', 'Cấu trúc dữ liệu', 3, 'l003'),
('sub04', 'Mạng máy tính', 2, 'l004'),
('sub05', 'Phân tích thiết kế HTTT', 3, 'l005');

-- class_section
create table if not exists class_section (
    class_id varchar(50) primary key,
    class_name varchar(100),
    subject_id varchar(50),
    lecturer_id varchar(50),
    foreign key (subject_id) references subject(subject_id) on delete cascade,
    foreign key (lecturer_id) references lecturer(lecturer_id) on delete cascade
);

insert into class_section (class_id, class_name, subject_id, lecturer_id) values
('cs001', 'Java 01', 'sub01', 'l001'),
('cs002', 'Java 02', 'sub01', 'l001'),
('cs003', 'CSDL 01', 'sub02', 'l002'),
('cs004', 'CTDL 01', 'sub03', 'l003'),
('cs005', 'MMT 01', 'sub04', 'l004');

-- register_class
create table if not exists register_class (
    register_id varchar(50) primary key,
    student_id varchar(50),
    class_id varchar(50),
    student_name varchar(100),
    class_name varchar(100),
    foreign key (student_id) references student(student_id),
    foreign key (class_id) references class_section(class_id)
);

insert into register_class (register_id, student_id, class_id) values
('r001', 's001', 'cs001'),
('r002', 's001', 'cs003'),
('r003', 's002', 'cs004'),
('r004', 's003', 'cs001'),
('r005', 's005', 'cs002');

-- exam_schedule
create table if not exists exam_schedule (
    exam_id serial primary key,
    subject_id varchar(50),
    subject_name varchar(255) not null,
    exam_date date,
    start_time time,
    duration_minutes int,
    exam_format varchar(100),
    location varchar(100),
    foreign key (subject_id) references subject(subject_id)
);

insert into exam_schedule (subject_id,subject_name, exam_date, start_time, duration_minutes, exam_format, location) values
('sub01', 'Lập trình Java', '2025-07-10', '08:00', 60, 'Trắc nghiệm', 'A101'),
('sub02', 'Cơ sở dữ liệu', '2025-07-12', '09:00', 90, 'Tự luận', 'B102'),
('sub03','Cấu trúc dữ liệu', '2025-07-13', '13:00', 75, 'Thực hành', 'C203'),
('sub04', 'Mạng máy tính', '2025-07-14', '10:00', 60, 'Trắc nghiệm', 'A104'),
('sub05', 'Phân tích thiết kế HTTT', '2025-07-15', '14:00', 90, 'Tự luận + Trắc nghiệm', 'B105');

-- enviroment
create table if not exists enviroment (
    enviroment_id varchar(50) primary key,
    class_id varchar(50),
    class_name varchar(100),
    subject_name varchar(255),
    lecturer_name varchar(100),
    location varchar(100),
    day_of_week varchar(50),  -- ví dụ: 'Thứ 2 - 2025-07-01'
    time varchar(20),
    foreign key (class_id) references class_section(class_id)
);



insert into enviroment (enviroment_id, class_id, class_name, subject_name, lecturer_name, location, day_of_week, time) values
-- Môn: Lập trình Java
('env001', 'cs001', 'Java 01', 'Lập trình Java', 'Phạm Văn F', 'A6-203', 'Thứ 2 - 2025-07-01', '08:00 - 10:00'),
('env002', 'cs001', 'Java 01', 'Lập trình Java', 'Phạm Văn F', 'A6-203', 'Thứ 3 - 2025-07-02', '10:00 - 12:00'),
('env003', 'cs002', 'Java 02', 'Lập trình Java', 'Phạm Văn F', 'A6-204', 'Thứ 5 - 2025-07-04', '14:00 - 16:00'),

-- Môn: Cơ sở dữ liệu
('env004', 'cs003', 'CSDL 01', 'Cơ sở dữ liệu', 'Lê Thị G', 'B4-202', 'Thứ 2 - 2025-07-01', '13:00 - 15:00'),
('env005', 'cs003', 'CSDL 01', 'Cơ sở dữ liệu', 'Lê Thị G', 'B4-202', 'Thứ 4 - 2025-07-03', '10:00 - 12:00'),
('env006', 'cs003', 'CSDL 01', 'Cơ sở dữ liệu', 'Lê Thị G', 'B4-202', 'Thứ 6 - 2025-07-05', '07:30 - 09:30'),

-- Môn: Cấu trúc dữ liệu
('env007', 'cs004', 'CTDL 01', 'Cấu trúc dữ liệu', 'Trần Văn H', 'C1-101', 'Thứ 3 - 2025-07-02', '08:00 - 10:00'),
('env008', 'cs004', 'CTDL 01', 'Cấu trúc dữ liệu', 'Trần Văn H', 'C1-101', 'Thứ 5 - 2025-07-04', '13:00 - 15:00'),
('env009', 'cs004', 'CTDL 01', 'Cấu trúc dữ liệu', 'Trần Văn H', 'C1-101', 'Thứ 6 - 2025-07-05', '15:00 - 17:00'),

-- Môn: Mạng máy tính
('env010', 'cs005', 'MMT 01', 'Mạng máy tính', 'Nguyễn Thị I', 'D5-305', 'Thứ 2 - 2025-07-01', '10:00 - 12:00'),
('env011', 'cs005', 'MMT 01', 'Mạng máy tính', 'Nguyễn Thị I', 'D5-305', 'Thứ 4 - 2025-07-03', '14:00 - 16:00'),
('env012', 'cs005', 'MMT 01', 'Mạng máy tính', 'Nguyễn Thị I', 'D5-305', 'Thứ 6 - 2025-07-05', '08:00 - 10:00'),

-- Môn: Phân tích thiết kế HTTT (dùng chung giảng viên với cs005 nếu cần)
('env013', 'cs002', 'Java 02', 'Phân tích thiết kế HTTT', 'Hoàng Văn K', 'E3-201', 'Thứ 2 - 2025-07-01', '13:00 - 15:00'),
('env014', 'cs002', 'Java 02', 'Phân tích thiết kế HTTT', 'Hoàng Văn K', 'E3-201', 'Thứ 3 - 2025-07-02', '09:00 - 11:00'),
('env015', 'cs002', 'Java 02', 'Phân tích thiết kế HTTT', 'Hoàng Văn K', 'E3-201', 'Thứ 5 - 2025-07-04', '15:00 - 17:00');

CREATE TABLE users (
    user_id varchar(50) primary key,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    FOREIGN KEY (email) REFERENCES person(email) ON DELETE CASCADE
);
-- Tạo user cho sinh viên
insert into users (user_id, email, password, role) values
('u001', 'a@student.com', '123456', 'student'),
('u002', 'b@student.com', '123456', 'student'),
('u003', 'c@student.com', '123456', 'student'),
('u004', 'd@student.com', '123456', 'student'),
('u005', 'e@student.com', '123456', 'student'),
('u006', 'f@lecturer.com', '123456', 'lecturer'),
('u007', 'g@lecturer.com', '123456', 'lecturer'),
('u008', 'h@lecturer.com', '123456', 'lecturer'),
('u009', 'i@lecturer.com', '123456', 'lecturer'),
('u010', 'k@lecturer.com', '123456', 'lecturer');
