package cn.eliadoarias.springdemo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    NOT_FOUND(404, "未找到目标资源"),
    WRONG_USER(2000, "账号不存在"),
    WRONG_PASSWORD(2001, "账号或密码不正确"),
    DUPLICATED_USER(2002, "账号已存在"),
    USERNAME_NOT_A_NUM(2003, "账号名含非数字"),
    WRONG_PASSWORD_LENGTH(2004, "密码长度不在8-16之间"),
    WRONG_USER_TYPE(2005, "用户类型错误"),
    INVALID_PARAMETERS(2009, "参数错误"),
    NO_OP(2020, "权限不足"),
    SERVER_ERROR(2099, "服务器异常");

    private final Integer code;
    private final String message;
}
