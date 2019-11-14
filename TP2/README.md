# TP2

## Présentation du Circular menu

### Fonctionnalités

Clic droit dans la fenetre -> ouverture du menu circulaire.
Si l'on refait un clic droit a un autre endroit de la fenetre, on ferme le menu circulaire, et on le réouvre à l'endroit cliqué.
Clic gauche dans le menu -> selectionne un item du menu. 
La zone de clic correspond au secteur angulaire encadrant l'item (partie délimitée par 2 rayons et un arc de cercle, représentée graphiquement sur le menu).
Si cet item mène à un sous menu, alors :
1. On ferme l'ancien menu
2. On ouvre le nouveau sous menu, qui est un circular menu, à l'endroit du clic.   
Clic gauche en dehors du menu -> fermeture des menus ouvert   
Nous avons limité le nombre d'item à 8 au maximum, dans le menu circulaire. Au dela, on les affiche dans une liste verticale, en dessous du cercle.

### Contenu du menu
Nous avons un menu principal, composé des items suivants:
* Tools : Contient les différents outils de dessins
* Colors : Contient les différentes couleurs permettant de dessiner

Lorsque l'on clique sur Tools, on ouvre un nouveau menu circulaire, composé des différents outils de dessin : 
* Pen, pour du dessin libre
* Rect : Permet de dessiner des rectangles
* Ellipse : Permet de dessiner des ellipses   

Lorsque l'on clique sur Colors, on ouvre un nouveau menu circulaire, composé des différentes couleurs de dessins (bleu, blanc, rouge etc).x²
