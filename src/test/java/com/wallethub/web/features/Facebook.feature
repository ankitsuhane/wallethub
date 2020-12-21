#This feature file is for the facebook login.
#Please add username password in the properties files

Feature: Facebook login

  @Web
  Scenario: Login to facebook say hello and logout
    Given I want to click login to facebook
    Then Post a status message "Hello World" and logout
