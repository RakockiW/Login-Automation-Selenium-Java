package com.epam.training.wiktor_rakocki.steps;

import com.epam.training.wiktor_rakocki.driver.DriverSingleton;
import com.epam.training.wiktor_rakocki.pages.SauceDemoDashboardPage;
import com.epam.training.wiktor_rakocki.pages.SauceDemoHomePage;
import com.epam.training.wiktor_rakocki.utils.Slf4jLoggerAdapter;
import com.epam.training.wiktor_rakocki.utils.TestLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {

    private WebDriver driver;
    private SauceDemoHomePage loginPage;
    private SauceDemoDashboardPage dashboardPage;
    private static final TestLogger logger = new Slf4jLoggerAdapter(LoginSteps.class);

    @Before
    public void setUp() {
        logger.info("Setting up WebDriver and navigating to the login page");
        driver = DriverSingleton.getDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new SauceDemoHomePage(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario FAILED: " + scenario.getName());

            try {
                File screenCapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotName = scenario.getName().replaceAll("\\s", "_") + "_" + getCurrentTimeAsString() + ".png";
                FileUtils.copyFile(screenCapture, new File(
                        ".//target/screenshots/" +
                                screenshotName + ".png"));
                logger.error("Screenshot saved to: " + screenCapture.getAbsolutePath());
            } catch (Exception e) {
                logger.error("Failed to save screenshot: " + e);
            }
        } else {
            logger.info("Scenario PASSED: " + scenario.getName());
        }
        logger.info("Tearing down WebDriver after Cucumber scenario.");
        DriverSingleton.closeDriver();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        assertThat("Not on the login page", driver.getCurrentUrl(), equalTo("https://www.saucedemo.com/"));
    }

    @When("I enter {string} into the username field")
    public void iEnterIntoTheUsernameField(String username) {
        loginPage.enterUsername(username);
    }

    @And("I enter {string} into the password field")
    public void iEnterIntoThePasswordField(String password) {
        loginPage.enterPassword(password);
    }

    @And("I clear the username field")
    public void iClearTheUsernameField() {
        loginPage.clearUsername();
    }

    @And("I clear the password field")
    public void iClearThePasswordField() {
        loginPage.clearPassword();
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        dashboardPage = loginPage.clickLoginButton();
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedErrorMessage) {
        String actualText = loginPage.getMessageBoxText();
        assertThat("Error message does not match expected", actualText, containsString(expectedErrorMessage));
    }

    @Then("I should see the title {string} on the dashboard")
    public void iShouldSeeTheTitleOnTheDashboard(String expectedTitle) {
        String actualTitle = dashboardPage.getTitle();
        assertThat("Dashboard title does not match expected", actualTitle, equalTo(expectedTitle));
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
