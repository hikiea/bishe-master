package com.lzy.bishe.common;

public enum UserPowerCode implements BaseCode{
    ADMIN(0,"系统管理员"),
    USER(1,"用户"),
    WORKER(2,"维修工人"),
    C_ADMIN(3,"小区管理员"),
    ;


    private Integer code;
    private String message;

    UserPowerCode(Integer code, String message) {
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
