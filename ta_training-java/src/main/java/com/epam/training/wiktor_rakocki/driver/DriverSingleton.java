package com.epam.training.wiktor_rakocki.driver;

import com.epam.training.wiktor_rakocki.driver.strategy.DriverStrategy;
import com.epam.training.wiktor_rakocki.driver.strategy.DriverStrategyFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

public class DriverSingleton {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            String browserName = System.getProperty("browser", "firefox");

            DriverStrategy strategy = DriverStrategyFactory.getDriverStrategy(browserName);
            WebDriver driver = strategy.setUpDriver();

            driver.manage().window().maximize();
            DRIVER.set(driver);
        }
        return DRIVER.get();
    }

    public static void closeDriver() {
        DRIVER.get().quit();
        DRIVER.remove();
    }
}

