package com.banyuijo.foundation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BooleanStatus {
    YES(-1, "Yes"),
    NO(0, "No");

    private final int code;
    private final String description;

}
