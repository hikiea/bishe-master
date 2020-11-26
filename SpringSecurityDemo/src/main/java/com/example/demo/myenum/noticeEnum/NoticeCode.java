
package com.example.demo.myenum.noticeEnum;

public enum NoticeCode implements INoticeCode{
    SYSTEM_NOTICE(0,"系统通知"),//各类无法归类的，由系统发出的通知均位于此
    COMMENT_NOTICE(1,"主题新回复"),
    ;

    NoticeCode(Integer code,String type){
        this.code=code;
        this.type=type;
    }

    private Integer code;
    private String type;

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }
}