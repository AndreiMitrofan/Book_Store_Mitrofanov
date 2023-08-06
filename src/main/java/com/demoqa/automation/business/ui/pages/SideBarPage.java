package com.demoqa.automation.business.ui.pages;

import com.demoqa.automation.business.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideBarPage extends BasePage {

    @FindBy(xpath = "//div[@class='element-group' and .//div[text()='Book Store Application']]/..//li[span[text()='Profile']]")
    public WebElement profileTabElement;

    public SideBarPage(WebDriver driver) {
        super(driver);
    }

    public void goToProfilePage() {
        profileTabElement.click();
    }
}
