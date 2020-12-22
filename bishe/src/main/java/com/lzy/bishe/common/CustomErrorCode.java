package com.lzy.bishe.common;

public enum CustomErrorCode implements ICustomErrorCode{
    UNKNOWN_ERROR(2100,"未知异常"),
    GAME_NOT_FIND(2001,"没有找到这个游戏"),
    NOT_AUTHORITY(2002,"权限不足"),
    USER_NOT_FOUND(2003,"该用户不存在"),
    PASSWORD_WRONG(2004,"密码错误"),
    FILE_NOT_FOUND(2005,"未找到文件"),
    FILE_IS_NULL(2006,"文件为空"),
    CONT_REGISTER_THIS_ROLE(2007,"不能注册这一权限"),
    ;


    private Integer code;
    private String message;

    CustomErrorCode(Integer code, String message) {
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
