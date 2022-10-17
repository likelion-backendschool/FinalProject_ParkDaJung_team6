package com.lion.ebook.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultData<T> {
    private String code;
    private String msg;
    private T data;

    public ResultData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
