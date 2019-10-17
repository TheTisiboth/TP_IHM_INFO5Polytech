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

## Question 3

### Travail r�alis�

Nous avons r�alis� un RangeSlider, bas� sur le JSlider de java swing.
Il impl�mente les fonctionnalit�s suivantes : 
* D�placement lors d'un clic gauche enfonc�/d�placement de la souris/clic gauche relach� de l'un des 2 curseurs, suivant sur lequel on clique.   
* D�placement de l'un des 2 curseurs, lorsque l'on clique sur la barre horizontale sur laquelle ils �voluent. Les regles de d�placmeents sont d�crites dans la Question 2.
* Le d�placement du 1er curseur est controlable par le clavier. Pas pour le deuxi�me.
En r�sum�, le RangeSlider est completement fonctionnel.

De plus, nous avons r�alis� le HomeFinder, qui impl�mente les fonctionnalit�s suivantes:
* G�n�rations et affichage de maisons al�atoire
* Filtre dynamique � l'aide des RangeSliders
En r�sum�, le HomeFinder est completement fonctionnel.


### Structure
Nous avons repris la structure du JSlider, � savoir une structure suivant le l'architecture MVC:
* L'impl�mentation du mod�le est faite dans DefaultBoundedRangeSliderModel.   
* Dans RangeSliderUI, il y a tout d'abord la Vue, � savoir la partie graphique du slider. De plus, comme vue et controller sont tres li�es, nous avons laiss� le controlleur dans cette classe.   
Pour instancier notre widget, il faut instancier l'objet RangeSlider.

Le fichier Main dans le package HomeFinder permet de lancer le programme HomeFinder.
