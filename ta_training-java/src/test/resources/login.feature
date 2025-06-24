Feature: Login Functionality

  Scenario: UC-1 Test Login form with empty credentials
    Given I am on the login page
    When I enter "test" into the username field
    And I enter "test" into the password field
    And I clear the username field
    And I clear the password field
    And I click the login button
    Then I should see the error message "Username is required"