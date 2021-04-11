package ProjetS4.src.block;

import java.util.ArrayList;

public class MerkleTree {

    public String createMerkleTree(ArrayList<String> txnLists) {
        ArrayList<String> merkleRoot = merkleTree(txnLists); //Création du merkle root
        return merkleRoot.get(0); //On retourne le premier élément
    }

    private ArrayList<String> merkleTree(ArrayList<String> hashList){
        //Si la taille vaut 1, on retourne la chaine non hashé
        if(hashList.size() == 1){
            return hashList;
        }
        ArrayList<String> parentHashList=new ArrayList<>();
        //Hash the leaf transaction pair to get parent transaction
        int i = 0;
        while(i+1<hashList.size()){ //Tant qu'on a pas parcouru toute la liste
        	HashUtil hash = new HashUtil();
            String hashedString = hash.applySha256(hashList.get(i).concat(hashList.get(i+1)));
            parentHashList.add(hashedString);
            i+=2;
        }
        // If odd number of transactions , add the last transaction again
        if(hashList.size() % 2 == 1){
        	HashUtil hash = new HashUtil();
            String lastHash=hashList.get(hashList.size()-1);
            String hashedString = hash.applySha256(lastHash.concat(lastHash));
            parentHashList.add(hashedString);
        }
        return merkleTree(parentHashList);
    }
}
