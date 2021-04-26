package ProjetS4.src.block;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Classe Test qui lance la création de la blockchain, demande à l'utilisateur les différents parametres, lit et écrit grâce à JSON
 * et affiche différents éléments dont la blockchain grâce à la fonction affichage
 * @author Simon Hautesserres
 * @date 18/04/2021
 * @version 1.0
 */
public class Test {
	
	/** 
	 * Methode affiche la blockchain passé en paramètre
	 * @param hashCode
	 */
	public static void affichage(Blockchain blockchain, int delay) {
		System.out.println("======== GENESIS ======== \n"+ blockchain.getBlocks(0).toString()+"\n=== PHASE HELICOPTERE ===");
		int k = 1;
		while(k < blockchain.getMaxBlocks() && blockchain.getBlocks(k) != null) {
			try {
	            Thread.sleep(delay);
	        }
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			System.out.println(blockchain.getBlocks(k));
			k++;
		}
	}
	
	
	
	public static void main(String[] args) {
		Input input = new Input();
		Queue<Integer> q = new LinkedList<>(); /*File globale de transactions*/
		int reward = 50; /*Reward initial à 50 bonobos*/
		/*On demande  le nombre de blocs, le nombre d'utilisateurs, la difficulte, et le nombre de transactions max en entree a  l'utilisateur*/
		System.out.println(" == INPUT == \n-- Rentrez le nombre de blocs max --\nChoix:  ");
		int nbBlocks = input.inputInt();
		System.out.println("-- Rentrez le nombre d'utilisateurs --\nChoix:  ");
		int nbUtilisateurs = input.inputInt();
		System.out.println("-- Rentrez la difficulté du hash --\nChoix: ");
		int difficulty = input.inputInt();
		System.out.println("-- Rentrez le nombre max de transactions --\nChoix: ");
		int maxTransactions = input.inputInt();
		
		/*On lance la creation d'une blockchain et des mineurs*/
		Mineur [] mineurs = new Mineur[nbUtilisateurs];
		mineurs[0] = new Mineur("Creator",reward);
		Blockchain blockchain = new Blockchain(nbBlocks, difficulty, maxTransactions);
		for(int k = 1; k < nbUtilisateurs; k++) {
			mineurs[k] = new Mineur("Usern"+k,reward);
			blockchain.addBlock(mineurs[0].getNom()+" envoie "+reward+" Bnb à "+ mineurs[k].getNom()); /*Phase Helicopter Money où chaque User reçoit 50 bonobos*/
		}
		/*Phase de marché*/
		
		
		affichage(blockchain, 1000);
		/*Ecriture de la blockchain dans un fichier texte*/
		BCJsonUtils.BCJsonWriter(blockchain, "C:\\Users\\chris\\OneDrive\\Bureau\\UPS\\ProjetS4\\test.txt");
	}
}
