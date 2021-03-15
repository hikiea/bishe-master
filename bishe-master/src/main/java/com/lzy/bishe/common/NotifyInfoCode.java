package com.lzy.bishe.common;

public enum NotifyInfoCode implements BaseCode{
    WORKER(0,""),
    ADMIN (1,""),
    COMMENT(2,""),
    TIE_DELETE(3,""),
    COMEENT_DELETE(4,"")
    ;
    private Integer code;
    private String message;

    NotifyInfoCode(Integer code, String message) {
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
