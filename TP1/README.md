# TP1 
## Question 1

Reprend le principe du BoundedRangeModel, avec la rÃ¨gle suivante : 

minimum <= curseur1 < curseur2 <= maximum

## Question 2

* Etat initial : Le curseur 1 est Ã  la position minimum, et le curseur 2 est Ã  la position maximum. Donc, tout l'intervalle entre minimum et maximum est selectionnÃ© par dÃ©faut.

* Cas 1 : Si l'utilisateur clique sur le curseur 1 en restant appuyÃ©, on peut faire varier le curseur 1 entre la valeur minimum et le curseur 2. 
Lorsque le bouton de la souris est relachÃ©, l'ancienne valeur du curseur 1 est remplacÃ©e par la valeur associÃ©e Ã  la nouvelle position du curseur 1.

* Cas 2 : Si l'utilisateur clique sur le curseur 2 en restant appuyÃ©, on peut faire varier le curseur 2 entre le curseur 1 et la valeur maximale. 
Lorsque le bouton de la souris est relachÃ©, l'ancienne valeur du curseur 2 est remplacÃ©e par la valeur associÃ©e Ã  la nouvelle position du curseur 2.

* Cas 3 : Si l'utilisateur clique et relÃ¢che dans la zone entre la valeur minimale et le curseur 1, le curseur 1 se dÃ©place Ã  cette nouvelle position et sa valeur est mise Ã  jour.

* Cas 4 : Si l'utilisateur clique et relÃ¢che dans la zone entre le curseur 2 et la valeur maximale, le curseur 2 se dÃ©place Ã  cette nouvelle position et sa valeur est mise Ã  jour.

* Cas 5 : Si l'utilisateur clique et relÃ¢che dans la premiÃ¨re moitiÃ© de la zone compris entre le curseur 1 et le curseur 2, le curseur 1 se dÃ©place Ã  cette nouvelle position et sa valeur est mise Ã  jour.

* Cas 6 : Si l'utilisateur clique et relÃ¢che dans la deuxiÃ¨me moitiÃ© de la zone compris entre le curseur 1 et le curseur 2, le curseur 2 se dÃ©place Ã  cette nouvelle position et sa valeur est mise Ã  jour.

## Question 3

### Travail réalisé

Nous avons réalisé un RangeSlider, basé sur le JSlider de java swing.   
Il implémente les fonctionnalités suivantes :   
* Déplacement lors d'un clic gauche enfoncé/déplacement de la souris/clic gauche relaché de l'un des 2 curseurs, suivant sur lequel on clique.    
* Déplacement de l'un des 2 curseurs, lorsque l'on clique sur la barre horizontale sur laquelle ils évoluent. Les regles de déplacmeents sont décrites dans la Question 2.    
* Le déplacement du 1er curseur est controlable par le clavier. Pas pour le deuxième.  

En résumé, le RangeSlider est completement fonctionnel.   

De plus, nous avons réalisé le HomeFinder, qui implémente les fonctionnalités suivantes:   
* Générations et affichage de maisons aléatoire     
* Filtre dynamique à l'aide des RangeSliders.    

En résumé, le HomeFinder est completement fonctionnel.  


### Structure
Nous avons repris la structure du JSlider, à savoir une structure suivant le l'architecture MVC:   
* L'implémentation du modèle est faite dans DefaultBoundedRangeSliderModel.      
* Dans RangeSliderUI, il y a tout d'abord la Vue, à savoir la partie graphique du slider. De plus, comme vue et controller sont tres liées, nous avons laissé le controlleur dans cette classe.     

Pour instancier notre widget, il faut instancier l'objet RangeSlider.   
   
Le fichier Main dans le package HomeFinder permet de lancer le programme HomeFinder.  
