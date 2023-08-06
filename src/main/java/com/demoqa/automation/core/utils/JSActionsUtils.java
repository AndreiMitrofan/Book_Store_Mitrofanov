package com.demoqa.automation.core.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.demoqa.automation.core.driver.DriverManager.getDriverInstance;

public class JSActionsUtils {

    private static final JavascriptExecutor executor = (JavascriptExecutor) getDriverInstance();

    public static void scrollElementIntoView(WebElement element) {
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static boolean waitUntilPageLoadedWithJS() {
        return executor.executeScript("return document.readyState").toString().equals("complete");
    }
}
