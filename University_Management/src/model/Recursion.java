package University_Management.src.model;

public class Recursion {
    public static int sumCredits(int[] credits, int n) {
        if (n <= 0) {
            return 0;
        }
        return sumCredits(credits, n - 1) + credits[n - 1];
    }

    public static void main(String[] args) {
        int[] credits = { 3, 2, 4, 1 };
        int totalCredits = sumCredits(credits, credits.length);
        System.out.println("Tổng số tín chỉ: " + totalCredits);
    }
}
