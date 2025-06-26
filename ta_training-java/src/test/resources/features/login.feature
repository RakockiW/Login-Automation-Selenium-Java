Feature: Login Functionality
  Scenario: UC-1 Test Login form with empty credentials
    Given I am on the login page
    When I enter "test" into the username field
    And I enter "test" into the password field
    And I clear the username field
    And I clear the password field
    And I click the login button
    Then I should see the error message "Username is required"

  Scenario: UC-2 Test Login form with credentials by passing only Username
    Given I am on the login page
    When I enter "test" into the username field
    And I enter "test" into the password field
    And I clear the password field
    And I click the login button
    Then I should see the error message "Password is required"

  Scenario Outline: UC-3 Test Login form with credentials by passing Username & Password
    Given I am on the login page
    When I enter "<username>" into the username field
    And I enter "secret_sauce" into the password field
    And I click the login button
    Then I should see the title "Swag Labs" on the dashboard

    Examples: Valid Usernames
      | username                |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |