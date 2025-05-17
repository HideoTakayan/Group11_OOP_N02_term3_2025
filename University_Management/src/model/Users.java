package University_Management.test;
import University_Management.src.model.Users;
public class UsersTest {
    public static void main(String[] args) {
        Users user = new Users("Trieu Tuan Duy", "duyoop", "123456", "sinh vien");
        System.out.println("=== Người dùng: ===");
        System.out.println("Tên người dùng: " + user.getusername());
        System.out.println("Tài khoản: " + user.getaccout());
        System.out.println("Mật khẩu: " + user.getpassword());
        System.out.println("Vai trò: " + user.getrole());
    }
}