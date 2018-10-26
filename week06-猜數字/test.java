import java.util.Scanner;

class test {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			int count = 0, input = 0;
			double result = (int) (Math.random() * 100) + 1;
			System.out.println(result);
			do {
				input = scanner.nextInt();
				if (input > result)
					System.out.println("太大囉");
				if (input < result)
					System.out.println("太小囉");
				count++;
			} while (input != result && count < 7);

			if (count >= 7)
				System.out.println("GG");
			if (input == result)
				System.out.println("Good");
				
			System.out.print("Continue?(y/n)");
		} while (scanner.next().charAt(0) == 'y');
	}
}
