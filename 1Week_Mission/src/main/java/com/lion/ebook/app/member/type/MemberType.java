package com.lion.ebook.app.member.type;

import lombok.Getter;
import lombok.val;

@Getter
public enum MemberType {
    GENERAL("3"),
    ADMIN("7"),
    AUTHOR("4"),
    ;

    private String value;
    MemberType(String value) {
        this.value = value;
    }
}
