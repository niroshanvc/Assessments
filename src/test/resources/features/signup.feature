#Author: niroshanvc@gmail.com

Feature: Test loqbox signup form


  @SignUp_Lite
  Scenario: Test signup form of the loqbox with Lite plan
    Given User wants to navigate to the signup page
    When User enters valid values for all required fields in sign up form
    Then User verifies amounts display in every point matters
    When User clicks on Next button in the every point matters
    And User selects the plan as "Lite"
    Then User verifies the per week amount is "0"
    When User completes the sign up process
    Then User verifies his first name correctly appears in the page

  @SignUp_Loqbox
  Scenario: Test signup form of the loqbox with Loqbox plan
    Given User wants to navigate to the signup page
    When User enters valid values for all required fields in sign up form
    Then User verifies amounts display in every point matters
    When User clicks on Next button in the every point matters
    And User selects the plan as "Loqbox"
    Then User verifies the per week amount is "2.50"
    When User enters the payment details and completes the sign up process
    Then User verifies his first name correctly appears in the page

  @SignUp_NegativeScenario
  Scenario: Test validation and error messages displays in the Sign Up page
    Given User wants to navigate to the signup page
    When User enters empty values for every required field
    Then User verifies error messages display in every required field
    When User refreshes the signup page
    And User enters invalid email address
    Then User verifies error message display for invalid email address
    When User refreshes the signup page
    And User enters password as less than 12 characters
