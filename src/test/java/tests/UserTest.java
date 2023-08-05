package tests;

import com.demoqa.automation.core.pojo.account.responses.TokenResponse;
import com.demoqa.automation.core.pojo.account.responses.UserResponse;
import com.demoqa.automation.core.pojo.book.Book;
import com.demoqa.automation.core.pojo.book.BookIsbn;
import com.demoqa.automation.core.pojo.book.requests.AddBooksRequest;
import com.demoqa.automation.core.pojo.book.responses.BooksResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.SortOrder;
import java.util.List;

import static com.demoqa.automation.core.testdata.BaseData.DEFAULT_USER;
import static com.demoqa.automation.core.utils.StringUtils.isNullOrEmpty;

public class UserTest extends BaseTest {
    private String userId;
    private String token;
    private List<Book> listOfBooks;

    @BeforeClass
    public void createUser_GetBooks() {
        userId = accountService.createUser(DEFAULT_USER).as(UserResponse.class).getUserId();
        token = accountService.generateTokenForUser(DEFAULT_USER).as(TokenResponse.class).getToken();
        listOfBooks = bookService.getBooks().as(BooksResponse.class).getBooks();
    }

    @Test
    public void verifyUserCanAddBooksTest() {
        //Validate integrity of books information
        validateBooksResponse(listOfBooks);
        //Compose request for adding books to user
        List<String> listIsbns = listOfBooks.stream().map(Book::getIsbn).toList();
        List<BookIsbn> listBookIsbns = listIsbns.stream().map(BookIsbn::new).toList();
        AddBooksRequest booksToAdd = new AddBooksRequest(userId, listBookIsbns);
        //Add books to user
        bookService.addBooks(booksToAdd, token);
        UserResponse userResponse = accountService.getUser(userId, token).as(UserResponse.class);
        //Verify list of books for user sorted by Author
        Assert.assertEquals(sortBooksByAuthor(userResponse.getBooks(), SortOrder.ASCENDING),
                sortBooksByAuthor(listOfBooks, SortOrder.ASCENDING),
                String.format("Collection of books for user '%s' differs from expected!", userId));
        Assert.assertEquals(sortBooksByPublisher(userResponse.getBooks(), SortOrder.DESCENDING),
                sortBooksByPublisher(listOfBooks, SortOrder.DESCENDING),
                String.format("Collection of books for user '%s' differs from expected!", userId));
    }

    @AfterClass
    public void removeUser() {
        accountService.deleteUser(userId, token);
    }

    private void validateBooksResponse(List<Book> listOfBooks) {
        SoftAssert softAssert = new SoftAssert();
        listOfBooks.forEach( book -> {
            softAssert.assertFalse(isNullOrEmpty(book.getIsbn()),
                    String.format("ISBN of the book '%s' is not valid!", book.getTitle()));
            softAssert.assertFalse(isNullOrEmpty(book.getTitle()),
                    String.format("Title of the book '%s' is not valid!", book.getTitle()));
            softAssert.assertFalse(isNullOrEmpty(book.getSubTitle()),
                    String.format("Sub title of the book '%s' is not valid!", book.getTitle()));
            softAssert.assertFalse(isNullOrEmpty(book.getAuthor()),
                    String.format("Author of the book '%s' is not valid!", book.getTitle()));
            //TODO: Make sure date is in correct format
            softAssert.assertFalse(isNullOrEmpty(book.getPublishDate()),
                    String.format("ISBN of the book with ISBN '%s' is not valid!", book.getIsbn()));
            softAssert.assertFalse(isNullOrEmpty(book.getPublisher()),
                    String.format("Publisher of the book '%s' is not valid!", book.getTitle()));
            softAssert.assertTrue(book.getPages() > 0,
                    String.format("Number of pages is not valid for the book '%s'", book.getTitle()));
            softAssert.assertFalse(isNullOrEmpty(book.getDescription()),
                    String.format("Description of the book '%s' is not valid!", book.getTitle()));
            softAssert.assertFalse(isNullOrEmpty(book.getWebsite()),
                    String.format("Website of the book '%s' is not valid!", book.getTitle()));
        });
        softAssert.assertAll();
    }
}
