package University_Management.test;

import University_Management.src.model.User;

public class UsersTest {
    public static void main(String[] args) {
        User user = new User(1, "Trieu Tuan Duy", "123456", "duyoop@gmail.com", "sinh vien");
        System.out.println("=== Người dùng: ===");
        System.out.println("Tên người dùng: " + user.getUsername());
        System.out.println("Mật khẩu: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Vai trò: " + user.getRole());
    }
}
