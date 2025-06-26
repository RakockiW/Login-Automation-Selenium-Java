package com.epam.training.wiktor_rakocki.pages;

import com.epam.training.wiktor_rakocki.utils.Slf4jLoggerAdapter;
import com.epam.training.wiktor_rakocki.utils.TestLogger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SauceDemoHomePage
{
    private WebDriver driver;
    private final TestLogger logger = new Slf4jLoggerAdapter(SauceDemoHomePage.class);

    //Locators
    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3")
    private WebElement messageBox;

    public SauceDemoHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
        logger.info("Entered username: " + username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
        logger.info("Entered password: " + password);
    }

    public void clearUsername() {
        usernameInput.sendKeys(Keys.CONTROL + "a");
        usernameInput.sendKeys(Keys.DELETE);
        logger.info("Cleared username");
    }

    public void clearPassword() {
        passwordInput.sendKeys(Keys.CONTROL + "a");
        passwordInput.sendKeys(Keys.DELETE);
        logger.info("Cleared password");
    }

    public SauceDemoDashboardPage clickLoginButton() {
        loginButton.click();
        return new SauceDemoDashboardPage(driver);
    }

    public String getMessageBoxText() {
        String text = messageBox.getText();
        logger.info("Message box text is: " + text);
        return text;
    }

}
