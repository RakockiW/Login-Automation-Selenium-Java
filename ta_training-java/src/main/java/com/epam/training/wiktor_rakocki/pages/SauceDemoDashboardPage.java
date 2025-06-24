package com.epam.training.wiktor_rakocki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceDemoDashboardPage {

    private WebDriver driver;

    //Locators
    private By titleBox = By.xpath("//div[@class='header_label']/div[@class='app_logo']");

    public SauceDemoDashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(titleBox).getText();
    }
}
