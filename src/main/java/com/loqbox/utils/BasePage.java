package com.loqbox.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BasePage {
    private static BasePage basePage;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final Logger logger = LogManager.getFormatterLogger();
    static Properties prop;


    private BasePage() {
        String browser = getProperty("BROWSER");
        logger.info("Open browser %s",  browser);

        if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            WebDriverManager.chromedriver().clearResolutionCache().setup();
//            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        } else {
            logger.info("Browser name %s is invalid.", browser);
        }
    }

    public static String getProperty(String key) {
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\project.properties";
        try (FileInputStream fs = new FileInputStream(path)) {
            prop = new Properties();
            prop.load(fs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static void navigate(String urlKey) {
        String url = getProperty(urlKey);
        logger.info("Navigating to %s", url);
        driver.get(url);
    }

    public static void waitUntilElementPresent(WebElement element, int timeOutSeconds) {
        // present and visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void clickAfterWait(WebElement element) {
        waitUntilElementPresent(element, 60);
        logger.info("---Clicked on the web element %s", element);
        element.click();
    }

    public static boolean isElementPresent(WebElement ele, int timeOutSeconds) {
        try {
            // present and visible
            waitUntilElementPresent(ele, timeOutSeconds);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static void scrollToWebElement(WebElement ele) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public static void uploadFileUsingSendKeys(WebElement ele, String filePath) {
        scrollToWebElement(ele);
        String fullFilePathToBeUploaded = System.getProperty("user.dir") + "\\src\\test" + filePath;
        ele.sendKeys(fullFilePathToBeUploaded);
    }

    public static void selectByVisibleText(WebElement we, String text) {
        Select sel = new Select(we);
        sel.selectByVisibleText(text);
    }


    public static void moveMousePointerUsingActionClass(WebElement ele) {
        new Actions(driver)
                .moveToElement(ele)
                .perform();
    }


    public static void clickWithJavaScript(WebElement ele) {
        waitUntilElementPresent(ele, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", ele);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setUpDriver() {
        if (basePage == null) {
            basePage = new BasePage();
        }
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        basePage = null;
    }

    public static List<WebElement> findListOfWebElements(String xpath) {
        List<WebElement> els = null;
        try {
            By locator = By.xpath(xpath);
            els = getDriver().findElements(locator);

        } catch (Exception ex) {
            logger.error("Object not found %s", xpath);
        }
        return els;
    }

    public static void typeWithStringBuilder(WebElement element, String data) {
        logger.info("Type using StringBuilder in %s", element);
        StringBuilder sb = new StringBuilder(data);
        element.sendKeys(sb);
    }

    public static String getText(WebElement element){
        logger.info("Get text in %s", element);
        return element.getText();
    }

    public static String getAttributeValue(WebElement element, String attribute){
        logger.info("Get text in %s and %s", element, attribute);
        return element.getAttribute(attribute);
    }

    public static void clickTabKey(WebElement element) {
        logger.info("Click tab key in %s", element);
        element.sendKeys(Keys.TAB);
    }

    public static void waitUntilElementClickable(WebElement element, int timeOutSeconds) {
        // present and clickable
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void selectFirstOption(String xpath) {
        Select sel = new Select(driver.findElement(By.xpath(xpath)));
        sel.selectByValue("0");
    }

    public static void clickShitAndTabKeyTogether(WebElement element) {
        logger.info("Click shift + tab key in %s", element);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.SHIFT)
                        .sendKeys(Keys.TAB)
                                .keyUp(Keys.SHIFT)
                                        .build()
                                                .perform();
    }

    public static void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
    }

    public static void waitUntilElementVisible(By by, int timeOutSeconds) {
        // present and visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
