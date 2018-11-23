import java.util.Scanner;

public class test1 {
	public static void main(String[] args) {
		System.out.print("½Ð¿é¤J1-25(©_¼Æ):");
		int n = Integer.valueOf(System.console().readLine());
		int half = n / 2;

		System.out.println();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i - half == j || i == j - half || i - half == n - j + 1 || i == n + 1 - j - half) {
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