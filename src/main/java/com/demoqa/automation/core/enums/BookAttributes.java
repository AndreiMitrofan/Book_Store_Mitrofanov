package com.demoqa.automation.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum BookAttributes {
    ISBN("ISBN"),
    TITLE("Title"),
    SUB_TITLE("Sub Title"),
    AUTHOR("Author"),
    PUBLISHER("Publisher"),
    TOTAL_PAGES("Total Pages"),
    DESCRIPTION("Description"),
    WEBSITE("Website");

    final String value;
}
