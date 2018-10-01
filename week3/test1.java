import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number(1~15):");
        int n = scanner.nextInt();

        System.out.print("    ");
        for (int i = 1; i <= n; i++)
            System.out.printf("%4d", i);
            
        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.printf("%4d", i);
            for (int j = 1; j <= n; j++)
                System.out.printf("%4d", i * j);
            System.out.println();
        }
    }
}