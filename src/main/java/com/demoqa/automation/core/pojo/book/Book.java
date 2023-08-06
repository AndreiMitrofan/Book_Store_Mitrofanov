package com.demoqa.automation.core.pojo.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    String isbn;
    String title;
    String subTitle;
    String author;
    @JsonIgnore
    String publish_date;
    String publisher;
    Integer pages;
    String description;
    String website;
}
