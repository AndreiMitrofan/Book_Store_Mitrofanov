package com.demoqa.automation.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final long WAIT_SECONDS = 15;

    private DriverManager(){}

    public static WebDriver getDriverInstance() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SECONDS));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_SECONDS));
            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
        driver.remove();
    }
}
