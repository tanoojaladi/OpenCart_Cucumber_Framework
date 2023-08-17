Feature: tanu

  #scenario outline this scenario will excute multiple times based on data we provided in examples
  Scenario Outline: tanu
    Given user Launch browser
    And opens url "http://localhost/opencart/upload/index.php?route=common/home&language=en-gb"
    When user navigates to My Account menu
    And click on login
    And user enters email as "<email>" and password as "<password>"
    And click on login button
    Then verify user navigates to my account page according to the "<results>"

    Examples: 
      | email                     | password |  | results |
      | tanu123@gmail.com         | tanu123  |  | valid   |
      | tanu@gmail.com            | tanu     |  | valid   |
      | pavanoltraining@gmail.com | test@123 |  | invalid |
