package com.tangxs.bilibili.util;

import com.tangxs.bilibili.constant.enums.ExceptionCode;
import com.tangxs.bilibili.domain.model.ResponseBean;
import com.tangxs.bilibili.exception.GlobalException;

/**
 * @Author tangxs
 * @Description
 * @Date 2023/10/2 17:34
 **/
public class ResponseUtil {

    public ResponseUtil(){

    }

    public static <T> ResponseBean<T> success(T result) {
        return fail(null, 200, "ok", result);
    }

    public static <T> ResponseBean<T> success(String reqId, T result) {
        return fail(reqId, 200, "ok", result);
    }

    public static <T> ResponseBean<T> success() {
        return fail(null, 200, "ok", null);
    }

    public static <T> ResponseBean<T> fail(ExceptionCode exception) {
        return fail(null, exception.getCode(), exception.getMsg(), null);
    }

    public static <T> ResponseBean<T> fail(String reqId, ExceptionCode exception) {
        return fail(reqId, exception.getCode(), exception.getMsg(), null);
    }

    public static <T> ResponseBean<T> fail(GlobalException exception) {
        return fail(null, exception.getCode(), exception.getMessage(), null);
    }

    public static <T> ResponseBean<T> fail(String reqId, GlobalException exception) {
        return fail(reqId, exception.getCode(), exception.getMessage(), null);
    }


    public static <T> ResponseBean<T> fail(int code, String message, T result) {
        ResponseBean<T> responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(message);
        responseBean.setData(result);
        return responseBean;
    }

    public static <T> ResponseBean<T> fail(String reqId, int code, String message, T result) {
        ResponseBean<T> responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(message);
        responseBean.setData(result);
        responseBean.setReqId(reqId);
        return responseBean;
    }
}
