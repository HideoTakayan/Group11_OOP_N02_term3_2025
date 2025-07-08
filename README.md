# **University Management**  
<p align="center">
  <img src="https://miro.medium.com/v2/0*9XI4DHbHZoGzMVfD.gif" alt="VAK" width="600"/>
</p>

## **I. Thành viên**
Group11_OOP_NO2_term3_2025
| Họ tên            | MSSV      |
|-------------------|-----------|
| Đỗ Như Minh Hiếu  | 23010291  |
| Phan Minh Trúc    | 23010818  |
| Triệu Tuấn Duy    | 23010449  |

---

## **II. Giới thiệu**
Một ứng dụng web dựa trên CRUD để quản lý đại học – được phát triển bằng Spring Boot MVC + Thymeleaf + MySQL. Hệ thống này cho phép kiểm soát toàn diện các đối tượng như sinh viên, giảng viên, lớp học, lịch học và thể hiện trực quan 

---
## **III. Tính năng**
Hệ thống hỗ trợ các chức năng quản lý, chỉnh sửa và tra cứu:
- Sinh viên
- Giảng viên
- Môn học
- Lớp học
- Lớp học phần
- Lịch học
- Lịch thi
- Đăng kí môn học
- Phân quyền người dùng ( phân quyền admin/lecturer/student )  
Thông qua một giao diện bảng điều khiển trực quan, người dùng có thể dễ dàng quản lý thông tin học tập, lịch học, lịch thi và dữ liệu cá nhân.

---
## **IV. Thiết kế cơ sở dữ liệu**  
### 1. ClassSection  
_Quản lý thông tin các lớp học phần_

| Trường        | Kiểu    | Mô tả                                |
|---------------|---------|--------------------------------------|
| classId       | String  | Mã lớp học phần                      |
| className     | String  | Tên lớp học phần                     |
| subjectId     | String  | Mã môn học                           |
| subjectName   | String  | Tên môn học                          |
| lecturerId    | String  | Mã giảng viên                        |
| lecturerName  | String  | Tên giảng viên                       |

---

### 2. Environment  
_Quản lý thời khóa biểu lớp học_

| Trường        | Kiểu    | Mô tả                                  |
|---------------|---------|----------------------------------------|
| enviromentId  | String  | Mã lịch học                            |
| classId       | String  | Mã lớp học phần                        |
| className     | String  | Tên lớp học phần                       |
| subjectName   | String  | Tên môn học                            |
| lecturerName  | String  | Tên giảng viên                         |
| location      | String  | Địa điểm học                           |
| dayOfWeek     | String  | Thứ và ngày học (VD: Thứ 2 - ...)      |
| time          | String  | Khung giờ học                          |

---

### 3. ExamSchedule  
_Quản lý lịch thi_

| Trường           | Kiểu    | Mô tả                                |
|------------------|---------|--------------------------------------|
| subjectName      | String  | Tên môn thi                          |
| examDate         | Date    | Ngày thi                             |
| startTime        | Time    | Giờ bắt đầu                          |
| durationMinutes  | int     | Số phút thi                          |
| examFormat       | String  | Hình thức thi (Trắc nghiệm, Tự luận...) |
| location         | String  | Địa điểm thi                         |

---

### 4. Lecturer  
_Đại diện cho giảng viên (kế thừa từ Person)_

| Trường       | Kiểu    | Mô tả                    |
|--------------|---------|--------------------------|
| lecturerId   | String  | Mã giảng viên            |
| department   | String  | Khoa/phòng ban phụ trách |

---

### 5. Person *(abstract)*  
_Thông tin cá nhân dùng chung cho Student và Lecturer_

| Trường        | Kiểu    | Mô tả                    |
|---------------|---------|--------------------------|
| personId      | String  | Mã định danh cá nhân     |
| name          | String  | Họ và tên                |
| address       | String  | Địa chỉ                  |
| email         | String  | Email liên hệ            |
| dateOfBirth   | Date    | Ngày sinh                |
| gender        | String  | Giới tính                |

---

### 6. RegisterClassSection  
_Đăng ký môn học cho sinh viên_

| Trường           | Kiểu    | Mô tả                      |
|------------------|---------|----------------------------|
| registerId       | String  | Mã đăng ký học phần        |
| studentId        | String  | Mã sinh viên               |
| classSectionId   | String  | Mã lớp học phần            |
| name             | String  | Tên sinh viên              |
| className        | String  | Tên lớp học phần           |

---

### 7. Student  
_Đại diện cho sinh viên (kế thừa từ Person)_

| Trường      | Kiểu    | Mô tả                     |
|-------------|---------|---------------------------|
| studentId   | String  | Mã sinh viên              |
| classId     | String  | Mã lớp sinh viên          |
| className   | String  | Tên lớp sinh viên         |

---

### 8. StudentClass  
_Thông tin lớp học của sinh viên_

| Trường      | Kiểu    | Mô tả                     |
|-------------|---------|---------------------------|
| classId     | String  | Mã lớp sinh viên          |
| className   | String  | Tên lớp sinh viên         |

---

### 9. Subject  
_Thông tin môn học_

