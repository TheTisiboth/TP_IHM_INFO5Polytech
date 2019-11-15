# TP2

## Présentation du Circular menu

Le circular menu est situé dans le package circular (on le lance depuis le fichier circular.paintCircular).

### Fonctionnalités
Le Circular menu est completement fonctionnel. Pour l'utiliser, on peut faire les actions suivantes :   
Clic droit dans la fenetre -> ouverture du menu circulaire.   
Si l'on refait un clic droit a un autre endroit de la fenetre, on ferme le menu circulaire, et on le réouvre à l'endroit cliqué.   
Clic gauche dans le menu -> selectionne un item du menu. 
La zone de clic correspond au secteur angulaire encadrant l'item (partie délimitée par 2 rayons et un arc de cercle, représentée graphiquement sur le menu).   
Si cet item mène à un sous menu, alors :
1. On ferme l'ancien menu
2. On ouvre le nouveau sous menu, qui est un circular menu, à l'endroit du clic.   
Clic gauche en dehors du menu -> fermeture des menus ouvert   

Nous avons limité le nombre d'item à 8 au maximum, dans le menu circulaire. Au dela, on les affiche dans une liste verticale, en dessous du cercle.

Par défaut, la couleur selectionnée est le noir. Si l'on change de couleur, la couleur sera effective jusqu'au prochain changement de couleur. Lorsque l'on change d'outil, on conserve la meme couleur.

### Contenu du menu
Nous avons un menu principal, composé des items suivants:
* Tools : Contient les différents outils de dessins
* Colors : Contient les différentes couleurs permettant de dessiner

Lorsque l'on clique sur Tools, on ouvre un nouveau menu circulaire, composé des différents outils de dessin : 
* Pen, pour du dessin libre
* Rect : Permet de dessiner des rectangles
* Ellipse : Permet de dessiner des ellipses   

Lorsque l'on clique sur Colors, on ouvre un nouveau menu circulaire, composé des différentes couleurs de dessins (bleu, blanc, rouge etc).

### Lancement
Il faut lancer le programme contenu dans paintCircular, dans le package main.

## Présentation du Marking Menu

Le marking menu est situé dans le package marking (on le lance depuis le fichier marking.paintMarking).

### Fonctionnalités
Le marking menu n'est pas completement fonctionnel.
Même Fonctionnalité que le circular menu. On commence par un clic droit, ce qui affiche le circular menu. Ensuite, tout en maintenant le clic droit, on se déplace vers l'outils choisit, pour le selectionner. Si c'est un sous menu, alors on l'affiche, et il faut continuer a maintenir le clic droit et se déplacer jusqu'au prochain item pour le selectionner.

### Contenu du menu
Il est tres similaire au circular menu. Nous avons ajouté une "dead zone" centrale. C'est un cercle au centre du circular menu, dans le quel on ne peut effectuer aucune action, ce qui nous laisse une marge de manoeuvre afin de nous déplacer pour selectionner un item.

### Work in progress
L'implémentation est en cours. On peut clic droit sur l'ecran, puis tout en maintenant le clic, on peut se déplacer pour sélectionner un sous menu, ce qui a pour effet de cacher le menu courant, et d'afficher le nouveau sous menu, ou de selectionner l'item courant. En revanche, on a du mal au niveau de la selection de sous menu, ou d'item, cette partie est en cours d'implémentation. Cela est dû à un probleme de coordonnées absolues/relatives.
