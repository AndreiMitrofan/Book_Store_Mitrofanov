package tests;

import com.demoqa.automation.business.api.services.AccountService;
import com.demoqa.automation.business.api.services.BookService;
import com.demoqa.automation.business.ui.pages.BookStorePage;
import com.demoqa.automation.business.ui.pages.MainPage;
import com.demoqa.automation.business.ui.steps.LoginSteps;
import com.demoqa.automation.business.ui.steps.ProfileSteps;
import com.demoqa.automation.core.pojo.book.Book;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;

import static com.demoqa.automation.core.driver.DriverManager.getDriverInstance;

public class BaseTest {

    protected final WebDriver driver = getDriverInstance();
    protected final AccountService accountService = new AccountService();
    protected final BookService bookService = new BookService();
    protected MainPage mainPage = new MainPage(driver);
    protected BookStorePage bookStorePage = new BookStorePage(driver);
    protected LoginSteps loginSteps = new LoginSteps();
    protected ProfileSteps profileSteps = new ProfileSteps();

    protected List<Book> sortBooksByAuthor(List<Book> books, SortOrder order) {
        if (order.equals(SortOrder.ASCENDING)) {
            return books.stream().sorted(Comparator.comparing(Book::getAuthor)).toList();
        }
        return books.stream().sorted(Comparator.comparing(Book::getAuthor).reversed()).toList();
    }

    protected List<Book> sortBooksByPublisher(List<Book> books, SortOrder order) {
        if (order.equals(SortOrder.ASCENDING)) {
            return books.stream().sorted(Comparator.comparing(Book::getPublisher)).toList();
        }
        return books.stream().sorted(Comparator.comparing(Book::getPublisher).reversed()).toList();
    }
}
