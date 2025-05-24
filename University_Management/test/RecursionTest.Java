package University_Management.test;

import University_Management.src.model.Recursion;

public class RecursionTest {
    public static void main(String[] args) {
        int[] credits = { 3, 2, 4, 1 };

        System.out.println("=== Test Recursion ===");
        System.out.print("Danh sách tín chỉ: ");
        for (int credit : credits) {
            System.out.print(credit + " ");
        }
        System.out.println();

        int total = Recursion.sumCredits(credits, credits.length);
        System.out.println("Tổng số tín chỉ: " + total);
    }
}
