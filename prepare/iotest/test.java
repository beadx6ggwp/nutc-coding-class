import java.util.Scanner;
import java.io.*;

class test {
    public static void main(String[] args) throws IOException {
        String input = System.console().readLine();
        System.out.println(input);

        do{
            System.out.println("Hello");
        }while(System.console().readLine().equals("yes"));
        
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // System.out.print("Enter String");
        // String s = br.readLine();
        // System.out.print("Enter Integer:");
        // try {
        //     int i = Integer.parseInt(br.readLine());
        // } catch (NumberFormatException nfe) {
        //     System.err.println("Invalid Format!");
        // }
    }
}