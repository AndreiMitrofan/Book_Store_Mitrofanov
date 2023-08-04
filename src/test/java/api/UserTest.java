package api;

import com.demoqa.automation.business.services.api.AccountService;
import com.demoqa.automation.core.pojo.account.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.demoqa.automation.core.testdata.BaseData.DEFAULT_USER;

public class UserTest {
    AccountService accountService = new AccountService();

    @Test
    public void createUserTest() {
        UserResponse response = accountService.createUser(DEFAULT_USER).as(UserResponse.class);
        Assert.assertEquals(response.getUserName(), DEFAULT_USER.getUserName(), "UserName is incorrect");
    }
}
