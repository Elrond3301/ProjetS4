# ProjetS4
Projet de la Torture made by Léa, Anys, Kylian et Simon

Cahier de charges JAVA : 

   1. Interface de gestion : pas de véritable contrainte. Malgré tout cette interface devra permettre de générer
une blockchain en choisissant le nombre de blocs, la difficulté, le nombre maximal de transactions par
bloc, sauver ou lire dans un fichier au format JSON, vérifier l’intégrité de la blockchain, sortir du
programme, afficher la blockchain ou un bloc d’un numéro donné. Cette interaction devra être légère
et efficace.
  2. Structure de données pour la blockchain centralisée
  3. Structure de données pour les blocs
  4. Structure de données pour les listes de transactions simplifiées (chaînes de caractères dans l’étape 1)
  5. Calcul du hash256 d’un bloc
  6. Minage des blocs
  7. Test de validité 1 et 2 (présence du Génésis, les ash des blocks sont cohérents, le chaînage, aussi, les
racines de Merkle aussi)
  8. Calcul de l’arbre de Merkle d’une liste de transactions
  9. Sauvegarde/lecture fichier JSON (en utilisant le package Google). Cela doit normalement se passer
sans réel travail.
  10. Générateur aléatoire de transactions simplifiés entre deux utilisateurs. On considère qu’on a N utilisateurs : user1,..., userN, pouvant tous être mineurs, et un utilisateur particulier : "Creator" qui est
chargé de créer le bloc genesis. Les transactions aléatoires consistent à choisir deux utilisateurs et un
montant.
  11. Création de blocs par un mineur tiré au hasard.
  12. Une classe main qui gère l’ensemble et simule un processus de création monétaire décrit plus bas.
  
 Cahier de charges C:
 
  1. Structure de données pour la blockchain centralisée
  2. Structure de données pour les blocs
  3. Structure de données pour les listes de transactions simplifiées (chaînes de caractères)
  4. Calcul du hash256 d’un bloc
  5. Minage des blocs
  6. Test de validité 1 et 2 (présence du Génésis, les ash des blocks sont cohérents, le chaînage aussi, les
racines de Merkle aussi)
  7. Calcul de l’arbre de Merkle d’une liste de transactions simplifiées
  8. Générateur aléatoire de transactions simplifiées
  9. Choix d’un mineur et création de blocs
  10. Création d’une interface (simple) pour entrer des données et lancer le programme.
  11. Un main qui gère l’ensemble. Pas d’interface demandé, mais il faudra prévoir de générer une blockchain
avec choix de difficulté, nombre de blocs et de transactions, mise en action ou non du cheater avec
dans ce cas le choix d’un numéro de bloc et, en option, de transaction à supprimer. Ces paramètres
pourront être passés en ligne de commande (recommandé).
  12. un cheater de block qui supprime un block dont le numéro est passé en paramètre et
recalcule les hash de tous les blocks suivant pour effacer les traces avec affichage du
temps de calcul final.
  13. un cheater de transaction qui supprime une transaction dont le numéro est passé en
paramètre ainsi que celui de son block et recalcule les hash de l’arbre de Merkle et de
tous les blocks suivant pour effacer les traces avec affichage du temps de calcul final.
