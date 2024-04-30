#Author: niroshanvc@gmail.com

Feature: Test loqbox signup form

  Background:
    Given User wants to navigate to the signup page

  @Regression
  @SignUp_With_Lite_Option
  Scenario: Test signup form of the loqbox with Lite plan
    Given User enters valid values for all required fields in sign up form
    Then User verifies amounts display in every point matters
    When User clicks on Next button in the every point matters
    And User selects the plan as "Lite"
    Then User verifies the per week amount is "0"
    When User completes the sign up process
    Then User verifies his first name correctly appears in the page

  @Regression
  @SignUp_With_Loqbox_Option
  Scenario: Test signup form of the loqbox with Loqbox plan
    Given User enters valid values for all required fields in sign up form
    Then User verifies amounts display in every point matters
    When User clicks on Next button in the every point matters
    And User selects the plan as "Loqbox"
    Then User verifies the per week amount is "2.50"
    When User enters the payment details and completes the sign up process
    Then User verifies his first name correctly appears in the page

  @Regression
  @SignUp_Validate_Empty_Field_Error_Message
  Scenario: Validation and error messages display in the Sign Up page
    Given User enters empty values for every required field
    Then User verifies error messages display in every required field

  @Regression
  @SignUp_Validate_Invalid_Email_Error_Message
  Scenario: Verify error message for invalid email
    Given User enters invalid email address
    Then User verifies error message display for invalid email address

  @Regression
  @SignUp_Validate_Invalid_Email_Error_Message
  Scenario: Verify error messages for invalid password
    Given User enters password as less than 12 characters
    Then User verifies error message display for password less than 12 characters
    When User refreshes the signup page
    When User enters password without a special character
    Then User verifies error message display for password without special characters
