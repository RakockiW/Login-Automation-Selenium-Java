package com.epam.training.wiktor_rakocki.driver.strategy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeStrategy implements DriverStrategy {
    public WebDriver setUpDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
