package com.loqbox.actions;

import com.loqbox.locators.SignUpPageLocators;
import com.loqbox.locators.WelcomePageLocators;
import com.loqbox.utils.BasePage;
import com.loqbox.utils.GlobalVariables;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WelcomePageActions {

    WelcomePageLocators welcomePageLocators;
    SignUpPageLocators signUpPageLocators;

    public WelcomePageActions() {
        welcomePageLocators = new WelcomePageLocators();
        PageFactory.initElements(BasePage.getDriver(), welcomePageLocators);
    }

    public void verifyFirstName() {
        BasePage.waitUntilElementPresent(welcomePageLocators.welcomeMessage, 90);

        String welcomeText = BasePage.getText(welcomePageLocators.welcomeMessage);

        //split using Welcome to Loqbox texts and gets the second element from the array
        String secondPart = welcomeText.split("Welcome to Loqbox ")[1];

        //split above text using ! mark and gets the first element from the array
        String firstName = secondPart.split("!")[0];

        String expectedFirstName = GlobalVariables.getFirstName();
        assertThat(firstName, is(expectedFirstName));
    }
}
