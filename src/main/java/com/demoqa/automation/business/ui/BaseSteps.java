package com.demoqa.automation.business.ui;

import com.demoqa.automation.business.ui.pages.BookDetailsPage;
import com.demoqa.automation.business.ui.pages.LoginPage;
import com.demoqa.automation.business.ui.pages.ProfilePage;
import com.demoqa.automation.business.ui.pages.SideBarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.demoqa.automation.core.driver.DriverManager.getDriverInstance;

public class BaseSteps {
    private final WebDriver driver = getDriverInstance();
    private static final Duration TIMEOUT_IN_SECONDS = Duration.ofSeconds(5);
    protected LoginPage loginPage = new LoginPage(driver);
    protected SideBarPage sideBarPage = new SideBarPage(driver);
    protected ProfilePage profilePage = new ProfilePage(driver);
    protected BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
    protected WebDriverWait wait = new WebDriverWait(getDriverInstance(), TIMEOUT_IN_SECONDS);
}
