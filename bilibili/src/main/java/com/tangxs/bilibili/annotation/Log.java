package com.tangxs.bilibili.annotation;

import com.tangxs.bilibili.constant.enums.BusinessType;

import java.lang.annotation.*;

/**
 * @Author tangxs
 * @Description  自定义操作日志注解
 * @Date 2023/10/1 21:32
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Documented
public @interface Log {

    /**
     * 模块
     **/
    public String title() default "";


    /**
     * 操作类型
     **/
    public BusinessType businessType() default BusinessType.OTHER;


    /**
     * 操作人员类别
     **/
    public String operatorType() default "other";


    /**
     * 是否保存请求参数
     */
    public boolean isSaveRequestData() default true;

}
