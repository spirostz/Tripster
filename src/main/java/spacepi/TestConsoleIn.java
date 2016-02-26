package spacepi;

import java.util.Scanner;

public class TestConsoleIn {
	public static void main(String args[]) {
		System.out.print("Enter your sentence: ");
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext() == true) {
			String s1 = sc.next();
			System.out.println(s1);
		}

		System.out.println("The loop has been ended"); 
	}
}