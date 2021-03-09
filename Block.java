package block;

import java.security.MessageDigest;
import java.text.DateFormat;

/**
 * Classe Block qui a pour attribut un indice, une date, une liste de transactions, un hashCode, le hashcode du block 
 * précédent et une nonce initialisé à0
 *
 * @author Simon Hautesserres
 * @date 02/03/2021
 * @version 1.0
 */

public class Block {
	private int index;
	private int nonce = 0;
	private String transaction;
	private DateFormat timeStamp;
	private String hashCode;
	private String previousHash;
	
	/** 
	 * Constructeur qui met à jour la veleur de l'index, de la date, des transactions et du hash précédent
	 * @param index
	 * @param timeStamp
	 * @param transaction
	 * @param previousHash
     */
	public Block(int index, DateFormat timeStamp, String transaction, String previousHash) {
		this.index = index;
		this.timeStamp = timeStamp;
		this.transaction = transaction;
		this.previousHash = previousHash;
		this.hashCode = calculateHash();
	}
	
	/** 
	 * Méthode qui appelle la méthode sha256, incrémente la nonce et retourne le hashCode
	 * @return hashCode
	 */
	private String calculateHash() {
		String hashCode = applySha256(this.index+this.timeStamp.toString()+this.transaction+this.previousHash+this.nonce);
		nonce++;
		return hashCode ;
	}
	
	/** 
	 * Méthode qui prend en entrée un String et utilise la méthode du sha256 pour retourner le hashCode
	 * @param input
	 * @return hashCode
	 */
	public String applySha256(String input){
		  try {
		   MessageDigest digest = MessageDigest.getInstance("SHA-256");
		         
		   //Applies sha256 to our input, 
		   byte[] hash = digest.digest(input.getBytes("UTF-8"));
		         
		   StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
		   for (int i = 0; i < hash.length; i++) {
		    String hex = Integer.toHexString(0xff & hash[i]);
		    if(hex.length() == 1) hexString.append('0');
		    hexString.append(hex);
		   }
		   return hexString.toString();
		  }
		  catch(Exception e) {
		   throw new RuntimeException(e);
		  }
		 }

	/** 
	 * Getter qui retourne l'index du block
	 * @return index
	 */
	public int getIndex() {
		return index;
	}
	
	/** 
	 * Getter qui retourne la valeur de la nonce
	 * @return nonce
	 */
	public int getNonce() {
		return nonce;
	}
	
	/** 
	 * Getter qui retourne la liste des transactions
	 * @return transaction
	 */
	public String getTransaction() {
		return transaction;
	}
	
	/** 
	 * Getter qui retourne la date de création du block
	 * @return timeStamp
	 */
	public DateFormat getTimeStamp() {
		return timeStamp;
	}
	
	/** 
	 * Getter qui retourne le hash code du block
	 * @return hashCode
	 */
	public String getHashCode() {
		return hashCode;
	}
	
	/** 
	 * Getter qui retourne le hash du bloc précédent
	 * @return previousHash
	 */
	public String getPreviousHash() {
		return previousHash;
	}
	
	/** 
	 * Méthode toString qui retourne une chaine concaténée des attributs du block
	 * @return chaine
	 */
	public String toString() {
		return "Block #" + index + "\n[nonce : " + nonce + "\ntransaction : " + transaction + "\ntimeStamp : "
				+ timeStamp + "\nhashCode : " + hashCode + "\npreviousHash : " + previousHash + "]\n\n";
	}
	
	public boolean isHashCodeValid(int difficulty, Block block) {
        for(int i = 0; i<difficulty;i++)
             if (block.getHashCode()[i] != "0") {
                 return false;
            }
        return true;
}

	public int mining() {
		int nonce = 0;
		while(!isHashCodeValid(difficulty, block)) {
			nonce ++;
			hash = Block.calculateHash(getHashCode());
		}
	}
	
	public float prime(int nbBlocks) { /* la prime décroit de moitié tout les N blocs */
		float primebase = 50;
		float prime = primebase;
		int val = nbBlocks%10;
		int i = 0;
		if(val == 1) {
			return primebase;
		}
		else {
			while(i != val ) {
				prime = prime + prime/2;
				i++;
			}
			return prime;
		}
	
	
}
