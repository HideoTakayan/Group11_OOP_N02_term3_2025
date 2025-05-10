
public class App {
    public static void main(String[] args) {
        // Tạo đối tượng Student
        Student student = new Student(1, "Nguyen Van A", "Male", "2003-05-10");
        System.out.println("=== Student Info ===");
        System.out.println("ID: " + student.getStudentId());
        System.out.println("Name: " + student.getName());
        System.out.println("Gender: " + student.getGender());
        System.out.println("Date of Birth: " + student.getDateOfBirth());

        // Giảng viên
        Lecturers lecturer = new Lecturers(101, "Le Thi B", "Female", "1980-03-15");
        System.out.println("\n=== Lecturer Info ===");
        System.out.println("ID: " + lecturer.getLecturerId());
        System.out.println("Name: " + lecturer.getName());
        System.out.println("Gender: " + lecturer.getGender());
        System.out.println("Date of Birth: " + lecturer.getDateOfBirth());

        // Môn học
        Subject subject = new Subject(101, "Mathematics", 3, 101); // Subject_ID, Subject_Name, Credit, Lecturer_ID
        System.out.println("\n=== Subject Info ===");
        System.out.println("Subject ID: " + subject.getSubjectId());
        System.out.println("Subject Name: " + subject.getSubjectName());
        System.out.println("Credit: " + subject.getCredit());
        System.out.println("Lecturer ID: " + subject.getLecturerId());
    }
}
