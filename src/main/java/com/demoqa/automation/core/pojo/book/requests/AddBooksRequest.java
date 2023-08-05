package com.demoqa.automation.core.pojo.book.requests;

import com.demoqa.automation.core.pojo.book.BookIsbn;
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
public class AddBooksRequest {
    String userId;
    List<BookIsbn> collectionOfIsbns;
}
