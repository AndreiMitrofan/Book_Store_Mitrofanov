package com.demoqa.automation.core.testdata;

import com.demoqa.automation.core.enums.Rows;
import com.demoqa.automation.core.pojo.account.User;

import static com.demoqa.automation.core.enums.Rows.TEN;
import static com.demoqa.automation.core.utils.PropertyUtils.getProperty;

public class BaseData {
    public static final String BASE_URL = getProperty("base.url");
    public static final String BASE_PATH_ACCOUNT = getProperty("base.path.account");
    public static final String BASE_PATH_BOOK_STORE = getProperty("base.path.book.store");
    public static final User DEFAULT_USER = new User(getProperty("default.user.name"),
            getProperty("default.user.password"));
    public static final Rows NUMBER_OF_ROWS = TEN;
}
