package com.epam.training.wiktor_rakocki.driver.strategy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverStrategyFactory {
    public static DriverStrategy getDriverStrategy(String browser) {
        switch(browser.toLowerCase()) {
            case "edge":
                return new EdgeStrategy();
            case "firefox":
            default:
                return new FirefoxStrategy();
        }
    }
}
