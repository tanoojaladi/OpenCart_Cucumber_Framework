Feature: Login Data Driven With Excel

  Scenario Outline: Login Data Driven with Excel
    Given user Launch browser
    And opens url "http://localhost/opencart/upload/index.php?route=common/home&language=en-gb"
    When user navigates to My Account menu
    And click on login
    Then check User navigates to MyAccount Page by passing Email and Password with excel row "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      
