package com.demoqa.automation.core.pojo.account.responses;

import com.demoqa.automation.core.pojo.book.Book;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    @JsonProperty("userID")
    @JsonAlias("userId")
    String userId;
    @JsonProperty("username")
    String userName;
    List<Book> books;
}
