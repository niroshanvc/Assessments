package com.loqbox.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPageLocators {

    @FindBy(name = "firstName")
    public WebElement firstName;

    @FindBy(name = "lastName")
    public WebElement lastName;

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(name = "dob")
    public WebElement dateOfBirth;

    @FindBy(name = "phone")
    public WebElement mobilePhoneNumber;

    @FindBy(name = "zipcode")
    public WebElement postcode;

    public final String dateOfBirthYear = "//div[contains(@class, 'MuiPickersYear-')]";

    @FindBy(xpath = "(//button[contains(@class, 'MuiPickersCalendarHeader')])[1]")
    public WebElement calendarPreviousMonth;

    @FindBy(xpath = "(//button[contains(@class, 'MuiPickersCalendarHeader')])[2]")
    public WebElement calendarNextMonth;

    @FindBy(xpath = "//div[contains(@class, 'switchHeader')]/div/p")
    public WebElement monthYearText;

    public final String dates = "//button[contains(@class, 'MuiPickersDay') and not(contains(@class, 'MuiPickersDay-hidden'))]/span/p";

    @FindBy(xpath = "//p[text()='Look up']/..")
    public WebElement lookUpButton;

    public final String addressXpath = "//select[@name='addressDropdown']";

    @FindBy(xpath = "//select[@name='addressDropdown']")
    public WebElement addressElement;

    @FindBy(xpath = "//p[text()='Next']/..")
    public WebElement nextButton;

    @FindBy(xpath = "//p[text()='Loqbox']/../..")
    public WebElement loqboxPlan;

    @FindBy(xpath = "//p[text()='Lite']/../..")
    public WebElement litePlan;

    @FindBy(xpath = "//p[contains(text(), 'per week')]")
    public WebElement perWeek;

    @FindBy(xpath = "//p[text()='Show me how']/..")
    public WebElement showMeHowButton;

    @FindBy(xpath = "(//tr[contains(@class, 'MuiTableRow')])[2]/td[2]/p[1]")
    public WebElement seventyPointPersonalLoanAmount;

    @FindBy(xpath = "(//tr[contains(@class, 'MuiTableRow')])[3]/td[2]/p[1]")
    public WebElement seventyPointCreditCardAmount;

    @FindBy(xpath = "(//tr[contains(@class, 'MuiTableRow')])[4]/td[2]/p[1]")
    public WebElement seventyPointTotalAmount;

    @FindBy(xpath = "(//tr[contains(@class, 'MuiTableRow')])[2]/td[3]/p[1]")
    public WebElement threeHundredPointPersonalLoanAmount;

    @FindBy(xpath = "(//tr[contains(@class, 'MuiTableRow')])[3]/td[3]/p[1]")
    public WebElement threeHundredPointCreditCardAmount;

    @FindBy(xpath = "(//tr[contains(@class, 'MuiTableRow')])[4]/td[3]/p[1]")
    public WebElement threeHundredPointTotalAmount;

    @FindBy(id = "cardnumber")
    public WebElement debitCardNumber;

    @FindBy(id = "cardholder_name")
    public WebElement debitCardHolderName;

    @FindBy(id = "cardexp")
    public WebElement expiry;

    @FindBy(id = "cardcvv")
    public WebElement cardVerificationCode;

    @FindBy(id = "pay-now")
    public WebElement finishSetupButton;

    @FindBy(xpath = "//div[contains(@class, 'MuiInputAdornment') and not(@style)]")
    public WebElement makePasswordVisible;

    @FindBy(id = "signup/create-account/first-name-helper-text")
    public WebElement firstNameError;

    @FindBy(id = "signup/create-account/last-name-helper-text")
    public WebElement lastNameErrorMessage;

    @FindBy(id = "signup/create-account/email-helper-text")
    public WebElement emailErrorMessage;

    @FindBy(xpath = "//p[contains(@id, 'password')]")
    public WebElement passwordErrorMessage;

    @FindBy(xpath = "//input[@name='dob']/../../p")
    public WebElement dateOfBirthErrorMessage;

    @FindBy(id = "signup/create-account/phone-helper-text")
    public WebElement mobilePhoneNumberErrorMessage;

    @FindBy(id = "signup/create-account/post-code-helper-text")
    public WebElement postcodeErrorMessage;

    @FindBy(name = "buildingName")
    public WebElement buildingName;

    @FindBy(name = "buildingNumber")
    public WebElement buildingNumber;

    @FindBy(name = "address1")
    public WebElement addressLine1;

    @FindBy(name = "address2")
    public WebElement addressLine2;

    @FindBy(name = "town")
    public WebElement town;

    @FindBy(name = "state")
    public WebElement county;
}
