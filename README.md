
###Cahier des charges et contexte

Utiliser la modularité de la programmation objet pour que le code soit ensuite réutilisable dans le projet principal Escape game (donc pas de programmation procédurale !). Il faut qu’on puisse extraire les packages/classes à terme.
Un fichier de configuration doit contenir les paramètres de l’application (nombre de chiffres dans la combinaison, nombre d’essais, mode “développeur” activé ou non).
Un mode “développeur” doit pouvoir être activé via un paramètre. Ce mode affiche la solution dès le début du jeu.
Un fichier de configuration ( log4j.xml ) permettra de paramétrer les logs de l'application. La gestion des logs se fera avec Apache Log4j.
Mettre le code sur un repository github, d’indenter, commenter et formater le nom des variables.

##Présentation du projet

Le projet consiste à créer une application d'un jeu recherche +/- avec trois modes (Challenger, Défenseur et Duel (expliqués dans la règle du jeu)), un fichier config, log et README

#Guide d'utilisation du jeu recherche +/-

- Lancer l'application
- Choisissez un mode parmis les 3 modes proposés

- Tapez CH puis la touche entrée du clavier pour le mode Challenger
- Tapez DE puis la touche entrée du clavier pour le mode Defenseur
- Tapez DU puis la touche entrée du clavier pour le mode Duel

**Mode Challenger**

- Il y a X essais (X est précisé au début, il est changeable dans le fichier confog.properties)
- L'ordinateur génère une combinaison secrète à X chiffres aléatoirement (X est précisé au début, il est changeable dans le fichier confog.properties)
- Le joueur saisit sa combinaison et valide en appuyant sur la touche entrée du clavier
- L'ordinateur donne sa réponse 
- Le joueur rectifie sa combinaison en fonction de la réponse de l'ordinateur et valide en appuyant sur la touche entrée du clavier
- Le joueur gagne si il trouve la combinaison secrète de l'ordinateur ou perd si il ne la trouve pas au bout du nombre d'essais donnés au début

**Mode Challenger**

- Il y a X essais (X est précisé au début, il est changeable dans le fichier confog.properties)
- Le joueur rentre une combinaison secrète à X chiffres et valide en appuyant sur la touche entrée (X est précisé au début, il est changeable dans le fichier confog.properties)
- L'ordinateur saisit sa combinaison aléatoirement
- Le joueur donne sa réponnse (avec des signes opératoires, par exemple: =,+,-,+) et valide en appuyant sur la touche entrée du clavier
- L'ordinateur rectifie sa combinaison en fonction de la réponse du joueur
- L'ordinateur gagne si il trouve la combinaison secrète du joueur ou perd si il ne la trouve pas au bout du nombre d'essais donnés au début

**Mode Duel**

- Il y a X essais (X est précisé au début, il est changeable dans le fichier confog.properties)
- Le joueur rentre une combinaison secrète à X chiffres et valide en appuyant sur la touche entrée (X est précisé au début, il est changeable dans le fichier confog.properties)
- L'ordinateur génère une combinaison secrète à X chiffres aléatoirement (X est précisé au début, il est changeable dans le fichier confog.properties)
- Le joueur saisit sa combinaison et valide en appuyant sur la touche entrée du clavier
- Le joueur donne sa réponnse (avec des signes opératoires, par exemple: =,+,-,+) et valide en appuyant sur la touche entrée du clavier
- L'ordinateur donne sa réponse 
- Le joueur rectifie sa combinaison en fonction de la réponse de l'ordinateur et valide en appuyant sur la touche entrée du clavier
- L'ordinateur rectifie sa combinaison en fonction de la réponse du joueur

 
 
### Règles du jeu

**Mode challenger:**

  1.  L'intelligence artificielle de l’ordinateur joue le rôle de défenseur. Elle définit une combinaison de X chiffres aléatoirement.
  1.  Le joueur a le rôle d’attaquant et doit faire une proposition d’une combinaison de X chiffres.
  1.  L'intelligence artificielle de l’ordinateur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
  1.  Il y a un nombre limité d’essais.
 
**Mode défenseur:**
 
  1.  Le joueur (cette fois dans le rôle de défenseur) définit une combinaison de X chiffres aléatoirement.
  1.  L'intelligence artificielle de l’ordinateur doit faire une proposition d’une combinaison de X chiffres (c’est le rôle attaquant).
  1.  Le joueur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
  1.  L’intelligence artificielle fait une autre proposition en se basant sur la réponse fournit par le joueur.
  1.  Il y a un nombre limité d’essais.
  
**Mode Duel:**
  
Le joueur et l’intelligence artificielle de l’ordinateur jouent tour à tour. Le premier à trouver la combinaison secrète de l'autre a gagné ! 


##Pré-requis pour lancer le projet

La version Java 1.8 minimum est requise pour lancer l'application

## Description de l'arborescence du livrable


## Lancement du jeu

Le lancement du jeu se fait à partir du fichier .jar

## Lien vers Git

## Auteur:
Fabien Chapeau pour Openclassrooms