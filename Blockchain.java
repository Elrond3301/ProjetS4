package ProjetS4.src.block;
/**
 * Classe Blockchain qui a pour attribut le nombre de blocks actuels, le maximum de blocks possibles, la difficulte de la blockchain et une 
 * liste de blocks
 *
 * @author Simon Hautesserres
 * @date 02/03/2021
 * @version 1.0
 */

public class Blockchain {
	private int nbBlocks = 1;
	private int maxBlocks;
	private int difficulty;
	private int maxTransactions;
	private Block[] blocks;
	
	/** 
	 * Constructeur qui cree la blockchain, met a jour l'attribut maxBlocks et diffculty et initialise le premier bloc Genesis 
	 * @param maxBlocks
	 * @param difficulty
     */
	public Blockchain(int maxBlocks, int difficulty, int maxTransactions) {
		blocks = new Block[maxBlocks];
		this.maxBlocks = maxBlocks;
		this.difficulty = difficulty;
		this.maxTransactions = maxTransactions;
		blocks[0] = new Block(0,"0", difficulty, 1);
	}
	
	/** 
	 * Methode qui ajoute un nouveau Block dans la blockchain et incremente l'attribut nbBlocks
	 */
	public void addBlock() {
		if(nbBlocks != maxBlocks ) {
			blocks[nbBlocks] = new Block(nbBlocks,blocks[nbBlocks-1].getHashCode(), difficulty, maxTransactions);
			nbBlocks ++;
		}
		else {
			System.out.println("WARNING !! La blockchain est déjà rempli.");
		}
	}

	/** 
	 * Getter qui retourne le nombre de blocks actuels de la blockchain
	 * @return nbBlocks
	 */
	public int getNbBlocks() {
		return nbBlocks;
	}
	
	/** 
	 * Getter qui retourne le nombre max de blocks de la blockchain
	 * @return maxBlocks
	 */
	public int getMaxBlocks() {
		return maxBlocks;
	}
	
	/** 
	 * Getter qui retourne la difficulte de calcul de la blockchain
	 * @return difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/** 
	 * Getter qui retourne le nombre max de transactions
	 * @return maxTransactions
	 */
	public int getMaxTransactions() {
		return maxTransactions;
	}

	/** 
	 * Getter qui retourne un block de la blockchain en fonction de l'index en parametre
	 * @param index
	 * @return transaction
	 */
	public Block getBlocks(int index) {
		return blocks[index];
	}
	
	/** 
	 * Methode isBlockchainValid qui vérifie si chaque bloc de la blockchain sont valides
	 */
	public void isBlockchainValid() {
		for(int k = 0; k < nbBlocks; k++){
			if(!blocks[k].isBlockValid() && k+1<maxBlocks) blocks[k+1].setPreviousHash(blocks[k].getHashCode());
		}
	}
	
	
}
