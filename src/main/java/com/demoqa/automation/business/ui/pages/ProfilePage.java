package com.demoqa.automation.business.ui.pages;

import com.demoqa.automation.core.enums.Rows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends SideBarPage {

    @FindBy(xpath = "//div[@role='row' and ./div/img]")
    public List<WebElement> listOfUserBookElements;
    @FindBy(xpath = "//select[@aria-label='rows per page']")
    public WebElement bookRowsMenu;
    public static final String SEARCH_INPUT_XPATH = "//input[@placeholder='Type to search']";
    private static final String NUMBER_OF_ROWS_XPATH = "//option[@value='%s']";


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void changeNumberOfRows(Rows numberOfRows) {
        bookRowsMenu.click();
        bookRowsMenu.findElement(By.xpath(String.format(NUMBER_OF_ROWS_XPATH, numberOfRows.getValue()))).click();
    }

    public void goToBookDetails(WebElement book) {
        book.findElement(By.xpath(".//a/parent::span")).click();
    }
}
