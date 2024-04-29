package com.loqbox.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePageLocators {

    @FindBy(xpath = "//div[contains(@class, 'MuiBox') and @style]/p[contains(@class, 'MuiTypography')]")
    public WebElement welcomeMessage;
}
