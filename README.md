#Group11_OOP_N02_term3_2025
👨‍🎓 Group 11: OOP Term 3 2025 – K17
🧑‍🤝‍🧑 Thành viên:  
Đỗ Như Minh Hiếu – 23010291  
Phan Minh Trúc – 23010818  
Triệu Tuấn Duy – 23010449  
📌 Tiêu đề: Quản lý đại học  
🔗 Link README giới thiệu project
https://hideotakayan.github.io/Group11_OOP_N02_term3_2025/

#🎯 Đối tượng
✅ Các lớp đối tượng  
person (
    person_id varchar(50) primary key,
    name varchar(100),
    address varchar(255),
    email varchar(100) unique,
    date_of_birth date not null,
    gender enum('Nam', 'Nữ') not null
);  
student_class (
    class_id varchar(50) primary key,
    class_name varchar(100)
);  
student (
    student_id varchar(50) primary key,
    person_id varchar(50),
    class_id varchar(50),
	class_name VARCHAR(100),
    foreign key (person_id) references person(person_id) on delete cascade,
    foreign key (class_id) references student_class(class_id) on delete cascade
);  
lecturer (
    lecturer_id varchar(50) primary key,
    person_id varchar(50),
    department varchar(100),
    foreign key (person_id) references person(person_id) on delete cascade
);  
subject (
    subject_id varchar(50) primary key,
    subject_name varchar(255) not null,
    credits int not null,
    lecturer_id varchar(50),
    foreign key (lecturer_id) references lecturer(lecturer_id) on delete cascade
);

## 🏗️ Xây dựng ứng dụng Quản lý đại học (University Management System)
# ⚙️ Yêu cầu:
Giao diện: Java Spring Boot
Các chức năng chính:
 - Thêm, sửa, xoá, truy vấn Môn học, Sinh viên, Giảng viên, Lịch học, Lịch thi, Lớp.  
 - Quản lý môn học, quản lý lịch thi, đăng kí môn học.  
 - Thực hiện giao diện người dùng với Học sinh và Giảng viên.  
# 📋 Cụ thể:
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
#📚 Nội dung 02 – Class Diagram  
01 sơ đồ Class Diagram của bài tập lớn  
- Sơ đồ Class Diagram:
- ![Untitledllll](https://github.com/user-attachments/assets/b6ddcb33-e368-4b1a-a1a5-7c7e37983b71)
#🎬 Nội dung 03 – Behavioural Diagram  
- Sequence Diagram ![Ảnh chụp màn hình 2025-05-20 113942](https://github.com/user-attachments/assets/ae52c239-d422-4f8f-9c4b-05a7d7ea7ccb)
# Các phương thức hiện có:  
1. Phương thức đăng kí môn học, hủy đăng kí môn học  
2. Phương thức hiển thị,tìm kiếm lịch thi  
3. Phương thức sửa đổi thông tin cá nhân.  
# Lưu đồ phương thức đăng kí môn học ( Phương thức chính ):
![luudothuattoan](https://github.com/user-attachments/assets/4a3a7d8e-fbe3-446e-89c8-60d6e76fc119)
Quản lý đăng kí môn học: Cho phép sv đăng kí môn học, Kiểm tra xem học sinh đã đki được môn học hay chưa, Hủy đăng kí môn học  
Phân chia công việc:  
## Trần Tuấn Duy: Hoàn thành phương thức đăng kí môn học  
## Phan Minh Trúc: Hoàn thành phương thức kiểm tra xem học sinh đã đăng kí môn học chưa.  
## Đỗ Như Minh HIếu: Hoàn thành phương thức hủy đăng kí môn học  
❌ Phương thức huỷ đăng ký môn học  
🧩 Ý tưởng  
Phương thức này được sử dụng khi sinh viên muốn hủy đăng ký một lớp học đã chọn trước đó, vì các lý do như:  
 - Chọn nhầm lớp.  
 - Trùng lịch học.  
 - Thay đổi kế hoạch học tập.  
 - Lớp bị hủy hoặc thay đổi giảng viên.  
🧾 Mô tả phương thức unregisterClass  
@PostMapping("/unregister-class")  
public String unregisterClass(@RequestParam("registerId") String registerId, HttpSession session) {  
    String email = (String) session.getAttribute("userEmail");  
    if (email == null)  
        return "redirect:/login";  
    try {  
        registerDao.deleteRegisterClass(registerId);  
    } catch (Exception e) {  
    }  
    return "redirect:/student/registered-classes";  
}  
🔍 Chức năng  
Huỷ lớp học đã đăng ký cho sinh viên hiện tại (đã đăng nhập).  
Xóa bản ghi tương ứng với registerId trong bảng register_class_section.  
🪜 Các bước xử lý  
Kiểm tra sinh viên đã đăng nhập hay chưa (userEmail trong session).  
Nếu chưa: chuyển hướng về /login.  
Nếu đã đăng nhập:  
Gọi registerDao.deleteRegisterClass(registerId) để xoá bản ghi đăng ký.  
Sau khi thực hiện, chuyển về trang /student/registered-classes.  




