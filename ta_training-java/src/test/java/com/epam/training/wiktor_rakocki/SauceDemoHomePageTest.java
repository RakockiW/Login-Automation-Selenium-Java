package com.epam.training.wiktor_rakocki;

import com.epam.training.wiktor_rakocki.driver.DriverSingleton;

import com.epam.training.wiktor_rakocki.pages.SauceDemoDashboardPage;

import com.epam.training.wiktor_rakocki.pages.SauceDemoHomePage;

import com.epam.training.wiktor_rakocki.utils.Log4j2LoggerAdapter;

import utils.LoggingTestWatcher;

import com.epam.training.wiktor_rakocki.utils.TestLogger;

import org.apache.logging.log4j.LogManager;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.parallel.Execution;

import org.junit.jupiter.api.parallel.ExecutionMode;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;

import org.openqa.selenium.WebDriver;



import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.containsString;

import static org.hamcrest.Matchers.equalTo;





/**

 * Unit test for simple App.

 */

@Execution(ExecutionMode.CONCURRENT)

@ExtendWith(LoggingTestWatcher.class)

public class SauceDemoHomePageTest {

    private WebDriver driver;

    private SauceDemoHomePage loginPage;

    private static final TestLogger logger = new Log4j2LoggerAdapter(LogManager.getLogger(SauceDemoHomePageTest.class));



    @BeforeEach

    public void setUp() {

        logger.info("Setting up WebDriver and navigating to login page");

        driver = DriverSingleton.getDriver();

        driver.get("https://www.saucedemo.com/");

        loginPage = new SauceDemoHomePage(driver);

    }



    @Test

    public void testLoginWithEmptyCredentialsShowsUsernameRequiredError() {

        logger.info("Start test: UC-1 - Login with empty credentials");



        loginPage.enterUsername("test");

        loginPage.enterPassword("password");

        loginPage.clearUsername();

        loginPage.clearPassword();

        loginPage.clickLoginButton();



        String actualText = loginPage.getMessageBoxText();

        String expectedFragment = "Username is required";



        logger.debug("Actual message text: {}" + actualText);



        assertThat("Checking whether text contains fragment", actualText, containsString(expectedFragment));



    }



    @Test

    public void testLoginWithEmptyPasswordShowsPasswordRequiredError() {

        logger.info("Start test: UC-2 - Login with empty password");



        loginPage.enterUsername("test");

        loginPage.enterPassword("password");

        loginPage.clearPassword();

        loginPage.clickLoginButton();



        String actualText = loginPage.getMessageBoxText();

        String expectedFragment = "Password is required";



        logger.debug("Actual message text: {}" + actualText);



        assertThat("Checking whether text contains fragment", actualText, containsString(expectedFragment));



    }



    @ParameterizedTest

    @ValueSource(strings = {

            "standard_user",

            "problem_user",

            "locked_out_user",

            "performance_glitch_user",

            "error_user",

            "visual_user"

    })

    public void testLoginWithValidCredentialsRedirectsToDashboard(String username) throws InterruptedException {

        logger.info("Start test: UC-3 - Login with valid credentials");



        loginPage.enterUsername(username);

        loginPage.enterPassword("secret_sauce");



        SauceDemoDashboardPage dashboardPage = loginPage.clickLoginButton();

        String expectedTitle = "Swag Labs";



        logger.debug("Actual title: {}" + dashboardPage.getTitle());



        assertThat("Checking if title is 'Swag Labs'", dashboardPage.getTitle(), equalTo(expectedTitle));



    }



    @AfterEach

    public void stopBrowser() {

        DriverSingleton.closeDriver();

    }



    public WebDriver getDriver() {

        return driver;

    }



}