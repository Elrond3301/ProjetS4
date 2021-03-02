package block;

import java.text.SimpleDateFormat;

/**
 * Classe Blockchain qui a pour attribut le nombre de blocks actuels, le maximum de blocks possibles, la difficulté de la blockchain et une 
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
	private Block[] blocks;
	
	/** 
	 * Constructeur qui crée la blockchain, met à jour l'attribut maxBlocks et diffculty et initialise le premier bloc Genesis 
	 * @param maxBlocks
	 * @param difficulty
     */
	public Blockchain(int maxBlocks, int difficulty) {
		blocks = new Block[maxBlocks];
		this.maxBlocks = maxBlocks;
		this.difficulty = difficulty;
		blocks[0] = new Block(0,new SimpleDateFormat("dd/MM/yyyy"),"Genesis","0");
	}
	
	/** 
	 * Méthode qui ajoute un nouveau Block à la blockchain et incrémente l'attribut nbBlocks
	 */
	public void addBlock() {
		blocks[nbBlocks] = new Block(nbBlocks,new SimpleDateFormat("dd/MM/yyyy"),"0",blocks[nbBlocks-1].getHashCode());
		nbBlocks ++;
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
	 * Getter qui retourne la difficulté de calcul de la blockchain
	 * @return difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/** 
	 * Getter qui retourne un block de la blockchain en fonction de l'index en paramètre
	 * @param index
	 * @return transaction
	 */
	public Block getBlocks(int index) {
		return blocks[index];
	}
	
	/** 
	 * Méthode toString qui retourne une chaine concaténée de chaque info de chaque block de la blockchain
	 * @return chaine
	 */
	public String toString() {
		String chaine = "";
		for(int k = 0; k < maxBlocks; k++)
			chaine = chaine + blocks[k].toString();
		return chaine;
	}
}
