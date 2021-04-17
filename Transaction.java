package ProjetS4.src.block;

import java.util.ArrayList;

/**
 * Classe Transaction qui contient l'ensemble des paramètres de transactions d'un bloc
 *
 * @author Simon Hautesserres
 * @date 17/04/2021
 * @version 1.0
 */
public class Transaction {
	private ArrayList<String> transaction;
	private int nbTransactions = 1;
	private int maxTransactions;
	private String hashTransaction= "";
	MerkleTree mkTree = new MerkleTree();
	
	
	/** 
	 * Constructeur qui met a jour l'attribut mzxTransactions, créer une arrayList et ajoute une première transaction
	 * Il peut ainsi calculer le premier hash des transactions.
	 * @param firstTransaction
	 * @param maxTransactions
     */
	public Transaction(String firstTransaction, int maxTransactions) {
		this.maxTransactions = maxTransactions;
		this.transaction = new ArrayList<String>(maxTransactions);
		transaction.add(firstTransaction);
		calculateHashTransaction();
	}
	
	/** 
	 * Getter qui retourne le max des Transactions
	 * @return maxTransactions
	 */
	public int getMaxTransactions() {
		return maxTransactions;
	}
	
	/** 
	 * Getter qui retourne l'index du block
	 * @return maxTransactions
	 */
	public String getHashTransactions() {
		return hashTransaction;
	}
	
	/** 
	 * Méthode qui calcule le hash des transactions avec le merkle tree
	 */
	private void calculateHashTransaction() {
		hashTransaction = mkTree.createMerkleTree(transaction);
	}
	
	/** 
	 * Méthode qui ajouter une transaction et recalcule le hash des transactions
	 * @param chaine
	 */
	public void addTransaction(String chaine) {
		if(nbTransactions != maxTransactions) {
			transaction.add(chaine);
			calculateHashTransaction();
		}
			
	}
	
	
	
}
