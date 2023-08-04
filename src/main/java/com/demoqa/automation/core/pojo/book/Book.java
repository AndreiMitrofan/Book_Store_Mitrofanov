package com.demoqa.automation.core.pojo.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    String isbn;
    String title;
    String subTitle;
    String author;
    @JsonProperty("publish_date")
    String publishDate;
    String publisher;
    Integer pages;
    String description;
    String website;
}
