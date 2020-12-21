#This feature file is for the Wallet hub review rating.
#Please add username password in the properties files

Feature: Wallet hub review rating

  @Web
  Scenario: To give rating for a product and verify it
    Given User landing to the test insurance_company
    When Submit the 4 star rating for a Health Insurance product
    Then check it on the review feed page
