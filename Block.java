package block;

import java.security.MessageDigest;
import java.text.DateFormat;

/**
 * Classe Block qui a pour attribut un indice, une date, une liste de transactions, un hashCode, le hashcode du block 
 * précédent et une nonce initialisé à0
 *
 * @author Simon Hautesserres
 * @date 09/03/2021
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
	public Block(int index, DateFormat timeStamp, String previousHash) {
		this.index = index;
		this.timeStamp = timeStamp;
		this.previousHash = previousHash;
	    calculateHash();
	}
	
	/** 
	 * Méthode qui appelle la méthode sha256, incrémente la nonce et retourne le hashCode
	 * @return hashCode
	 */
	private void calculateHash() {
		this.hashCode = applySha256(this.index+this.timeStamp.toString()+this.transaction+this.previousHash+this.nonce);
		nonce++;
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
	
	/**
	 * Méthode qui vérifie si le hashCode respecte la difficulté qui correspond à difficulté * "0" au début du hash
	 * @param difficulty
	 * @return boolean
	 */
	public boolean isHashCodeValid(int difficulty) {
		String zeros = zeros(difficulty);
        for(int i = 0; i<difficulty;i++)
             if (!getHashCode().substring(0, difficulty).equals(zeros)) {
                 return false;
            }
        return true;
}

	/**
	 * Méthode qui vérifie si le hashCode est valide et le recalcule tant que celui ci ne respecte pas la difficulté
	 * @param difficulty
	 * @return nonce
	 */
    public int mining(int difficulty){
        while(!isHashCodeValid(difficulty)) {
            calculateHash();
        }
        return nonce;
    } 
    
    /**
     * Méthode qui renvoie la prime qui décroit de moitié tous les N blocs
     * @param nbBlocks
     * @return prime
     */
    public float prime(int nbBlocks) { 
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
    
    /**
     * Méthode qui concatènte des zéros "difficulty" fois dans un String
     * @param difficulty
     * @return zeros
     */
    public String zeros(int difficulty) {
    	String zeros = "";
    	for(int i = 0; i< difficulty; i++) {
    		zeros+="0";
    	}
    	return zeros;
    }
	
	
}
