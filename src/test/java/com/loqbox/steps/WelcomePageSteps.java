package com.loqbox.steps;

import com.loqbox.actions.WelcomePageActions;
import io.cucumber.java.en.Then;

public class WelcomePageSteps {

    WelcomePageActions welcomePageActions = new WelcomePageActions();

    @Then("User verifies his first name correctly appears in the page")
    public void userVerifiesHisFirstNameCorrectlyAppearsInThePage() {
        welcomePageActions.verifyFirstName();
    }
}
