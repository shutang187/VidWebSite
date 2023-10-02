package com.tangxs.bilibili.exception;

import com.tangxs.bilibili.constant.enums.ExceptionCode;
import com.tangxs.bilibili.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author tangxs
 * @Description 全局异常处理器
 * @Date 2023/10/2 17:08
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object exceptionHandler(Exception ex) {
        if (ex instanceof GlobalException) {
            GlobalException serviceException = (GlobalException) ex;
            log.error("|GlobalExceptionHandler|exceptionHandler| biz exception , errorMsg = {}!", ex.getMessage(), ex);
            return ResponseUtil.fail(serviceException);
        }
        log.error("|GlobalExceptionHandler|exceptionHandler| global exception , errorMsg = {}!", ex.getMessage(), ex);
        return ResponseUtil.fail(ExceptionCode.SYSTEM_IS_BUSY);
    }

}
