package ProjetS4.src.block;

import java.security.MessageDigest;

/**
 * Classe HashUtil qui contient les fonctions utiles pour calculer le hashCode
 *
 * @author Simon Hautesserres
 * @date 17/04/2021
 * @version 1.0
 */
public class HashUtil {
 
	//Applies Sha256 to a string and returns the result. 
	public  String applySha256(String input){
  
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

}
