#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <unistd.h>
#include "blockchain.h"

int printBlockchain(int i){
	return i;
}

int main(int argc, char **argv){
	
	if (argc!=4) {
		fprintf(stderr,"usage : %s nbBlocksMax difficulty maxTransactions\n", argv[0]);
		exit(1);
	}


    Blockchain *bc;
    bc = blockchain_create(atoi(argv[1]), atoi(argv[2]), atoi(argv[3])); /*Création de la blockchain*/
	sleep(0.5); printf("======== GENESIS ======== \n");
	blockchain_push_back(bc, 0); /*Ajout du bloc génésis*/
	sleep(0.5); printf("======== PHASE HELICOPTERE ======== \n");	
    for (int i=1; i<atoi(argv[1]) ;i++) /*Initialisation des blocs passés en paramètres*/
			blockchain_push_back(bc, i);
    blockchain_map(bc, printBlockchain);
	for (int i=0; i<atoi(argv[1]) ;i++)
			blockchain_remove_at(bc, i);
	blockchain_delete(bc);

    exit(0);
}

