package com.lzy.bishe.common;

public enum NotifyStatusCode implements BaseCode{
    NO_READ(0,"未读"),
    READ (1,"已读"),
    ;
    private Integer code;
    private String message;

    NotifyStatusCode(Integer code, String message) {
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
