package com.demoqa.automation.business.ui.steps;

import com.demoqa.automation.business.ui.BaseSteps;
import com.demoqa.automation.core.enums.BookAttributes;
import com.demoqa.automation.core.pojo.book.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static com.demoqa.automation.business.ui.pages.ProfilePage.SEARCH_INPUT_XPATH;
import static com.demoqa.automation.core.testdata.BaseData.NUMBER_OF_ROWS;
import static com.demoqa.automation.core.utils.JSActionsUtils.scrollElementIntoView;
import static com.demoqa.automation.core.utils.JSActionsUtils.waitUntilPageLoadedWithJS;

public class ProfileSteps extends BaseSteps {

    public void navigateToProfilePage() {
        scrollElementIntoView(sideBarPage.profileTabElement);
        sideBarPage.goToProfilePage();
    }

    public int getNumberOfBooksForUser() {
        scrollElementIntoView(profilePage.bookRowsMenu);
        profilePage.changeNumberOfRows(NUMBER_OF_ROWS);
        return profilePage.listOfUserBookElements.size();
    }

    public List<Book> getBooksDetails() {
        List<Book> books = new ArrayList<>();
        profilePage.listOfUserBookElements.forEach(book -> {
            //Here I have intermittent failures because of (book) stale element not found
            //Even wait until element staleness doesn't help
            //Thread sleep for 1 second solves the problem, but it's not the best solution
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (waitUntilPageLoadedWithJS()) {
                wait.until(ExpectedConditions.visibilityOf(book));
                scrollElementIntoView(book);
                profilePage.goToBookDetails(book);
                if (bookDetailsPage.isPageLoaded()) {
                    books.add(getBookDetails());
                    scrollElementIntoView(bookDetailsPage.backToBookListButton);
                    bookDetailsPage.returnToUserBooksList();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_INPUT_XPATH)));
                }
            }
        });
        return books;
    }

    private Book getBookDetails() {
        Book book = new Book();
        for (BookAttributes attribute: BookAttributes.values()) {
            switch (attribute) {
                case ISBN -> book.setIsbn(bookDetailsPage.getBookAttributeValue(attribute));
                case TITLE -> book.setTitle(bookDetailsPage.getBookAttributeValue(attribute));
                case SUB_TITLE -> book.setSubTitle(bookDetailsPage.getBookAttributeValue(attribute));
                case AUTHOR -> book.setAuthor(bookDetailsPage.getBookAttributeValue(attribute));
                case PUBLISHER -> book.setPublisher(bookDetailsPage.getBookAttributeValue(attribute));
                case TOTAL_PAGES -> book.setPages(Integer.valueOf(bookDetailsPage.getBookAttributeValue(attribute)));
                case DESCRIPTION -> book.setDescription(bookDetailsPage.getBookAttributeValue(attribute));
                case WEBSITE -> book.setWebsite(bookDetailsPage.getBookAttributeValue(attribute));
            }
        }
        return book;
    }
}
