package com.demoqa.automation.business.ui.pages;

import com.demoqa.automation.business.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@class='card mt-4 top-card' and .//h5[text()='Book Store Application']]")
    public WebElement bookStoreApplicationElement;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void goToBookStoreApplication() {
        bookStoreApplicationElement.click();
    }
}
