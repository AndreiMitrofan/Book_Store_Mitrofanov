package com.demoqa.automation.business.ui.steps;

import com.demoqa.automation.business.ui.BaseSteps;
import com.demoqa.automation.core.pojo.account.User;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.demoqa.automation.business.ui.pages.BookStorePage.USER_NAME_TEXT_XPATH;

public class LoginSteps extends BaseSteps {

    public void performLogin(User user) {
        System.out.println("Performing login");
        if (loginPage.isPageLoaded()) {
            loginPage.setUserName(user.getUserName());
            loginPage.setPassword(user.getPassword());
            loginPage.clickOnLoginButton();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USER_NAME_TEXT_XPATH)));
        } else {
            throw new ElementNotInteractableException("Login page is not loaded!");
        }
    }
}
