package com.epam.training.wiktor_rakocki.pages;

import com.epam.training.wiktor_rakocki.utils.Log4j2LoggerAdapter;
import com.epam.training.wiktor_rakocki.utils.TestLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SauceDemoHomePage
{
    private WebDriver driver;
    private final TestLogger logger = new Log4j2LoggerAdapter(LogManager.getLogger(SauceDemoHomePage.class));

    //Locators
    private By usernameInput = By.xpath("//input[@id='user-name']");
    private By passwordInput = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");
    private By messageBox = By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3");

    public SauceDemoHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
        logger.info("Entered username: " + username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        logger.info("Entered password: " + password);
    }

    public void clearUsername() {
        WebElement inputField = driver.findElement(usernameInput);
        inputField.sendKeys(Keys.CONTROL + "a");
        inputField.sendKeys(Keys.DELETE);
        logger.info("Cleared username");
    }

    public void clearPassword() {
        WebElement inputField = driver.findElement(passwordInput);
        inputField.sendKeys(Keys.CONTROL + "a");
        inputField.sendKeys(Keys.DELETE);
        logger.info("Cleared password");
    }

    public SauceDemoDashboardPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new SauceDemoDashboardPage(driver);
    }

    public String getMessageBoxText() {
        String text = driver.findElement(messageBox).getText();
        logger.info("Message box text is: " + text);
        return text;
    }

}
