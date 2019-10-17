# TP1 
## Question 1

Reprend le principe du BoundedRangeModel, avec la règle suivante : 

minimum <= curseur1 < curseur2 <= maximum

## Question 2

* Etat initial : Le curseur 1 est à la position minimum, et le curseur 2 est à la position maximum. Donc, tout l'intervalle entre minimum et maximum est selectionné par défaut.

* Cas 1 : Si l'utilisateur clique sur le curseur 1 en restant appuyé, on peut faire varier le curseur 1 entre la valeur minimum et le curseur 2. 
Lorsque le bouton de la souris est relaché, l'ancienne valeur du curseur 1 est remplacée par la valeur associée à la nouvelle position du curseur 1.

* Cas 2 : Si l'utilisateur clique sur le curseur 2 en restant appuyé, on peut faire varier le curseur 2 entre le curseur 1 et la valeur maximale. 
Lorsque le bouton de la souris est relaché, l'ancienne valeur du curseur 2 est remplacée par la valeur associée à la nouvelle position du curseur 2.

* Cas 3 : Si l'utilisateur clique et relâche dans la zone entre la valeur minimale et le curseur 1, le curseur 1 se déplace à cette nouvelle position et sa valeur est mise à jour.

* Cas 4 : Si l'utilisateur clique et relâche dans la zone entre le curseur 2 et la valeur maximale, le curseur 2 se déplace à cette nouvelle position et sa valeur est mise à jour.

* Cas 5 : Si l'utilisateur clique et relâche dans la première moitié de la zone compris entre le curseur 1 et le curseur 2, le curseur 1 se déplace à cette nouvelle position et sa valeur est mise à jour.

* Cas 6 : Si l'utilisateur clique et relâche dans la deuxième moitié de la zone compris entre le curseur 1 et le curseur 2, le curseur 2 se déplace à cette nouvelle position et sa valeur est mise à jour.
