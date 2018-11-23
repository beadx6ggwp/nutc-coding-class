import java.util.Scanner;

public class test1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = 26;

		System.out.print("    ");
		for (int i = 1; i <= n; i++)
			System.out.printf("%4d", i);

		System.out.println();
		for (int i = 1; i <= n; i++) {
			System.out.printf("%4d", i);
			for (int j = 1; j <= n; j++) {
				if (RangeOfTri(j, i, 12, 4, 12, 12) || RangeOfRect(j, i, 4, 11, 11, 13)) {
					System.out.printf("%4c", ' ');
				} else {
					System.out.printf("%4d", i * j);
				}
			}
			System.out.println();
		}
	}

	static boolean RangeOfTri(int j, int i, int x, int y, int w, int h) {
		return (j - x >= i - y) && j >= x && j <= x + w && i >= y && i <= y + h;
	}

	static boolean RangeOfRect(int j, int i, int x, int y, int w, int h) {
		return j >= x && j <= x + w && i >= y && i <= y + h;
	}
}