L'application paymybuddy a été créée en suivant les instructions, certaines fonctions non demandées ont été implémentées pour permettre la démonstration de bon fonctionnement du logiciel

l'application a été créée en utilisant SPRING Jpa mysql SPRING security ThYMELEAF SPRING WEB

L'application a été designée sous le pattern REPOSITORY MVC

L'analyse UML montrait qu'il était necessaire de disposer d'une entité USER, d'une entité CONNEXION qui représente les users qui établissent des transactions de 1 à 1  et enfin une entité TRANSACTION qui représentait une transaction établit par deux users définit dans une connexion

MVC Classique un controlleur sur chaque vue en interaction avec l'utilisateur, un service qui rassemeble tous les calculs, un repository qui effectue la liaison bidirectionnelle entre le service et la base de données MySQL, un model contenant les entités.

la base de données a été générée directement par SPRING au travers des entités et des diverses relations entre les entités du Modele.

Le code est commenté et explique la gestion des transactions


![umlPaymyBuddy](https://user-images.githubusercontent.com/56394467/164676262-6bf5dfac-3cea-4d5e-89fd-c50c74925753.png)

![PayMyBuddympd](https://user-images.githubusercontent.com/56394467/164675699-8f17380d-6ac8-41fa-bc55-34c620c3e667.png)
