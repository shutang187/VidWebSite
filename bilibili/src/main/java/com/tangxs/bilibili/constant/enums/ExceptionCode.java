package com.tangxs.bilibili.constant.enums;

import lombok.Data;

/**
 * @Author tangxs
 * @Description 异常类型枚举
 * @Date 2023/10/2 17:12
 **/

public enum ExceptionCode {
    SERVER_ERROR(500,"服务器内部错误"),
    SYSTEM_IS_BUSY(500,"系统繁忙"),
    TOKEN_VERIFY_ERROR(500,"token验证失败");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ExceptionCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
