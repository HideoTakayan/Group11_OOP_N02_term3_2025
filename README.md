# Group11_OOP_N02_term3_2025

#Group 11: OOP Term 3 2025 K17

# Thành viên:

1. Đỗ Như Minh Hiếu - 23010291;
2. Phan Minh Trúc - 23010818;
3. Triệu Tuấn Duy -23010449

## Tiêu đề : Quản lý đại học

##Link Readme giới thiệu project

- Link:
  Link: https://hideotakayan.github.io/Group11_OOP_N02_term3_2025/

# Đối tượng

## Đối tượng

class Student {
String studentId;
String name;
String dateOfBirth;
String gender;
}
class Lecturer {
String lecturerId;
String name;
String dateOfBirth;
String gender;
}
class Subject {
String subjectId;
String subjectName;
int credit;
String lecturerId;
List<String> studentIds;
}

class Grade {
String studentId;
String subjectId;
double score;
}

## Xây dựng ứng dụng Quản lý đại học ( University Management )

# Yêu cầu:

- Giao diện : Java Spring Boot.
- Chức năng chính:
  Thêm, sửa, xóa, truy vấn Môn học, Học sinh, Giảng viên.y
  Đăng kí môn học, gán môn học cho giảng viên phụ trách.
  Cập nhật và truy vấn điểm của sinh viên cho từng môn học.

# Cụ thể:

- Thêm, sửa, xóa Môn học ( Subject )

* Liệt kê thông tin về môn học, có thể lọc theo tên môn học ( SubjectName ) hoặc mã môn học ( SubjectId )
  -> Có chức năng quản lý môn học.

- Thêm, sửa, xóa Giảng viên ( Lecturer ).

* Liệt kê thông tin về giảng viên, có thể tìm kiểm giảng viên qua LecturerId.
  -> Có chức năng gán môn học cho giảng viên phụ trách

- Thêm, sửa, xóa Học sinh ( Student ).

* Liệt kê thông tin về học sinh, có thể tìm kiểm học sinh qua studentId.
  -> Có chức năng gán môn học cho sinh viên ( Sinh viên có thể đăng kí môn học )

- Thêm, sửa, xóa Lớp học(Grade).

* Tra cứu thông tin về môn học qua SubjectID, liệt kê các sinh viên trong lớp qua studentId.
  -> Có chức năng gán sinh viên, môn học cho lớp học.
* Dữ liệu được lưu trữ xuống file nhị phân

- Cần tạo các lớp liên quan đến " môn học ", " giảng viên ", " học sinh " để đọc, ghi xuống 1 hay nhiều file.

* Khi làm việc với dữ liệu trong bộ nhớ, dữ liệu cần được lưu trữ dưới dạng các Collection tùy chọn như ArrayList, LinkedList, Map, ....

* Truy vấn và cập nhật điểm của sinh viên của từng môn học.

Nội dung 02:

01 sơ đồ Class Diagram của bài tập lớn

- Sơ đồ Class Diagram:
- ![Untitledllll](https://github.com/user-attachments/assets/b6ddcb33-e368-4b1a-a1a5-7c7e37983b71)

Nội dung 03:

03 sơ đồ Behavioural Diagram của bài tập lớn

- Sequence Diagram ![Ảnh chụp màn hình 2025-05-20 113942](https://github.com/user-attachments/assets/ae52c239-d422-4f8f-9c4b-05a7d7ea7ccb)
04 Thực hiện viết CRUD cho ít nhất 03 đối tượng trong bài tập lớn của các bạn

Cập nhật Wiki/ReadMe cho phần kiểm thử Test 
![Ảnh chụp màn hình 2025-05-24 164834](https://github.com/user-attachments/assets/b0ab778c-09b2-43b5-af44-b4db9d2cf0f8)

