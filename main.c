#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "blockchain.h"

int printBlockchain(int i){
	printf("%d ", i);
	return i;
}

int main(int argc, char **argv){
	
	if (argc!=4) {
		fprintf(stderr,"usage : %s nbBlocksMax difficulty maxTransactions\n", argv[0]);
		exit(1);
	}


    Blockchain *bc;
    bc = blockchain_create(atoi(argv[1]), atoi(argv[2]), atoi(argv[3])); /*On initialise la blockchain*/
    for (int i=0; i<atoi(argv[1]) ;i++) /*Initialisation du nombre de blocs avec leur index*/
			blockchain_push_back(bc, i);
    blockchain_map(bc, printBlockchain);
	printf("\n");
        

    exit(0);
}

