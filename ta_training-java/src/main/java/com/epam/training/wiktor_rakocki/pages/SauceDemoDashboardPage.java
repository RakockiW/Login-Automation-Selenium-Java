package com.epam.training.wiktor_rakocki.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoDashboardPage {

    private WebDriver driver;

    //Locators
    @FindBy(xpath = "//div[@class='header_label']/div[@class='app_logo']")
    private WebElement titleBox;

    public SauceDemoDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String getTitle() {
        return titleBox.getText();
    }
}
