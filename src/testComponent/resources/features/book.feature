Feature: Gestion des livres
  En tant qu'utilisateur de la bibliothèque
  Je veux pouvoir ajouter, rechercher et supprimer des livres
  Afin de gérer efficacement la collection de livres

  Scenario: Ajouter un livre à la bibliothèque
    Given la bibliothèque est vide
    When j'ajoute un livre avec le titre "Le Petit Prince" et l'auteur "Antoine de Saint-Exupéry"
    Then le livre "Le Petit Prince" doit être présent dans la bibliothèque

  Scenario: Rechercher un livre par titre
    Given la bibliothèque contient un livre avec le titre "1984" et l'auteur "George Orwell"
    When je recherche le livre avec le titre "1984"
    Then le livre "1984" doit être retourné

  Scenario: Supprimer un livre de la bibliothèque
    Given la bibliothèque contient un livre avec le titre "L'Étranger" et l'auteur "Albert Camus"
    When je supprime le livre avec le titre "L'Étranger"
    Then le livre "L'Étranger" ne doit plus être présent dans la bibliothèque

