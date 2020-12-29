package com.lzy.bishe.common;

public enum UserStatusCode implements BaseCode{
    NORMAL(0,"正常"),
    NO_TALK(1,"被禁言"),
    ;


    private Integer code;
    private String message;

    UserStatusCode(Integer code, String message) {
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
