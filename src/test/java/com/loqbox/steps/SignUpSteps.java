package com.loqbox.steps;

import com.loqbox.actions.SignUpPageActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpSteps {

    SignUpPageActions signUpPageActions = new SignUpPageActions();

    @Given("User wants to navigate to the signup page")
    public void navigateToSignUpPage() {
        signUpPageActions.navigateToSignUpPage();
    }

    @And("User enters valid values for all required fields in sign up form")
    public void enterSignUpData() {
        signUpPageActions.fillSignUpForm();
    }

    @When("^User selects the plan as \"([^\"]*)\"$")
    public void selectPlan(String plan) {
        signUpPageActions.selectPlan(plan);
    }

    @Then("^User verifies the per week amount is \"([^\"]*)\"$")
    public void verifyPerWeekAmount(String planAmount) {
        signUpPageActions.verifyPerWeekAmount(planAmount);
    }

    @When("User completes the sign up process")
    public void cCompleteSignUpProcess() {
        signUpPageActions.completeSignUpProcess();
    }

    @Then("User verifies amounts display in every point matters")
    public void verifyAmountsDisplayInEveryPointMatters() {
        signUpPageActions.verifySeventyPointIncreaseTotalAmount();
        signUpPageActions.verifyThreeHundredPointIncreaseTotalAmount();
    }

    @When("User clicks on Next button in the every point matters")
    public void clickOnNextButtonInEveryPointMatters() {
        signUpPageActions.clickNextOnEveryPointMatters();
    }

    @When("User enters the payment details and completes the sign up process")
    public void enterPaymentDetailsAndCompleteSignUpProcess() {
        signUpPageActions.enterPaymentDetailsAndCompleteSignUpProcess();
    }

    @When("User enters empty values for every required field")
    public void enterEmptyValuesForEveryRequiredField() {
        signUpPageActions.enterEmptyValuesForEveryRequiredField();
    }

    @Then("User verifies error messages display in every required field")
    public void verifyErrorMessagesDisplayInEveryRequiredField() {
        signUpPageActions.verifyEmptyFieldErrorMessages();
    }

    @When("User refreshes the signup page")
    public void refreshesSignupPage() {
        signUpPageActions.refreshSignupPage();
    }

    @And("User enters invalid email address")
    public void enterInvalidEmailAddress() {
        signUpPageActions.enterInvalidEmailAddress();
    }

    @Then("User verifies error message display for invalid email address")
    public void userVerifiesErrorMessageDisplayForInvalidEmailAddress() {
        signUpPageActions.verifyErrorMessageDisplayForInvalidEmailAddress();
    }

    @And("User enters password as less than 12 characters")
    public void userEntersPasswordAsLessThanCharacters() {
        signUpPageActions.enterInvalidPassword();
    }

    @Then("User verifies error message display for password less than 12 characters")
    public void verifyErrorMessageForPasswordLessThanTwelveCharacters() {
        signUpPageActions.verifyPasswordRequiredLengthErrorMessage();
    }

    @When("User enters password without special characters")
    public void enterPasswordWithoutSpecialCharacters() {
        signUpPageActions.passwordWithoutSpecialCharacters();
    }

    @Then("User verifies error message display for password without special characters")
    public void verifyErrorMessageForPasswordWithoutSpecialCharacters() {
        signUpPageActions.verifyPasswordWithoutSpecialCharacterErrorMessage();
    }
}
