package com.demoqa.automation.business.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookStorePage extends SideBarPage {

    @FindBy(css = "#login")
    public WebElement loginButton;
    public static final String USER_NAME_TEXT_XPATH = "//div/label[@id='userName-value']";

    public BookStorePage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() {
        loginButton.click();
    }
}
