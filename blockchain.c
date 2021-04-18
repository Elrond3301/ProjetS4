
/*-----------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "blockchain.h"

struct s_Block{
	int index;
	int nonce;
	char * hashCode;
	char * previousHash;
	struct s_Block *previous;
	struct s_Block *next;
};

/* Use of a sentinel for implementing the Blockchain :
 The sentinel is a Block * whose next pointer refer always to the head of the Blockchain and previous pointer to the tail of the Blockchain
 */
struct s_Blockchain {
	struct s_Block *sentinel;
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

Blockchain *blockchain_push_back(Blockchain *l, int v) {
	Block *e = malloc(sizeof(struct s_Block));
	e->index = v;
	e->nonce = 0;
	e->previousHash = "0";
	e->next = l->sentinel;
	e->previous = e->next->previous;
	e->previous->next = e;
	e->next->previous = e;
	++(l->nbBlocksMax);
	return l;
}

/*-----------------------------------------------------------------*/

/*void Blockchain_delete(ptrBlockchain *l) {
    	*l=NULL;
}*/

/*-----------------------------------------------------------------*/

Blockchain *blockchain_push_front(Blockchain *l, int v) {
	Block *e = malloc(sizeof(struct s_Block));
	e->index = v;
	e->previous = l->sentinel;
	e->next = e->previous->next;
	e->previous->next = e;
	e->next->previous = e;
	++(l->nbBlocksMax);
	return l;
}

/*-----------------------------------------------------------------*/

int blockchain_front(Blockchain *l) {
	assert(!Blockchain_is_Empty(l));
	return l->sentinel->next->index;
}

/*-----------------------------------------------------------------*/

int blockchain_back(Blockchain *l) {
	assert(!blockchain_is_Empty(l));
	return l->sentinel->previous->index;
}

/*-----------------------------------------------------------------*/

Blockchain *blockchain_pop_front(Blockchain *l) {
	assert(!blockchain_is_empty(l));
	Block *e;
	e = l->sentinel->next;
	l->sentinel->next = e->next;
	l->sentinel->next->previous = l->sentinel;
	--(l->nbBlocksMax);
	free(e);
	return l;
}

/*-----------------------------------------------------------------*/

Blockchain *blockchain_pop_back(Blockchain *l){
	assert(!blockchain_is_empty(l));
	Block *e;
	e = l->sentinel->previous;
	l->sentinel->previous = e->previous;
	l->sentinel->previous->next = l->sentinel;
	--(l->nbBlocksMax);
	free(e);
	return l;
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

