package com.demoqa.automation.business.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends SideBarPage {

    @FindBy(xpath = "//h2[contains(text(),'Welcome')]")
    public WebElement welcomeText;
    @FindBy(xpath = "//input[@id='userName']")
    public WebElement userNameTextBox;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordTextBox;
    @FindBy(xpath = "//button[@id='login']")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUserName(String userName) {
        userNameTextBox.sendKeys(userName);
    }

    public void setPassword(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public boolean isPageLoaded() {
        return welcomeText.isDisplayed();
    }
}
