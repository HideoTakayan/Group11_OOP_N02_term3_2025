package University_Management.test;

import University_Management.src.model.Users;

public class UserTest {
    public static void main(String[] args) {
        Users user = new Users(1, "Do_Hieu", "123456", "mhieu@gmail.com", "student");
        Users user2 = new Users(1, "Trieu Tuan Duy", "123456", "duyoop@gmail.com", "sinh vien");
        Users user3 = new Users(1, "Phan Minh Truc", "1234567", "truc@gmail.com", "sinh vien");
        System.out.println("=== UserTest ===");
        System.out.println("Mã người dùng: " + user.getUserId());
        System.out.println("Tên đăng nhập: " + user.getUsername());
        System.out.println("Mật khẩu: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Vai trò: " + user.getRole());

        System.out.println("=== UserTest ===");

        System.out.println("Mã người dùng: " + user2.getUserId());
        System.out.println("Tên đăng nhập: " + user2.getUsername());
        System.out.println("Mật khẩu: " + user2.getPassword());
        System.out.println("Email: " + user2.getEmail());
        System.out.println("Vai trò: " + user2.getRole());
        System.out.println("=== UserTest ===");

        System.out.println("Mã người dùng: " + user3.getUserId());
        System.out.println("Tên đăng nhập: " + user3.getUsername());
        System.out.println("Mật khẩu: " + user3.getPassword());
        System.out.println("Email: " + user3.getEmail());
        System.out.println("Vai trò: " + user3.getRole());
    }
}
