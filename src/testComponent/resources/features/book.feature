Feature: the user can create and retrieve the books
  Scenario: user creates two books and retrieve both of them
    When the user creates the book "Les Misérables" written by "Victor Hugo"
    And the user creates the book "L'avare" written by "Molière"
    And the user get all books
    Then the list should contains the following books in the same order
      | title | author |
      | L'avare | Molière |
      | Les Misérables | Victor Hugo |

  Scenario: user reserves a book that is available
    When the user creates the book "1984" written by "George Orwell"
    And the user reserves the book titled "1984"
    Then the user should see that the book titled "1984" is reserved


  Scenario: user tries to reserve a book that is already reserved
    When the user creates the book "1984" written by "George Orwell"
    And the user reserves the book titled "1984"
    And the user reserves the book titled "1984"
    Then the request should fail with status 400


  Scenario: user tries to reserve a non-existing book
    When the user reserves the book titled "Nonexistent"
    Then the request should fail with status 404