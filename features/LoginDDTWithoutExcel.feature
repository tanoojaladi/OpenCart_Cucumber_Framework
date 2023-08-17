Feature: Login Data Driven without Excel

  #scenario outline this scenario will excute multiple times based on data we provided in examples
  Scenario Outline: Login Data Driven without Excel
    Given user Launch browser
    And opens url "http://localhost/opencart/upload/index.php?route=common/home&language=en-gb"
    When user navigates to My Account menu
    And click on login
    And user enters email as "<email>" and password as "<password>"
    And click on login button
    Then user nagivates MY Account Page

    Examples: 
      | email                     | password |
      | tanu123@gmail.com         | tanu123  |
      | tanu@gmail.com            | tanu     |
      | pavanoltraining@gmail.com | test@123 |
