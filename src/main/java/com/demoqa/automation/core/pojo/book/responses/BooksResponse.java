package com.demoqa.automation.core.pojo.book.responses;

import com.demoqa.automation.core.pojo.book.Book;
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
public class BooksResponse {
    List<Book> books;
}
