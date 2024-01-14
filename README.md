# Projet Mastermind
## Introduction
### Principe du projet
Le projet a été fait dans le cadre de la deuxième année de B.U.T. informatique à l'IUT Robert Schumman. Le but est de développer le jeu Mastermind à l'aide de Java Swing en respectant l'architecture MVC.

L'ensemble des consignes données pour le projet sont disponibles dans le fichier [README.md](ConsignesProjet.md) original.
# Technologies utilisées
Pour réaliser ce projet, différentes technologies ont été utilisées. L'entièreté du code a été fait en **Java** et tout ce qui touche directement à la fenêtre a été fait avec **Java Swing**.

La documentation a été faite à l'aide de **JavaDoc** et la conception a été faite avec **Plant UML**.

Le projet a été fait avec **Git** pour pouvoir garder différentes versions du projet et pour collaborer.

Les designs apparaissant dans l'application ont dans la majorité été produits avec le logiciel **Figma**, le reste des éléments graphiques sont les éléments de base générés par **Java Swing**.
## Installation et utilisation
Toutes les instructions d'installation et d'utilisation sont disponibles dans le fichier [INSTALL.md](INSTALL.md) à la racine du projet.
## Informations techniques
### Accès à la documentation JavaDoc
Le projet a une documentation générée à l'aide de JavaDoc, elle est disponible dans le répertoire nommé [docs](docs) à la racine. Cette documentation vous permettra de voir le fonctionnement de chaque classe et méthode du projet. Sa page principale est nommée [package-summary.html](docs/src/package-summary.html) et vous permettra de naviguer facilement à travers la documentation du projet. 
### Mécaniques du projet
#### Les modes de jeux
Le jeu peut être joué avec différents modes de jeux. De base, il n'y a que les modes de jeux "facile", "classique" et "numérique". Les modes de jeux vont changer l'affichage des indices que le joueur va avoir lors de sa partie.

Pour ajouter un nouveau mode de jeu, il faut suivre les instructions suivantes :
- Ajouter le nom du mode de jeu dans [ModeJeu](src/model/enums/ModeJeu.java)
- Ajouter une méthode abstraite d'affichage des indices dans [AfficheurIndices](src/model/userInterfaces/AfficheurIndices.java)
    - Implémenter la méthode dans toutes les sous-classes d'[ObservateurUI](src/model/userInterfaces/ObservateurUI.java) en respectant le principe d'affichage de la classe modifiée
- Modifier la méthode d'affichage des indices au démarrage d'une manche dans la classe [AffichageFenetre](src/view/AffichageFenetre.java) si l'affichage des indices doit être différent
#### Les types d'affichages
Originalement, notre jeu ne s'affiche qu'avec l'aide d'une fenêtre, cependant, il est possible de créer son propre affichage. Pour cela, il suffit de créer une classe dans [src/view](src/view) implémentant [ObservateurUI](src/model/userInterfaces/ObservateurUI.java). De nombreuses méthodes sont disponibles pour créer votre propre affichage.
