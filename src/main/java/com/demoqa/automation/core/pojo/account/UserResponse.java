package com.demoqa.automation.core.pojo.account;

import com.demoqa.automation.core.pojo.book.Books;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String userId;
    String userName;
    Books books;
}
