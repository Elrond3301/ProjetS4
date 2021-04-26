
/*-----------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>	
#include <time.h>
#include <unistd.h>
#include "blockchain.h"

#define STRLONG 60
#define BUFFERSIZE 32

struct s_Block{
	int index;
	int nonce;
	char * hashCode;
	char * previousHash;
	time_t timestamp;
	struct s_Block *previous;
	struct s_Block *next;
};

/* Use of a sentinel for implementing the Blockchain :
 The sentinel is a Block * whose next pointer refer always to the head of the Blockchain and previous pointer to the tail of the Blockchain
 */
struct s_Blockchain {
	Block *sentinel;
	int nbBlocksMax;
	int difficulty;
	int maxTransactions;
};


/*-----------------------------------------------------------------*/

Blockchain *blockchain_create(int nbBlocksMax, int difficulty, int maxTransactions) {
	Blockchain *bc = malloc(sizeof(struct s_Blockchain));
	bc->sentinel = malloc(sizeof(struct s_Block));
	bc->sentinel->previous = bc->sentinel->next = bc->sentinel;
	bc->nbBlocksMax = nbBlocksMax;
	bc->difficulty = difficulty;
	bc->maxTransactions = maxTransactions;
	return bc;
}

/*-----------------------------------------------------------------*/
Blockchain *blockchain_push_back(Blockchain *bc, int v) {
	Block *b = malloc(sizeof(struct s_Block));
	b->index = v;
	b->nonce = 1;
	b->hashCode = malloc(2*BUFFERSIZE+1); /*On alloue la mémoire pour le hash Code et celui de son prédecesseur*/
	b->previousHash =  malloc(2*BUFFERSIZE+1);
	b->timestamp = time(NULL); 
	b->next = bc->sentinel;
	b->previous = b->next->previous;
	b->previous->next = b;	
	b->next->previous = b;
	if(v == 0) b->previousHash = "0"; /*Le bloc génésis a la chaine "0" comme previousHash*/
	else b->previousHash = b->previous->hashCode; /*Sinon on prend le hash Code du prédecesseur*/
	char  buffer1[100], buffer2[100]; 
	sprintf(buffer1, "%d", b->index); sprintf(buffer2, "%d", b->nonce);	
	strcat(buffer1,strcat(buffer2,strcat(ctime(&b->timestamp),b->previousHash)));
	sha256ofString((BYTE *)buffer1,b->hashCode);
	++(bc->nbBlocksMax);
	mining(bc->difficulty,b);
	sleep(0.5);
	printf("Block Mined!! Numéro = %d Nonce = %d HashCode = %s PreviousHash = %s\n",b->index,b->nonce, b->hashCode, b->previousHash);
	return bc;
}

/*-----------------------------------------------------------------*/

void blockchain_delete(Blockchain *bc) {
		free(bc);
}


/*-----------------------------------------------------------------*/

int blockchain_front(Blockchain *bc) {
	assert(!Blockchain_is_Empty(bc));
	return bc->sentinel->next->index;
}

/*-----------------------------------------------------------------*/

int blockchain_back(Blockchain *bc) {
	assert(!blockchain_is_Empty(bc));
	return bc->sentinel->previous->index;
}

/*-----------------------------------------------------------------*/

Blockchain *blockchain_pop_front(Blockchain *bc) {
	assert(!blockchain_is_empty(bc));
	Block *e;
	e = bc->sentinel->next;
	bc->sentinel->next = e->next;
	bc->sentinel->next->previous = bc->sentinel;
	--(bc->nbBlocksMax);
	free(e);
	return bc;
}

/*-----------------------------------------------------------------*/

Blockchain *blockchain_pop_back(Blockchain *bc){
	assert(!blockchain_is_empty(bc));
	Block *e;
	e = bc->sentinel->previous;
	bc->sentinel->previous = e->previous;
	bc->sentinel->previous->next = bc->sentinel;
	--(bc->nbBlocksMax);
	free(e);
	return bc;
}

/*-----------------------------------------------------------------*/

Blockchain *blockchain_insert_at(Blockchain *l, int p, int v) {
	assert((0 <= p) && (p <= l->nbBlocksMax));
   	Block *insert = l->sentinel->next;
	while (p--) insert = insert->next;
	Block *e = malloc(sizeof(Block));
	e->index = v;
	e->next = insert;
	e->previous = insert->previous;
	e->previous->next = e;
	e->next->previous = e;
	++(l->nbBlocksMax);
	return l;
}

/*-----------------------------------------------------------------*/

Blockchain *blockchain_remove_at(Blockchain *l, int p) {
	assert((0 <= p) && (p < l->nbBlocksMax));
	Block *remove = l->sentinel->next;
	while (p--) remove = remove->next;
	remove->previous->next = remove->next;
	remove->next->previous = remove->previous;
	free(remove->hashCode);
	free(remove);
	--(l->nbBlocksMax);
	return l;
}

/*-----------------------------------------------------------------*/

int blockchain_at(Blockchain *l, int p) {
 	assert(!blockchain_is_empty(l) && (0 <= p) && p < l->nbBlocksMax);
 	Block *e = l->sentinel->next;
	for (;p;--p, e = e->next);
 	return e->index;
}

/*-----------------------------------------------------------------*/

bool blockchain_is_empty(Blockchain *l) {
	return (l->nbBlocksMax == 0);
}

/*-----------------------------------------------------------------*/

int blockchain_nbBlocksMax(Blockchain *l) {
	return l->nbBlocksMax;
}

/*-----------------------------------------------------------------*/

Blockchain * blockchain_map(Blockchain *l, SimpleFunctor f) {
	for(Block *e = l->sentinel->next; e != l->sentinel; e = e->next){
		e->index = f(e->index);
	}
	return l;
}

/*-----------------------------------------------------------------*/

Blockchain *blockchain_reduce(Blockchain *l, ReduceFunctor f, void *userData) {
	for(Block *e = l->sentinel->next; e != l->sentinel; e = e->next)
		f(e->index, userData);
	return l;
}

/*-----------------------------------------------------------------*/
void mining(int difficulty , Block *b) {
	char  buffer1[100], buffer2[100]; 
	
    while(!isHashCodeValid(difficulty, b)) {
		b->nonce ++;
		sprintf(buffer1, "%d", b->index); sprintf(buffer2, "%d", b->nonce);	
		strcat(buffer1,strcat(buffer2,strcat(ctime(&b->timestamp),b->previousHash)));
        sha256ofString((BYTE *)buffer1, b->hashCode);
    }
}

/*-----------------------------------------------------------------*/
float prime(int nbBlocks) { 
    /* la prime décroit de moitié tout les N blocs */
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

/*-----------------------------------------------------------------*/
int isHashCodeValid(int difficulty, Block *block) {
    for(int i = 0; i<difficulty;i++){
        if(block->hashCode[i] != '0') {
            return 0;
        }
    }
    return 1;
}

