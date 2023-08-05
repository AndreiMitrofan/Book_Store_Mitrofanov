package tests;

import com.demoqa.automation.business.api.services.AccountService;
import com.demoqa.automation.business.api.services.BookService;
import com.demoqa.automation.core.pojo.book.Book;

import javax.swing.SortOrder;
import java.util.Comparator;
import java.util.List;

public class BaseTest {
    protected final AccountService accountService = new AccountService();
    protected final BookService bookService = new BookService();

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
