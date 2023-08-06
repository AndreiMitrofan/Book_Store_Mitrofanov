package com.demoqa.automation.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Rows {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    TWENTY_FIVE(25),
    FIFTY(50),
    HUNDRED(100);

    final int value;
}
