package com.tangxs.bilibili.exception;

import com.tangxs.bilibili.constant.enums.ExceptionCode;
import lombok.Data;

/**
 * @Author tangxs
 * @Description 自定义全局异常类
 * @Date 2023/10/2 17:03
 **/
@Data
public class GlobalException extends RuntimeException{
    private int code;

    public GlobalException(int code,String name){
        super(name);
        this.code=code;
    }

    public GlobalException(String name){
        super(name);
        this.code = ExceptionCode.SERVER_ERROR.getCode();
    }

    public GlobalException(ExceptionCode exceptionCode){
        super(exceptionCode.getMsg());
        this.code = exceptionCode.getCode();
    }

}
