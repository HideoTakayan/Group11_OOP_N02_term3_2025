package University_Management.test;

import University_Management.src.model.User;

public class UserTest {
    public static void main(String[] args) {
        User user = new User(1, "Do_Hieu", "123456", "mhieu@gmail.com", "student");

        System.out.println("=== UserTest ===");
        System.out.println("Mã người dùng: " + user.getUserId());
        System.out.println("Tên đăng nhập: " + user.getUsername());
        System.out.println("Mật khẩu: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Vai trò: " + user.getRole());
    }
}
