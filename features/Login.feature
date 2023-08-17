Feature: Login with valid credentials

@sanity @regression
     Scenario:Successfull login with valid credentials
       Given user Launch browser
       And opens url "http://localhost/opencart/upload/index.php?route=common/home&language=en-gb"
       When user navigates to My Account menu
       And click on login
       And user enters email as "tanu123@gmail.com" and password as "tanu123"
       And click on login button
       Then user nagivates MY Account Page