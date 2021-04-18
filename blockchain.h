/*-----------------------------------------------------------------*/
/*
 Licence Informatique - Structures de données
 Mathias Paulin (Mathias.Paulin@irit.fr)
 
 Interface pour l'implantation du TAD Blockchain vu en cours.
 */
/*-----------------------------------------------------------------*/

#ifndef __Blockchain_H__
#define __Blockchain_H__

#include <stdbool.h>

typedef struct s_Blockchain Blockchain;
typedef struct s_Block Block;

/*-----------------------------------------------------------------*/

/** \defgroup Functors Functions prototypes that could be used with some operators on Blockchain.
 @{
 */
/** Simple functor to be used with the Blockchain_map function.
 This functor receive as argument the value of a Blockchain element and must return the eventually modified value of the element.
 */
typedef int(*SimpleFunctor)(int);

/** Functor with user data to be used with the Blockchain_reduce operator.
  This functor receive as argument the value of a Blockchain element and an opaque pointer to user provided data and must return the eventually modified value of the element.
 */
typedef int(*ReduceFunctor)(int, void *);

/** Functor to be used with the Blockchain_sort operator.
   This functor must implement a total ordering function (comp). When calling this functor with two Blockchain elements a and b, this functor must return true if (a comp b).
 */
typedef bool(*OrderFunctor)(int, int);
/** @} */

/*-----------------------------------------------------------------*/

/** \defgroup Constructors Contructors and destructors of the TAD.
 @{
 */
/** Implementation of the the constructor \c Blockchain from the specification.
 */
Blockchain *blockchain_create(int nbBlocksMax, int difficulty, int maxTranssactions);

/** Implementation of the the constructor \c push_back from the specification.
 @param l The Blockchain to modify
 @param v The value to add
 @return The modified Blockchain
 Add the value v at the end of the Blockchain l.
 @note This function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified by the function.

 */
Blockchain *blockchain_push_back(Blockchain *l, int v);

/** Destructor.
	Added by the implementation. Free ressources allocated by constructors.
 	@param l the adress of the Blockchain.
 	After calling this function, the Blockchain l becomes NULL.
 */
//void Blockchain_delete(ptrBlockchain *l);
/** @} */

/*-----------------------------------------------------------------*/

/** \defgroup FrontBackOperators Insertion and removal of elements at front or back of the Blockchain.
 These operators have a time complexity in O(1).
 @{
 */
/** Add an element at the front of the Blockchain.
 	@param l The Blockchain to modify
 	@param v The value to add
 	@return The modified Blockchain
 	@note This function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified by the function.
 */
Blockchain *blockchain_push_front(Blockchain *l, int v);

/** Acces to the element at begining of the Blockchain.
 	@return the value of the front element of l.
	@pre !empty(l)
 */
int blockchain_front(Blockchain *l);
/** Acces to the element at end of the Blockchain.
 	@return the value of the back element of l.
 	@pre !empty(l)
 */
int blockchain_back(Blockchain *l);

/** Remove to the element at begining of the Blockchain.
 	@return The modified Blockchain
 	@note This function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified by the function.
  	@pre !empty(l)
 */
Blockchain *blockchain_pop_front(Blockchain *l);

/** Remove to the element at end of the Blockchain.
 	@return The modified Blockchain
 	@note This function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified by the function.
 	@pre !empty(l)
 */
Blockchain *blockchain_pop_back(Blockchain *l);
/** @} */

/*-----------------------------------------------------------------*/

/** \defgroup RandomAccessOperators Insertion and removal of elements at any position in the Blockchain.
 These operators have a worst case time complexity in O(n), with n the size of the Blockchain.
 @{
 */
/** Insert an element at a given position.
 	@param l The Blockchain to modify.
 	@param p The position to insert.
 	@param v The value to add.
 	@return The modified Blockchain.
 	Insert an element in a Blockchain so that its position is given by p.
 	@pre 0 <= p <= Blockchain_size(l)
 	@note This function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified by the function.
 */
Blockchain *blockchain_insert_at(Blockchain *l, int p, int v);
/** Remove an element at a given position.
	 @param l The Blockchain to modify.
	 @param p The position of the element to be removed.
	 @return The modified Blockchain.
	 Remove the element located at position .
	 @pre 0 <= p < Blockchain_size(l)
	 @note This function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified by the function.
 */
Blockchain *blockchain_remove_at(Blockchain *l, int p);
/** Acces to an element at a given position.
	 @param l The Blockchain to acces.
	 @param p The position to acces.
	 @return The value of the element at position p.
	 @pre 0 <= p < Blockchain_size(l)
 */
int blockchain_at(Blockchain *l, int p);
/** @} */

/*-----------------------------------------------------------------*/

/** \defgroup UtilityOperators Operators allowing to access some properties or apply some processing on the whole Blockchain.
 @{
 */
/** Test if a Blockchain is empty.
 */
bool blockchain_is_empty(Blockchain *l);
/** Give the number of elements of the Blockchain.
 */
int blockchain_size(Blockchain *l);
/** Apply the same operator on each element of the Blockchain.
 	@param l The Blockchain to process.
 	@param f The operator (function) to apply to each element
 	@see SimpleFunctor
 	@return The eventually modified Blockchain
 	@note If the elements are modified by the operator f, this function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified bye the function.
 	This function sequentially apply the operator f on each element of the Blockchain, starting from the beginning of the Blockchain until the end. The value reurned by the operator when called on an element will replace the element.
*/
Blockchain * blockchain_map(Blockchain *l, SimpleFunctor f);

/** Apply the same operator on each element of the Blockchain gieven a user define environment.
 @param l The Blockchain to process.
 @param f The operator (function) to apply to each element
 @see ReduceFunctor
 @param userData The environment used to call the operator f together with each Blockchain element.
 @return The eventually modified Blockchain
 @note If the elements are modified by the operator f, this function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified bye the function.
 This function sequentially apply the operator f on each element of the Blockchain with the user supplied environment defined by the abstract pointer userData. The operator is applied starting from the beginning of the Blockchain until the end. The value reurned by the operator when called on an element will replace the element.
 */
Blockchain *blockchain_reduce(Blockchain *l, ReduceFunctor f, void *userData);


/** Sort the Blockchain according to the provided ordering functor.
 @param l The Blockchain to sort.
 @param f The order to use
 @return The sorted Blockchain.
 @note This function acts by side effect on the parameter l. The returned value is the same as the parameter l that is modified by the function.
 */
Blockchain *blockchain_sort(Blockchain *l, OrderFunctor f);




/** @} */

#endif

