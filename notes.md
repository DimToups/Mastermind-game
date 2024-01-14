# Notes
## Questions à creuser
- Est-ce que les observateurs ne seraient pas des stratégies puisqu'elles sont uniques et nécessaires au fonctionnement du jeu ?
## Décisions
### Observateurs
Il n'y a qu'un seul observateur pour chaque instance de Partie, de Manche et de Tentative parce que ce sont des vues qui demandent souvent à modifier des données. Il est préférable de n'avoir qu'un seul observateur pour éviter qu'ils ne se marchent dessus
### Modes de jeu
Il y a deux solutions principales pour intégrer les modes de jeux avec un lien aux sous-classes d'ObservateurUI :
- Soit on abandonne le patron de conception de stratégie en greffant les trois méthodes d'affichages à l'ObservateurUI
- Soit en convertissant les modes de jeux en classes abstraites et devant créer une sous-classe à chaque mode de jeu pour chaque classe implémentant ObservateurUI

La première solution permettrait de contenir toutes les méthodes dans l'UI et obligerait le concepteur à écrire toutes les méthodes d'affichages de modes de jeux mais nous serons contraint à accepter la problématique du patron de conception des stratégies.
La seconde solution conserve tous les patrons de conceptions mais cela demanderait à avoir un nombre démesuré de sous-classes de stratégies pour respecter les patrons de conceptions. Cela engendrerait une pollution excessive de minuscules classes 
## Bonus
- Voir pour ajouter ces tests unitaires
  - Vérifier que l'observateur appelle bien le contrôleur
  - Vérifier que tous les attributs comme tailleCombinaison respectent le [README](ConsignesProjet)
- Voir pour stocker les joueurs entre les lancements de l'application
## Affichage par fenêtre
### Menu
- Demande du pseudo du joueur dans une zone de texte
- Demande du nombre de manches à l'aide de radio buttons
- Demande de la taille des combinaisons à l'aide de radio buttons
- Demande du nombre de tentatives par manche à l'aide de radio buttons
- Demande du mode de jeu à l'aide d'un menu déroulant
- Affichage d'un bouton pour lancer la partie (qui ne marche que si toutes les informations sont entrées)
### Plateau
- L'affichage des couleurs est sur la gauche
- Le plateau contient des tentatives de taille fixe et il sera placé au milieu de la fenêtre sur l'axe vertical
- La tentative actuelle est indiquée par une flèche sur la gauche
- ~~Un menu déroulant est disponible pour changer le mode de jeu~~ (Annulé suite à la mise à jour du cahier des charges)
- Le numéro de la manche est affiché au-dessus du plateau
- Un bouton pour passer la manche est disponible sur le haut du plateau
- Le placement des couleurs sera fait en cliquant sur l'emplacement puis sur la couleur ou l'inverse. Une ombre sera affichée sur l'emplacement et sur la couleur lorsqu'elles sont cliquées
- La fin de manche affiche la combinaison secrète et passera à la prochaine manche lorsque le bouton 'Manche suivante' est cliqué. Un menu déroulant est aussi disponible pour changer de mode de jeu pour la manche suivante
### Fin de partie
- Le nom du joueur apparaît avec son score
- Le nombre de manches jouées et gagnées est affiché
### Liste des joueurs (?)

