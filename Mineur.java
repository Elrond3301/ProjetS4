package ProjetS4.src.block;

/**
 * Classe Mineur qui contient l'attribut argent
 *
 * @author Simon Hautesserres
 * @date 17/04/2021
 * @version 1.0
 */

public class Mineur {
	private String nom;
	private int argent;
	
	/** 
	 * Constructeur qui met a jour l'attribut argent
	 * @param argent
     */
	public Mineur(String nom, int argent) {
		this.nom = nom;
		this.argent = argent;
	}
	
	/** 
	 * Getter qui renvoie l'attribut argent du mineur
	 * @return argent
	 */
	public int getArgent() {
		return argent;
	}
	
	/** 
	 * Getter qui renvoie l'attribut index du mineur
	 * @return index
	 */
	public String getNom() {
		return nom;
	}
	
	/** 
	 * Methode qui ajoute un montant à l'attribut argent du mineur
	 * @param argent
	 */
	public void gagner(int argent) {
		this.argent += argent;
	}
	/** 
	 * Methode qui enlève un montant à l'attribut argent du mineur
	 * @param argent
	 */
	public void perdre(int argent) {
		this.argent -= argent;
	}
}
