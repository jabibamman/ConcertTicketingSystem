# Système de Billetterie Multithreadé

## Contexte

Dans ce projet, vous allez développer un **système de billetterie multithreadé** en Java. L'objectif est de gérer la réservation de billets pour un concert en simulant un grand nombre d'utilisateurs essayant d'acheter des billets en même temps.

Vous devrez utiliser les concepts et techniques appris en cours, comme les structures 
concurrentes et les outils de synchronisation (`Semaphore`, `ReadWriteLock`), pour garantir 
l'intégrité des données et la fluidité du système.

---

## Objectifs du Projet

### Fonctionnalités à implémenter :
1. **Gestion des billets :**
   - Suivre le nombre total de billets disponibles.
   - Permettre aux utilisateurs de réserver un billet s'il en reste.

2. **Concurrence et synchronisation :**
   - Gérer les réservations concurrentes en évitant les conditions de course.
   - Mettre en œuvre des mécanismes avancés comme :
      - `AtomicInteger` pour le suivi global des billets.
      - `Semaphore` pour limiter le nombre d'utilisateurs simultanés.
      - `PriorityBlockingQueue` pour gérer les utilisateurs VIP avec une priorité plus élevée.
      - `ReadWriteLock` pour autoriser plusieurs lectures tout en verrouillant les écritures.

3. **Robustesse :**
   - Traiter les exceptions éventuelles (réservations impossibles, threads interrompus).
   - Tester votre système avec plusieurs threads pour simuler un grand nombre d'utilisateurs.

---

## Barème d'Évaluation (Sur 20 points)

### Techniques Utilisées (15 points) :
- **AtomicInteger** pour le stock global de billets (3 points).
- **ConcurrentHashMap** pour gérer les places individuelles (3 points).
- **Semaphore** pour limiter les utilisateurs simultanés (3 points).
- **PriorityBlockingQueue** pour gérer les requêtes priorisées (3 points).
- **ReadWriteLock** pour la synchronisation des accès partagés (3 points).

### Techniques Bonus (5 points) :
- **StampedLock** qui remplacerait **ReadWriteLock** pour une gestion flexible des verrous (2 
  points + 3 donné car le lock est mis en place).
- **CompletableFuture** pour un traitement asynchrone des réservations (1 point).
- **Exchanger** pour synchroniser des données entre threads (1 point).
- **ConcurrentHashMap** pour suivre les utilisateurs et leurs réservations. (1 point)


---

## Livrables

1. **Code source :**
   - Implémentation fonctionnelle et documentée.
   - Gestion correcte des threads et de la concurrence.

2. **Documentation :**
   - Rapport expliquant les choix techniques, les défis rencontrés, et les solutions apportées.

3. **Tests :**
   - Tests unitaires démontrant la robustesse et l'efficacité du système.
   - Scénarios simulant une forte charge avec plusieurs utilisateurs concurrents.

4. **Présentation :**
   - Une démonstration fonctionnelle du projet.

**Il faut que les livrables soit zippé en un et impérativement uploadé sur MyGES**

---

## Instructions pour démarrer

1. **Cloner le dépôt du projet :**
   ```bash
   git clone https://github.com/votre-projet/ConcertTicketingSystem.git
   cd ConcertTicketingSystem
   ```
   
2. **Configurer l'environnement :**
   - Installez Java 23.
   - Configurez votre IDE (IntelliJ IDEA recommandé).
   - Avoir Docker installé sur votre machine.

3. Lancer le projet :

   - Assurez-vous que Docker est en cours d'exécution.
   - Faites la commande suivante pour lancer le serveur de base de données :
     ```bash
     docker-compose up -d
     ```
   L'user et le mot de passe de la base de données sont respectivement `admin` et `admin`.

   - Le point d'entrée se trouve dans Main.java.
   - Exécutez le fichier Main.java pour commencer.

3. Développer les fonctionnalités :

   - Complétez les classes TicketService, UserService, et autres nécessaires.
   - Implémentez les techniques indiquées dans le barème.
   
## Notes
- Soyez créatifs! Vous pouvez ajouter des fonctionnalités supplémentaires en voici certaines:
   -   Un système de notification (confirmation de réservation).
   - Une interface graphique ou une simulation avec des logs détaillés.
   -   Un gestionnaire de paiements fictif.

- Consultez les ressources vues en cours et la documentation officielle de Java pour vous aider.
      
Bonne chance !



