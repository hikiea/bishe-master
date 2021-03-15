package com.lzy.bishe.common;

public enum TieStatusCode implements BaseCode{
    NORMAL(0,"普通贴"),
    ANNOUNCEMENT (1,"公告贴"),

    ;
    private Integer code;
    private String message;

    TieStatusCode(Integer code, String message) {
        this.message=message;
        this.code=code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
