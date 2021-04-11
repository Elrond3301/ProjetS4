package ProjetS4.src.block;

import java.util.Scanner;

public class Input {

	private Scanner scanner = new Scanner(System.in);
	public String inputString() {
		String string = scanner.nextLine();
		return string;
	}
	
	public int inputInt() {
	    int number = scanner.nextInt();
		return number;
	}
	
}
