package com.loqbox.actions;

import com.github.javafaker.Faker;
import com.loqbox.locators.SignUpPageLocators;
import com.loqbox.utils.BasePage;
import com.loqbox.utils.GlobalVariables;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SignUpPageActions {

    SignUpPageLocators signUpPageLocators;

    private final Faker faker = new Faker(Locale.UK);
    Random random = new Random();

    public final String firstName = faker.name().firstName(); //generate fake first name
    String lastName = faker.name().lastName(); //generate fake last name
    String email = faker.internet().emailAddress(); //generate fake email address
    //generate fake password with minimum length 12, maximum length 15, minimum 1 uppercase,
    // minimum 1 special character and minimum 1 number
    String password = faker.internet().password(12, 15, true, true, true);
    String passwordWithLessThanTwelveCharacters = faker.internet().password(10, 11, true, true, true);
    String passwordWithoutSpecialCharacters = faker.internet().password(12, 15, true, false, true);

    public SignUpPageActions() {
        signUpPageLocators = new SignUpPageLocators();
        PageFactory.initElements(BasePage.getDriver(), signUpPageLocators);
    }

    public void navigateToSignUpPage(){
        BasePage.navigate("url");
    }

    public void fillSignUpForm() {
        BasePage.waitUntilElementPresent(signUpPageLocators.firstName, 90);
        BasePage.typeWithStringBuilder(signUpPageLocators.firstName, firstName);
        BasePage.clickTabKey(signUpPageLocators.firstName);
        GlobalVariables.setFirstName(BasePage.getAttributeValue(signUpPageLocators.firstName, "value"));
        BasePage.typeWithStringBuilder(signUpPageLocators.lastName, lastName);
        BasePage.typeWithStringBuilder(signUpPageLocators.email, email);
        BasePage.typeWithStringBuilder(signUpPageLocators.password, password);
        BasePage.scrollToWebElement(signUpPageLocators.dateOfBirth);
        selectDateFromCalendar();
        BasePage.typeWithStringBuilder(signUpPageLocators.mobilePhoneNumber, BasePage.getProperty("mobile_phone"));
        BasePage.typeWithStringBuilder(signUpPageLocators.postcode, BasePage.getProperty("postcode"));
        BasePage.clickTabKey(signUpPageLocators.postcode);
        BasePage.waitUntilElementClickable(signUpPageLocators.lookUpButton, 10);
        BasePage.clickAfterWait(signUpPageLocators.lookUpButton);
        BasePage.waitUntilElementPresent(signUpPageLocators.addressElement, 10);
        BasePage.selectFirstOption(signUpPageLocators.addressXpath);
        BasePage.waitUntilElementClickable(signUpPageLocators.nextButton, 10);
        BasePage.scrollToWebElement(signUpPageLocators.nextButton);
        BasePage.clickAfterWait(signUpPageLocators.nextButton);
    }

    private void selectDateFromCalendar() {
        //get the year text from the generated random date
        String year = generateRandomDate().split("-")[0];

        //get the month text from the generated random date
        int monthInt = Integer.parseInt(generateRandomDate().split("-")[1]);

        //convert month int into month name
        String monthName = new DateFormatSymbols().getMonths()[monthInt -1];

        //get the day text from the generated random date
        String day = generateRandomDate().split("-")[2];

        BasePage.clickAfterWait(signUpPageLocators.dateOfBirth);

        //select correct year from year list
        List<WebElement> yearElements = BasePage.findListOfWebElements(signUpPageLocators.dateOfBirthYear);
        for(WebElement yearElement : yearElements) {
            BasePage.scrollToWebElement(yearElement);
            if(yearElement.getText().equals(year)) {
                BasePage.clickAfterWait(yearElement);
                break;
            }
        }

        //navigating to correct month
        //get the month text display in the calendar
        String displayMonth = BasePage.getText(signUpPageLocators.monthYearText).split(" ")[0];

        //if the month to be selected is less than the display month, click on the previous button on the calendar
        // otherwise click on the next button
        if(Month.valueOf(displayMonth.toUpperCase()).getValue() >= monthInt) {
            while (!displayMonth.equalsIgnoreCase(monthName)) {
                BasePage.clickAfterWait(signUpPageLocators.calendarPreviousMonth);
                displayMonth = BasePage.getText(signUpPageLocators.monthYearText).split(" ")[0];
            }
        } else {
            while (!displayMonth.equalsIgnoreCase(monthName)) {
                BasePage.clickAfterWait(signUpPageLocators.calendarNextMonth);
                displayMonth = BasePage.getText(signUpPageLocators.monthYearText).split(" ")[0];
            }
        }

        //selecting correct date
        List<WebElement> dateElements = BasePage.findListOfWebElements(signUpPageLocators.dates);
        for(int i=0; i<dateElements.size(); i++){
            if(dateElements.get(i).getText().equals(day)) {
                BasePage.clickAfterWait(dateElements.get(i));
                break;
            }
        }
    }

    private String generateRandomDate() {
        LocalDate currentDate = LocalDate.now();

        // Generate a random year between 18 and 100 years ago
        int randomYear = random.nextInt(83) + 18; // 100 - 18 + 1 = 83, so we add 18 to ensure it's between 18 and 100

        // Generate a random month
        int randomMonth = random.nextInt(12) + 1; // Month value should be between 1 and 12

        // Generate a random day within the range of the selected month
        int randomDay = random.nextInt(Month.of(randomMonth).length(currentDate.isLeapYear())) + 1;

        // Create the random date
        LocalDate randomDate = LocalDate.of(currentDate.getYear() - randomYear, randomMonth, randomDay);
        //format the random date without leading zero for day
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

        return randomDate.format(formatter);
    }

    public void selectPlan(String plan) {
        BasePage.waitUntilElementPresent(signUpPageLocators.loqboxPlan, 10);
        if(plan.equalsIgnoreCase("loqbox"))
            BasePage.clickAfterWait(signUpPageLocators.loqboxPlan);
        else
            BasePage.clickAfterWait(signUpPageLocators.litePlan);
    }

    public void verifyPerWeekAmount(String planAmount) {
        BasePage.waitUntilElementPresent(signUpPageLocators.perWeek, 30);
        String perWeekTexts = BasePage.getText(signUpPageLocators.perWeek);

        //split the above text using space character and gets the first element
        String amountWithPound = perWeekTexts.split(" ")[0];

        //remove the £ from amount
        String actualAmount = amountWithPound.replaceAll("[£,]", "");

        assertThat(actualAmount, equalTo(planAmount));
    }

    public void completeSignUpProcess() {
        BasePage.waitUntilElementClickable(signUpPageLocators.nextButton, 30);
        BasePage.clickAfterWait(signUpPageLocators.nextButton);
        BasePage.waitUntilElementClickable(signUpPageLocators.showMeHowButton, 30);
        BasePage.clickAfterWait(signUpPageLocators.showMeHowButton);
    }

    public void clickNextOnEveryPointMatters() {
        BasePage.waitUntilElementClickable(signUpPageLocators.nextButton, 30);
        BasePage.scrollToWebElement(signUpPageLocators.nextButton);
        BasePage.clickAfterWait(signUpPageLocators.nextButton);
    }

    public void verifySeventyPointIncreaseTotalAmount() {
        BasePage.waitUntilElementPresent(signUpPageLocators.seventyPointPersonalLoanAmount, 30);

        //remove the £ from amount display in Seventy Point Personal Amount cell
        String seventyPointPersonalAmount = BasePage.getText(signUpPageLocators.seventyPointPersonalLoanAmount).replaceAll("[£,]", "");

        //convert to int
        int seventyPointPersonalLoan = Integer.parseInt(seventyPointPersonalAmount);

        //remove the £ from amount display in Seventy Point Credit Card Amount cell
        String seventyPointCreditCardAmount = BasePage.getText(signUpPageLocators.seventyPointCreditCardAmount).replaceAll("[£,]", "");

        //convert to int
        int seventyPointCreditCard = Integer.parseInt(seventyPointCreditCardAmount);

        //add above two int values
        int expectedSeventyPointTotalAmount = seventyPointPersonalLoan + seventyPointCreditCard;

        //get the display total in 70 point column and
        int actualSeventyPointTotal = Integer.parseInt(BasePage.getText(signUpPageLocators.seventyPointTotalAmount).replaceAll("[£,]", ""));
        assertThat(actualSeventyPointTotal, equalTo(expectedSeventyPointTotalAmount));
    }

    public void verifyThreeHundredPointIncreaseTotalAmount() {
        BasePage.waitUntilElementPresent(signUpPageLocators.threeHundredPointPersonalLoanAmount, 30);

        //remove the £ from amount display in three hundred Point Personal Amount cell
        String threeHundredPointPersonalAmount = BasePage.getText(signUpPageLocators.threeHundredPointPersonalLoanAmount).replaceAll("[£,]", "");

        //convert to int
        int threeHundredPointPersonalLoan = Integer.parseInt(threeHundredPointPersonalAmount);

        //remove the £ from amount display in three hundred Point Credit Card Amount cell
        String threeHundredCreditCardAmount = BasePage.getText(signUpPageLocators.threeHundredPointCreditCardAmount).replaceAll("[£,]", "");

        //convert to int
        int threeHundredPointCreditCard = Integer.parseInt(threeHundredCreditCardAmount);

        //add above two int values
        int expectedThreeHundredPointTotalAmount = threeHundredPointPersonalLoan + threeHundredPointCreditCard;

        //get the display total in 300 point column and
        int actualSeventyPointTotal = Integer.parseInt(BasePage.getText(signUpPageLocators.threeHundredPointTotalAmount).replaceAll("[£,]", ""));
        assertThat(actualSeventyPointTotal, equalTo(expectedThreeHundredPointTotalAmount));
    }

    public void enterPaymentDetailsAndCompleteSignUpProcess() {
        BasePage.waitUntilElementPresent(signUpPageLocators.debitCardNumber, 30);
        BasePage.typeWithStringBuilder(signUpPageLocators.debitCardNumber, "4111111111111111");
        BasePage.typeWithStringBuilder(signUpPageLocators.debitCardHolderName, (firstName + " " + lastName));
        BasePage.typeWithStringBuilder(signUpPageLocators.debitCardHolderName, "12/24");
        BasePage.typeWithStringBuilder(signUpPageLocators.cardVerificationCode, "123");
        BasePage.clickWithJavaScript(signUpPageLocators.finishSetupButton);
    }

    public void enterEmptyValuesForEveryRequiredField() {
        BasePage.clickAfterWait(signUpPageLocators.firstName);
        BasePage.clickTabKey(signUpPageLocators.firstName);
        BasePage.clickTabKey(signUpPageLocators.lastName);
        BasePage.clickTabKey(signUpPageLocators.email);
        BasePage.scrollToWebElement(signUpPageLocators.mobilePhoneNumber);
        BasePage.clickTabKey(signUpPageLocators.password);
        BasePage.clickAfterWait(signUpPageLocators.mobilePhoneNumber);
        BasePage.clickShitAndTabKeyTogether(signUpPageLocators.mobilePhoneNumber);
        BasePage.clickTabKey(signUpPageLocators.mobilePhoneNumber);
        BasePage.clickTabKey(signUpPageLocators.postcode);
    }

    public void verifyEmptyFieldErrorMessages() {
        //verify first name empty field error message
        BasePage.scrollToWebElement(signUpPageLocators.firstName);
        assertThat(BasePage.getText(signUpPageLocators.firstNameError), is("First name is required"));

        //verify last name empty field error message
        assertThat(BasePage.getText(signUpPageLocators.lastNameErrorMessage), is("Last name is required"));

        //verify email empty field error message
        assertThat(BasePage.getText(signUpPageLocators.emailErrorMessage), is("Email is required"));

        //verify password empty field error message
        assertThat(BasePage.getText(signUpPageLocators.passwordErrorMessage), is("Password is required"));

        //verify dob empty field error message
        BasePage.scrollToWebElement(signUpPageLocators.dateOfBirth);
        assertThat(BasePage.getText(signUpPageLocators.dateOfBirthErrorMessage), is("Date of birth is required"));

        //verify mobile phone number empty field error message
        assertThat(BasePage.getText(signUpPageLocators.mobilePhoneNumberErrorMessage), is("Mobile phone number is required"));

        //verify passcode empty field error message
        assertThat(BasePage.getText(signUpPageLocators.postcodeErrorMessage), is("Postcode is required"));
    }

    public void refreshSignupPage() {
        BasePage.refreshPage();
    }

    public void enterInvalidEmailAddress() {
        BasePage.waitUntilElementPresent(signUpPageLocators.email, 60);
        BasePage.typeWithStringBuilder(signUpPageLocators.email, "mail#dom.com");
        BasePage.clickTabKey(signUpPageLocators.email);
    }

    public void verifyErrorMessageDisplayForInvalidEmailAddress() {
        assertThat(BasePage.getText(signUpPageLocators.emailErrorMessage), is("Enter valid Email"));
    }

    public void enterInvalidPassword() {
        BasePage.waitUntilElementPresent(signUpPageLocators.password, 60);
        BasePage.typeWithStringBuilder(signUpPageLocators.password, passwordWithLessThanTwelveCharacters);
        BasePage.clickTabKey(signUpPageLocators.password);
    }

    public void verifyPasswordRequiredLengthErrorMessage() {
        assertThat(BasePage.getText(signUpPageLocators.passwordErrorMessage), is("Password must be at least 12 characters"));
    }

    public void passwordWithoutSpecialCharacters() {
        BasePage.waitUntilElementPresent(signUpPageLocators.password, 60);
        BasePage.clearTexts(signUpPageLocators.password);
        BasePage.typeWithStringBuilder(signUpPageLocators.password, passwordWithoutSpecialCharacters);
        BasePage.clickTabKey(signUpPageLocators.password);
    }

    public void verifyPasswordWithoutSpecialCharacterErrorMessage() {
        assertThat(BasePage.getText(signUpPageLocators.passwordErrorMessage), is("Password must contain at least one special character, e.g. @$!%*#?&"));
    }
}
