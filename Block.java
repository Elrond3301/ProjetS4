package ProjetS4.src.block;
import java.sql.Timestamp;
/**
 * Classe Block qui a pour attribut un indice, une date, un objet transactions et un mineur associé, la difficulté du bloc,  un hashCode, le hashcode du block 
 * precedent et une nonce initialise a 0
 *
 * @author Simon Hautesserres
 * @date 18/04/2021
 * @version 1.0
 */

public class Block {
	private int index;
	private int nonce = 0;
	private Transaction transaction;
	private int difficulty;
	private Timestamp timeStamp;
	private String hashCode;
	private String previousHash = "0";
	
	/** 
	 * Constructeur qui met a jour la valeur de l'index, de la date, crée le merkle root des transaction  et du hash precedent
	 * @param index
	 * @param timeStamp
	 * @param transaction
	 * @param previousHash
     */
	public Block(int index, String previousHash, int difficulty, int maxTransactions, String transaction) {
		this.index = index;
		this.timeStamp = new Timestamp(System.currentTimeMillis());
		this.difficulty = difficulty;
		/* Si le block n'est pas le premier cree*/
		if(index!=0) {
			this.transaction = new Transaction(transaction, maxTransactions);
			calculateHash(); /*On calcule une premiere fois le Hash*/
			mining(difficulty); /*On mine en fonction de la difficulté le bloc*/
			this.previousHash = previousHash; /*On actualise le hash du bloc précédent*/
		}
		else {
			this.transaction = new Transaction("Genesis",maxTransactions);
			calculateHash(); /*On calcule une premiere fois le Hash*/
			nonce--; /*Il a une nonce nulle*/
		}
	    
	}
	
	/** 
	 * Methode qui appelle la methode sha256, incremente la nonce et actualise le hashCode
	 * @return hashCode
	 */
	private void calculateHash() {
		HashUtil hash = new HashUtil(); /*On crée un hash pour ensuite le calculer et l'actualiser dans hashCode*/
		this.hashCode = hash.applySha256(this.index+this.timeStamp.toString()+this.transaction.getHashTransactions()+this.previousHash+this.nonce);
		this.nonce++;
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
	 * @param index
	 * @return transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	
	/** 
	 * Getter qui retourne la date de creation du block
	 * @return timeStamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	
	/** 
	 * Getter qui retourne le hash code du block
	 * @return hashCode
	 */
	public String getHashCode() {
		return this.hashCode;
	}
	
	/** 
	 * Getter qui retourne le hash du bloc precedent
	 * @return previousHash
	 */
	public String getPreviousHash() {
		return previousHash;
	}
	
	/** 
	 * Setter qui modifie le hash du bloc précédent
	 * @param previous
	 */
	public void setPreviousHash(String previous) {
		previousHash = previous;
	}
	
	/** 
	 * Getter qui retourne le hash du bloc precedent
	 * @return previousHash
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	
	/** 
	 * Methode toString qui retourne une chaine concatenee des attributs du block
	 * @return chaine
	 */
	public String toString() {
		return "Block Mined !! : "+getHashCode()+" n° : "+getIndex()+" Nonce = "+getNonce()+" Previous Hash: "+getPreviousHash()+" Transaction: "+transaction.getHashTransactions();
	}
	
	/**
	 * Methode qui verifie si le hashCode respecte la difficulte qui correspond Ã  difficulte * "0" au debut du hash
	 * @param difficulty
	 * @return boolean
	 */
	private boolean isHashCodeValid(int difficulty) {
		String zeros = zeros(difficulty); /*On initialise avec un nombre definis de zeros par difficulte*/
        if (!getHashCode().substring(0, difficulty).equals(zeros)) { /*Si le début du hash code n'a pas le bon nombre de zeros, on retourne false*/
                 return false;
        }
        return true;
	}
	
	/**
	 * Methode qui verifie si le block est valide et mine le hashCode si nécessaire
	 * @return boolean
	 */
	public boolean isBlockValid(){
		if (index == 0) return true;
		HashUtil hash = new HashUtil();
		boolean etat = hash.applySha256(this.index+this.timeStamp.toString()+this.transaction.getHashTransactions()+this.previousHash+this.nonce).equals(hashCode);
		calculateHash(); /*On recalcule le hash une première fois*/
		mining(difficulty); /*On le mine jusqu'à ce qu'il soit valide*/
		return etat;
	}

	/**
	 * Methode qui verifie si le hashCode est valide et le recalcule tant que celui ci ne respecte pas la difficulte
	 * @param difficulty
	 */
    private void mining(int difficulty){
        while(!isHashCodeValid(difficulty)) {
            calculateHash();
        }
    } 
    
    
    /**
     * Methode qui concatente des zeros "difficulty" fois dans un String
     * @param difficulty
     * @return zeros
     */
    private String zeros(int difficulty) {
    	String zeros = "";
    	for(int i = 0; i< difficulty; i++) {
    		zeros+="0";
    	}
    	return zeros;
    }
    
    /*== PARTIE TRANSACTIONS A FAIRE DANS UN AUTRE OBJET == */
    
    
    /**
     * Methode qui renvoie la prime qui decroit de moitie tous les N blocs
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
    
}
