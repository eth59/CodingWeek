# CodingWeek 2025
**TELECOM Nancy - 06/01 au 10/01**

Les instructions sur l'organisation et les attendus de la semaine se trouve dans le fichier [INSTRUCTIONS.md](./INSTRUCTIONS.md).

TABLE DES MATIERES
=================

   * [Execution](#execution)
   * [Le jeu](#le-jeu)
     * [La page ACCUEIL](#la-page-accueil)
     * [La page ADD CARD](#la-page-add-card)
     * [La page CONFIGURATION](#la-page-configuration)
     * [La page GUESSER](#la-page-guesser)
     * [La page SPY](#la-page-spy)
     * [La page GAME OVER](#la-page-game-over)
     * [La page STATISTIQUES](#la-page-statistiques)

## Execution

Faire cette commande à la racine de ce projet :

``` java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar grp25.jar ```

#### /!\ **Attention** /!\ :
Il faut un fichier mots.json dans le même dossier que le jar pour que celui-ci puisse récupérer les catégories, mots et images pour jouer.


# Le jeu
# La page ACCUEIL

![Page ACCUEIL](./figures/ACCUEIL.png)

Cette page se lance lorsque vous lancez le jeu. Vous avez le choix entre 4 boutons :
- Règles : il vous redirige vers la page internet des règles du jeu 
- Add Card : il vous redirige vers la page d'ajout de cartes de jeu
- Play : il vous redirige vers la page de configuration de votre partie
- Quit : il vous permet de quitter le jeu

# La page ADD CARD

![Page ADD CARD](./figures/ADDCARD.png)

Cette page permet à l'utilisateur d'ajouter des cartes au jeu ou une catégorie. 
Pour ajouter une catégorie, il doit renseigner le nom de la catégorie puis cliquer sur le bouton *Add Category*.
Pour ajouter une carte, il doit renseigner la catégorie associée à la carte, le mot à faire deviner puis appuyer sur le bouton *Add Card*.

# La page CONFIGURATION

![Page CONFIGURATION](./figures/CONFIG.png)

Cette page permet à l'utilisateur de configurer sa partie. 
Il doit renseigner la taille de la grille (un entier compris entre 3 et 6 inclus), la catégorie avec laquelle il souhaite jouer. 
Il peut aussi le mode de jeu **Image** en cliquant sur la case à cocher. 
L'utilisateur peut aussi choisir de jouer avec un chronomètre indiquant le temps restant au **GUESSER** pour jouer. 
Une fois les paramètres renseignés, il peut cliquer sur le bouton *Start Game* pour lancer la partie. 
Si besoin, il peut cliquer sur le bouton *Return Menu* pour revenir à la page d'accueil. 
Par défaut, les paramètres sont initialisés à 5 pour la taille de la grille, la catégorie **Toutes les catégories**, le mode **Mot** et le chronomètre désactivé.

# La page GUESSER

![Page GUESSERMOT](./figures/GUESSERMOT.png)

Cette page est la page de jeu du **GUESSER** en mode **Mots**. 
Ici, on constate que c'est au tour du **GUESSER rouge** de jouer. 
Plusieurs actions sont possibles : 
- Cliquer sur le bouton *Menu* qui affiche un menu déroulant permettant, au choix, de charger une ancienne partie non terminée, sauvegarder la partie actuelle ou retourner à la page d'accueil. 
- Cliquer sur le bouton *Statistics* qui affiche les statistiques de jeu des équipes. 
- Cliquer sur une case de la grille pour découvrir la couleur associée. Le choix doit correspondre à l'indice donné par le **SPY**, qui est affiché en haut de la page. 
- Cliquer sur le bouton *Passer le tour* pour passer son tour. 

Le temps restant pour jouer est affiché en haut à droite de la page, avec un sablier qui se vide et tourne sur lui-même. 
La couleur du fond de la page est celle de l'équipe qui doit jouer. 

![Page GUESSERIMAGE](./figures/GUESSERIMAGE.png) 

Cette page est la page du jeu du **GUESSER** en mode **Images**.
On peut constater qu'ici, une case neutre a été révélée (coin supérieur droit). 
# La page SPY

![Page SPY](./figures/SPYMOT.png)

Cette page est la page de jeu du **SPY** en mode **Mots**.
Ici, on constate que c'est au du **SPY bleu** de jouer. 
Plusieurs actions sont possibles :
- Cliquer sur le bouton *Menu* qui affiche un menu déroulant permettant, au choix, de charger une ancienne partie non terminée, sauvegarder la partie actuelle ou retourner à la page d'accueil. 
- Cliquer sur le bouton *Statistics* qui affiche les statistiques de jeu des équipes.
- Cliquer sur le bouton *Afficher la clef* qui affiche la clef de la grille. Il peut ensuite cliquer sur le bouton *Cacher la clef* pour la cacher. 
- Entrer un indice dans le champ de texte puis le nombre de mots associés à cet indice et ensuite cliquer sur le bouton *Valider* afin de finir son tour et d'envoyer l'indice au **GUESSER**. 

La grille se met à jour à chaque fois que le **GUESSER** joue afin que le **SPY** puisse voir les cases découvertes. 
La couleur du fond de la page est celle de l'équipe qui doit jouer.

![Page SPYIMAGE](./figures/SPYIMAGE.png)

Cette page est la page de jeu du **SPY** en mode **Images**. 
On peut constater qu'ici, une case neutre a été révélée (coin supérieur droit). 

# La page GAME OVER

![Page GAME OVER](./figures/GAMEOVERBLUE.png) 

Cette page s'affiche lorsque la partie est terminée et que l'équipe blue a perdu. 
L'utilisateur n'a plus qu'à cliquer sur le bouton *Accueil* pour revenir à la page d'accueil.

![Page GAME OVER](./figures/GAMEOVERRED.png)

Cette page s'affiche lorsque la partie est terminée et que l'équipe rouge a perdu.
L'utilisateur n'a plus qu'à cliquer sur le bouton *Accueil* pour revenir à la page d'accueil. 

# La page STATISTIQUES

![Page STATISTIQUES](./figures/STATS.jpg)

Cette page affiche les statistiques de jeu des équipes.
Elle affiche le nombre de parties jouées, le nombre de parties gagnées par chaque équipe et le ratio indices données / bonnes cartes trouvées. 
Via le menu déroulant *Select Data to Display* l'utilisateur peut choisir d'afficher différentes statistiques comme le nombre de parties jouées, les victoires par équipes, les indices donnés ainsi que les bonnes cartes trouvées via différents graphiques. 
