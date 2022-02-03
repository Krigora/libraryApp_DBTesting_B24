@bookCategory @wip
Feature: Book Category

  Scenario: verify book categories with UI
    Given the user logged in as "librarian"
    When the user navigates to "Books" page
    And the user gets all book categories in webpage
    Then user should be able to see following categories
      | Action and Adventure    |
      | Anthology               |
      | Classic                 |
      | Comic and Graphic Novel |
      | Crime and Detective     |
      | Drama                   |
      | Fable                   |
      | Fairy Tale              |
      | Fan-Fiction             |
      | Fantasy                 |
      | Historical Fiction      |
      | Horror                  |
      | Science Fiction         |
      | Biography/Autobiography |
      | Humor                   |
      | Romance                 |
      | Short Story             |
      | Essay                   |
      | Memoir                  |
      | Poetry                  |

  Scenario: verify book categories with DB
    Given the user logged in as "librarian"
    When the user navigates to "Books" page
    And the user gets all book categories in webpage
    Then verify book categories must match book categories table from db
  @wip@db
  Scenario: Verify book information with db
    Given the user logged in as "librarian"
    And the user navigates to "Books" page
    When I open book "Clean Code"
    Then book information must match the database for "Clean Code"
@NZ
  Scenario: Verify book information with category
    Given the user logged in as "librarian"
    And the user navigates to "Books" page
    And Display book name and related bookCategory name from library
    And  Find me how many book we have in each category
    And  Display most popular book category ( this book needs to borrow to be popular.)
    Then Display how many books are borrowed not turn back yet


