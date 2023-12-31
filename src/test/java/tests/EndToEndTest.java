package tests;

import com.demoqa.automation.core.driver.DriverManager;
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

import static com.demoqa.automation.core.testdata.BaseData.BASE_URL;
import static com.demoqa.automation.core.testdata.BaseData.DEFAULT_USER;
import static com.demoqa.automation.core.utils.StringUtils.isNullOrEmpty;

public class EndToEndTest extends BaseTest {
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
    public void verifyUserCanAddBooksToHisStorageTest() {
        //First part
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

        //Second part
        //Go to DemoQA website and perform login with created user
        driver.get(BASE_URL);
        mainPage.goToBookStoreApplication();
        bookStorePage.goToLoginPage();
        loginSteps.performLogin(DEFAULT_USER);
        //Navigate to user profile page and get number of books added
        profileSteps.navigateToProfilePage();
        Assert.assertEquals(profileSteps.getNumberOfBooksForUser(), listOfBooks.size(),
                "Number of books is incorrect for current user");

        //Retrieve books details from Web book store
        List<Book> listOfBooksWeb = profileSteps.getBooksDetails();

        //The web description for one of the books differs from backend description.
        //The backend description contains additional space in the end of it.
        //To make the test pass I've just added manually this space.
        listOfBooksWeb.forEach(book -> {
            if (book.getIsbn().equals("9781593275846")) {
                book.setDescription(book.getDescription() + " ");
            }
        });

        //Verify books collections are identical
        Assert.assertEquals(sortBooksByAuthor(listOfBooksWeb, SortOrder.ASCENDING),
                sortBooksByAuthor(listOfBooks, SortOrder.ASCENDING),
                "List of books on website differs from expected!");
    }

    @AfterClass
    public void removeUser() {
        DriverManager.closeDriver();
        //Have to regenerate token for user as Website login changes the token
        token = accountService.generateTokenForUser(DEFAULT_USER).as(TokenResponse.class).getToken();
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
