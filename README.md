# Group11_OOP_N02_term3_2025
#Group 11: OOP Term 3 2025 K17
# Thành viên:
1. Đỗ Như Minh Hiếu - 23010291;
2. Phan Minh Trúc - 23010818;
3. Triệu Tuấn Duy -23010449
## Tiêu đề : Quản lý đại học

##Link Readme giới thiệu project
Link: https://hideotakayan.github.io/Group11_OOP_N02_term3_2025/
## Đối tượng
Học sinh:
class Students {
Student_id;
Name;
Date_of_birth;
Gender;
}
Giảng viên:
Class Lecturers{
Lecturer_ID;
Name;
Date_of_birth;
Gender;
}
Môn học:
Class Subject(
Subject_ID;
Subject_Name;
Credit ;
Lecturer_ID;
Student_ID;
}
##
Xây dựng ứng dụng Quản lý đại học ( University Management )

Yêu cầu:
- Giao diện Java Spring Boot.
- Có chức năng quản lý đại học

+ Thêm, sửa, xóa Môn học ( Subject )

+ Liệt kê thông tin về môn học, có thể lọc theo tên môn học ( SubjectName ) hoặc mã môn học ( SubjectId )
- Có chức năng quản lý môn học.

+ Thêm, sửa, xóa Giảng viên ( Lecturer ).
- Liệt kê thông tin về giảng viên, có thể tìm kiểm giảng viên qua LecturerId.
- Có chức năng gán môn học cho giảng viên phụ trách

+ Thêm, sửa, xóa Học sinh ( Student ).
- Liệt kê thông tin về học sinh, có thể tìm kiểm học sinh qua studentId.
- Có chức năng gán môn học cho sinh viên ( Sinh viên có thể đăng kí môn học ) 

- Dữ liệu được lưu trữ xuống file nhị phân

+ Cần tạo các lớp liên quan đến " môn học ", " giảng viên ", " học sinh " để đọc, ghi xuống 1 hay nhiều file.

- Khi làm việc với dữ liệu trong bộ nhớ, dữ liệu cần được lưu trữ dưới dạng các Collection tùy chọn như ArrayList, LinkedList, Map, ....

- Truy vấn và cập nhật điểm của sinh viên của từng môn học.

Nội dung 02:

01 sơ đồ Class Diagram của bài tập lớn


Nội dung 03:


03 sơ đồ Behavioural Diagram của bài tập lớn
+ Sequence Diagram
+ Or Activity Diagram

