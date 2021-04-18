package ProjetS4.src.block;

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
		for(int k = 1; k < blockchain.getMaxBlocks(); k++) {
			try {
	            Thread.sleep(delay);
	        }
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			System.out.println(blockchain.getBlocks(k));
		}
	}
	
	
	
	public static void main(String[] args) {
		Input input = new Input();
		/*On demande la difficulte, le nombre de blocs et le nombre de transactions max en entree a  l'utilisateur*/
		System.out.println(" == INPUT == \n-- Rentrez le nombre de blocs max --\nChoix:  ");
		int nbBlocks = input.inputInt();
		System.out.println("-- Rentrez la difficulté du hash --\nChoix: ");
		int difficulty = input.inputInt();
		System.out.println("-- Rentrez le nombre max de transactions --\nChoix: ");
		int maxTransactions = input.inputInt();
		
		/*On lance la creation d'une blockchain avec un nombre de blocs etablis*/
		Blockchain blockchain = new Blockchain(nbBlocks, difficulty, maxTransactions);
		for(int k = 1; k < nbBlocks; k++) {
			blockchain.addBlock();
		}
		affichage(blockchain, 1000);
		/*On ajoute un bloc et des transactions*/
		blockchain.addBlock();
		blockchain.getBlocks(1).getTransaction().addTransaction("Michel a 10 bonobos");
		blockchain.isBlockchainValid();
		/*Ecriture de la blockchain dans un fichier texte*/
		BCJsonUtils.BCJsonWriter(blockchain, "C:\\Users\\chris\\OneDrive\\Bureau\\UPS\\ProjetS4\\test.txt");
	}
}
