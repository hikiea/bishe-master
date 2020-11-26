package com.example.demo.myenum.roleEnum;

public enum RoleCode implements IRoleCode {
    OWNERS_COMMITTEE("admin",5),//业委会
    PROPERTY_MANAGEMENT_COMPANY("pmcAdmin",4),//物业
    REGISTERED_USER("user",3),//注册用户
    WORKER("worker",2),//物业工人
    ;

    private String role;
    private Integer code;

    RoleCode(String role, Integer code){
        this.role=role;
        this.code=code;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
