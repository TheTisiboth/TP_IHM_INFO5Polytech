# TP1 
## Question 1

Reprend le principe du BoundedRangeModel, avec la r√®gle suivante : 

minimum <= curseur1 < curseur2 <= maximum

## Question 2

* Etat initial : Le curseur 1 est √† la position minimum, et le curseur 2 est √† la position maximum. Donc, tout l'intervalle entre minimum et maximum est selectionn√© par d√©faut.

* Cas 1 : Si l'utilisateur clique sur le curseur 1 en restant appuy√©, on peut faire varier le curseur 1 entre la valeur minimum et le curseur 2. 
Lorsque le bouton de la souris est relach√©, l'ancienne valeur du curseur 1 est remplac√©e par la valeur associ√©e √† la nouvelle position du curseur 1.

* Cas 2 : Si l'utilisateur clique sur le curseur 2 en restant appuy√©, on peut faire varier le curseur 2 entre le curseur 1 et la valeur maximale. 
Lorsque le bouton de la souris est relach√©, l'ancienne valeur du curseur 2 est remplac√©e par la valeur associ√©e √† la nouvelle position du curseur 2.

* Cas 3 : Si l'utilisateur clique et rel√¢che dans la zone entre la valeur minimale et le curseur 1, le curseur 1 se d√©place √† cette nouvelle position et sa valeur est mise √† jour.

* Cas 4 : Si l'utilisateur clique et rel√¢che dans la zone entre le curseur 2 et la valeur maximale, le curseur 2 se d√©place √† cette nouvelle position et sa valeur est mise √† jour.

* Cas 5 : Si l'utilisateur clique et rel√¢che dans la premi√®re moiti√© de la zone compris entre le curseur 1 et le curseur 2, le curseur 1 se d√©place √† cette nouvelle position et sa valeur est mise √† jour.

* Cas 6 : Si l'utilisateur clique et rel√¢che dans la deuxi√®me moiti√© de la zone compris entre le curseur 1 et le curseur 2, le curseur 2 se d√©place √† cette nouvelle position et sa valeur est mise √† jour.

## Question 3

### Travail rÈalisÈ

Nous avons rÈalisÈ un RangeSlider, basÈ sur le JSlider de java swing.
Il implÈmente les fonctionnalitÈs suivantes : 
* DÈplacement lors d'un clic gauche enfoncÈ/dÈplacement de la souris/clic gauche relachÈ de l'un des 2 curseurs, suivant sur lequel on clique.   
* DÈplacement de l'un des 2 curseurs, lorsque l'on clique sur la barre horizontale sur laquelle ils Èvoluent. Les regles de dÈplacmeents sont dÈcrites dans la Question 2.
* Le dÈplacement du 1er curseur est controlable par le clavier. Pas pour le deuxiËme.
En rÈsumÈ, le RangeSlider est completement fonctionnel.

De plus, nous avons rÈalisÈ le HomeFinder, qui implÈmente les fonctionnalitÈs suivantes:
* GÈnÈrations et affichage de maisons alÈatoire
* Filtre dynamique ‡ l'aide des RangeSliders
En rÈsumÈ, le HomeFinder est completement fonctionnel.


### Structure
Nous avons repris la structure du JSlider, ‡ savoir une structure suivant le l'architecture MVC:
* L'implÈmentation du modËle est faite dans DefaultBoundedRangeSliderModel.   
* Dans RangeSliderUI, il y a tout d'abord la Vue, ‡ savoir la partie graphique du slider. De plus, comme vue et controller sont tres liÈes, nous avons laissÈ le controlleur dans cette classe.   
Pour instancier notre widget, il faut instancier l'objet RangeSlider.

Le fichier Main dans le package HomeFinder permet de lancer le programme HomeFinder.
