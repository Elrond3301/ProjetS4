package ProjetS4.src.block;

public class Test {
	public static void main(String[] args) {
		Input input = new Input();
		//On demande la difficulte, le nombre de blocs et le nombre de transactions max en entree a  l'utilisateur
		System.out.println(" == INPUT == \n-- Rentrez le nombre de blocs max --\nChoix:  ");
		int nbBlocks = input.inputInt();
		System.out.println("-- Rentrez la difficulté du hash --\nChoix: ");
		int difficulty = input.inputInt();
		System.out.println("-- Rentrez le nombre max de transactions --\nChoix: ");
		int maxTransactions = input.inputInt();
		//On lance la creation d'une blockchain avec un nombre de blocs etablis
		Blockchain blockchain = new Blockchain(nbBlocks, difficulty, maxTransactions);
		for(int k = 1; k < nbBlocks; k++)
			blockchain.addBlock();
		System.out.println(blockchain);
		/*On ajoute des transactions*/
		blockchain.getBlocks(1).addTransaction("Astérix envoie 10 Bnb à Obélix.");
		System.out.println(blockchain);
		
	}
}
