package utils;

import com.epam.training.wiktor_rakocki.SauceDemoHomePageTest;
import com.epam.training.wiktor_rakocki.utils.Log4j2LoggerAdapter;
import com.epam.training.wiktor_rakocki.utils.TestLogger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingTestWatcher implements TestWatcher, AfterTestExecutionCallback {

    private final TestLogger logger = new Log4j2LoggerAdapter(LogManager.getLogger(LoggingTestWatcher.class));

    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("Test FAILED: " + context.getDisplayName() + cause.getLocalizedMessage());
    }

    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            Object instance = context.getRequiredTestInstance();
            saveScreenshot(instance);
        }
    }

    public void testSuccessful(ExtensionContext context) {
        logger.info("Test PASSED: " + context.getDisplayName());
    }

    private void saveScreenshot(Object testInstance) {
        try {
            WebDriver driver = ((SauceDemoHomePageTest) testInstance).getDriver();
            File screenCapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/" +
                    getCurrentTimeAsString() + ".png"));
            logger.error("Screenshot saved to: " + screenCapture.getAbsolutePath());
        } catch (Exception e) {
            logger.error("Failed to save screenshot: " + e);
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