| Trường       | Kiểu    | Mô tả                    |
|--------------|---------|--------------------------|
| subjectId    | String  | Mã môn học               |
| subjectName  | String  | Tên môn học              |
| credits      | int     | Số tín chỉ               |
| lecturerId   | String  | Mã giảng viên phụ trách  |

---

### 10. User  
_Tài khoản người dùng hệ thống_

| Trường    | Kiểu    | Mô tả                                |
|-----------|---------|--------------------------------------|
| userID    | String  | Mã người dùng (UUID)                 |
| email     | String  | Email đăng nhập                      |
| password  | String  | Mật khẩu                             |
| role      | String  | Vai trò người dùng (student/lecturer)|

---
## **V. Các công nghệ đã sử dụng** 
- **Frontend:** HTML, CSS, Thymeleaf  
  <img src="https://skillicons.dev/icons?i=html,css,spring" />  
- **Backend:** Java, Spring Boot, Maven  
  <img src="https://skillicons.dev/icons?i=java,spring,maven" />    
- **Database:** MySQL  
  <img src="https://skillicons.dev/icons?i=postgres,mysql" />  
- **Version Control:** Github  
  <img src="https://skillicons.dev/icons?i=git,github" />   
- **IDE:** VsCode  
  <img src="https://skillicons.dev/icons?i=vscode" />
  
---
## **VI. Diagram**  
 – Class Diagram  
![image](https://github.com/user-attachments/assets/65d37735-bf62-4767-a3f9-93db669214a0)

Behavioural Diagram   
- Sequence Diagram
![image](https://github.com/user-attachments/assets/3bef8744-8d65-46c8-8b25-ad0f59342d8e)
![image](https://github.com/user-attachments/assets/79c2bec9-5c41-4fac-9037-4b7811e442cc)
### Chức năng đăng nhập/đăng ký
![image](https://github.com/user-attachments/assets/7809ed5f-412a-4fc0-ad44-7cf71ea4cf85)
### Các chức năng Admin
![image](https://github.com/user-attachments/assets/f8fac50d-0ddf-4833-96eb-b56eb23eb85c)
### Các chức năng của sinh viên
![image](https://github.com/user-attachments/assets/29878661-1007-49e5-813e-8e82351b2fbd)
### Các chức năng giảng viên
![image](https://github.com/user-attachments/assets/d17ca0d7-3f8b-4de6-a689-d949c9bc8c53)
--- 
## **VII. Tính năng hệ thống**  
### 1. Admin:
Có toàn quyền sử dụng và thao tác trên hệ thống, bao gồm các chức năng như: quản lý và cập nhật thông tin giảng viên, sinh viên, môn học,...; tạo và chỉnh sửa lịch thi, lịch học và các hoạt động liên quan đến quản lý học vụ.

 ---
 ### 2. Lecturer: 
Có thể xem và cập nhật thông tin cá nhân, đồng thời quản lý các học phần phụ trách, bao gồm: quản lý danh sách sinh viên, lịch học, và thiết lập, chỉnh sửa lịch thi cho từng học phần đang đảm nhiệm.

---
### 3. Student
Có thể xem và chỉnh sửa thông tin cá nhân, theo dõi danh sách các học phần đã đăng ký cùng với lịch học tương ứng, đồng thời thực hiện đăng ký học phần mới nếu cần.

---
## **VIII. Hướng dẫn cài đặt và sử dụng**  
### Yêu cầu  
- JDK 24  
- MySQL  
- VsCode  
### Cài đặt  
- Clone dự án về máy: https://github.com/HideoTakayan/Group11_OOP_N02_term3_2025.git  
  
- Di chuyển vào thư mục dự án: cd Group11_OOP_N02_term3_2025  
  
- Chạy ứng dụng: ./mvnw spring-boot:run     # (Linux/macOS)  
hoặc  ./mvnw.cmd spring-boot:run # (Windows)
  
- Truy cập ứng dụng: https://localhost:8080  

---
## **VIII. Giao diện hệ thống**  
- Giao diện đăng nhập:  
![image](https://github.com/user-attachments/assets/91f4150c-0804-4970-9410-b79b833f8711)
- Giao diện đăng ký:
![image](https://github.com/user-attachments/assets/45179a21-2952-4ef0-beae-012e63cdc6c6)
- Giao diện Admin:
![image](https://github.com/user-attachments/assets/b630fd95-5c01-44c2-8e4a-6a2ded142e0d)
- Giao diện Student:
![image](https://github.com/user-attachments/assets/9731ef0c-6b93-4091-af4c-57f8bcbc3097)
- Giao diện Lecturer:
![image](https://github.com/user-attachments/assets/7a546c60-c591-4d93-b249-6f0990c63a4f)

---
## **IX. Kế hoạch phát triển trong tương lai**  
- Thêm đối tượng Grade để hỗ trợ chức năng gán điểm cho sinh viên.
- Phân loại môn học thành:
✅ Môn đã hoàn thành  
⏳ Môn chưa hoàn thành  
- Thêm thông báo "Trượt môn" hoặc "Đạt môn" dựa trên kết quả điểm.
- Tính toán và hiển thị GPA (Điểm trung bình tích lũy) cho từng sinh viên.
- Phân chia môn học theo học kỳ, giúp theo dõi tiến độ học tập theo từng giai đoạn.
