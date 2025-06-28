🎓 Group11_OOP_N02_Term3_2025 – Quản Lý Đại Học
👥 Thành viên nhóm 11 – Lập trình hướng đối tượng K17
STT	Họ và tên	Mã sinh viên
1	Đỗ Như Minh Hiếu	23010291
2	Phan Minh Trúc	23010818
3	Triệu Tuấn Duy	23010449

🔗 Giới thiệu dự án
📄 Link README giới thiệu chi tiết:
https://hideotakayan.github.io/Group11_OOP_N02_term3_2025/

🏫 Mục tiêu đề tài – Quản lý Đại học (University Management System)
🖥 Giao diện:
Ứng dụng được xây dựng bằng Java Spring Boot với giao diện web.

🔧 Chức năng chính:
Quản lý Môn học, Sinh viên, Giảng viên: Thêm, sửa, xoá, tìm kiếm.

Đăng ký môn học và gán môn học cho giảng viên.

Cập nhật và tra cứu điểm sinh viên cho từng môn học.

🧩 Các lớp đối tượng chính
java
Sao chép
Chỉnh sửa
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
🗂️ Yêu cầu chi tiết
📚 Quản lý môn học (Subject)
Thêm, sửa, xoá môn học.

Liệt kê thông tin môn học, lọc theo mã hoặc tên môn.

Gán giảng viên phụ trách cho từng môn học.

👨‍🏫 Quản lý giảng viên (Lecturer)
Thêm, sửa, xoá giảng viên.

Tìm kiếm giảng viên theo mã giảng viên.

Quản lý môn học phụ trách của từng giảng viên.

👨‍🎓 Quản lý học sinh (Student)
Thêm, sửa, xoá học sinh.

Tìm kiếm học sinh theo mã sinh viên.

Cho phép sinh viên đăng ký các môn học.

🏷️ Quản lý điểm (Grade)
Quản lý điểm sinh viên theo từng môn học.

Lưu dữ liệu dưới dạng file nhị phân.

Sử dụng các cấu trúc dữ liệu ArrayList, LinkedList, Map, ...

📊 Sơ đồ Class Diagram


📈 Sơ đồ Sequence Diagram


⚙️ Các phương thức chính đã triển khai
✅ Phương thức đăng ký môn học.

🔍 Phương thức hiển thị và tìm kiếm lịch thi.

📖 Phương thức tra cứu điểm và thông tin sinh viên bằng mã sinh viên.

🔄 Lưu đồ thuật toán: Đăng ký môn học


🧠 Phân chia công việc
Thành viên	Công việc đảm nhiệm
Trần Tuấn Duy	Phương thức đăng ký môn học
Phan Minh Trúc	Kiểm tra sinh viên đã đăng ký môn học hay chưa
Đỗ Như Minh Hiếu	Phương thức huỷ đăng ký môn học

❌ Phương thức huỷ đăng ký môn học
🧩 Ý tưởng
Phương thức này được sử dụng khi sinh viên muốn hủy đăng ký một lớp học đã chọn trước đó, vì các lý do như:

Chọn nhầm lớp.

Trùng lịch học.

Thay đổi kế hoạch học tập.

Lớp bị hủy hoặc thay đổi giảng viên.

🧾 Mô tả phương thức unregisterClass
java
Sao chép
Chỉnh sửa
@PostMapping("/unregister-class")
public String unregisterClass(@RequestParam("registerId") String registerId, HttpSession session) {
    String email = (String) session.getAttribute("userEmail");
    if (email == null)
        return "redirect:/login";

    try {
        registerDao.deleteRegisterClass(registerId);
    } catch (Exception e) {
        // log nếu cần
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

🎯 Kết luận
Phương thức unregisterClass() giúp sinh viên chủ động quản lý lịch học của mình bằng cách hủy bỏ các lớp học không còn phù hợp, góp phần tối ưu kế hoạch học tập.
