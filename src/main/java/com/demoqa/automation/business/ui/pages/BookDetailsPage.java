package com.demoqa.automation.business.ui.pages;

import com.demoqa.automation.core.enums.BookAttributes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookDetailsPage extends SideBarPage {

    @FindBy(xpath = "//label[@id='ISBN-label']")
    public WebElement bookISBNText;
    @FindBy(xpath = "//div[@class='profile-wrapper']")
    public WebElement bookDetailsTable;
    @FindBy(xpath = "//button[@id='addNewRecordButton']")
    public WebElement backToBookListButton;
    private static final String BOOK_ATTRIBUTE_VALUE_XPATH =
            "//div[contains(@id,'wrapper') and div/label[text()='%s : ']]/div[@class='col-md-9 col-sm-12']";
    public BookDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getBookAttributeValue(BookAttributes attribute) {
        return bookDetailsTable.findElement(By.xpath(String.format(BOOK_ATTRIBUTE_VALUE_XPATH,
                attribute.getValue()))).getText();
    }

    public void returnToUserBooksList() {
        backToBookListButton.click();
    }

    public boolean isPageLoaded() {
        return bookISBNText.isDisplayed();
    }
}
