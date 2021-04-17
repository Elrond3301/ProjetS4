package ProjetS4.src.block;

import java.util.Scanner;
/**
 * Classe Input qui contient des fonctions utiles pour la saisie d'entier ou de chaine avec un seul attribut Scanner
 *
 * @author Simon Hautesserres
 * @date 17/04/2021
 * @version 1.0
 */
public class Input {
	private Scanner scanner = new Scanner(System.in);
	
	/** 
	 * Methode qui utilise le scanner pour renvoyer une chaine de caractères de l'entrée standard
	 * @return string
	 */
	public String inputString() {
		String string = scanner.nextLine();
		return string;
	}
	
	/** 
	 * Methode qui utilise le scanner pour renvoyer un int de l'entrée standard
	 * @return number
	 */
	public int inputInt() {
	    int number = scanner.nextInt();
		return number;
	}
	
}
