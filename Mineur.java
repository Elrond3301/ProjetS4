package ProjetS4.src.block;

/**
 * Classe Mineur qui contient l'attribut argent
 *
 * @author Simon Hautesserres
 * @date 17/04/2021
 * @version 1.0
 */

public class Mineur {
	private int argent;
	
	/** 
	 * Constructeur qui met a jour l'attribut argent
	 * @param argent
     */
	public Mineur(int argent) {
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
