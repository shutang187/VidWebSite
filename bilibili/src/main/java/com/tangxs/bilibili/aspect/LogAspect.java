package com.tangxs.bilibili.aspect;

import com.tangxs.bilibili.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author tangxs
 * @Description 操作日志处理切面
 * @Date 2023/10/1 22:14
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * @Author tangxs
     * @Description 切入点
     * @Date 2023/10/1 22:35
     **/
    @Pointcut("@annotation(com.tangxs.bilibili.annotation.Log)")
    public void logPointCut(){
    }


    /**
     * @Author tangxs
     * @Description 处理请求之后执行
     * @Date 2023/10/1 22:45
     * @Param joinPoint 切入点
     **/
    @AfterReturning(pointcut = "logPointCut()",returning = "res")
    public void doAfterReturning(JoinPoint joinPoint,Object res){
        handleLog(joinPoint,null,res);
    }

    private void handleLog(final JoinPoint joinPoint, final Exception e, Object res) {
        Log logAnnotation = getAnnotationLog(joinPoint);
        if (logAnnotation == null) {
            return;
        }



    }

    /**
     * @Author tangxs
     * @Description 获取Log注解,存在则返回
     * @Date 2023/10/1 23:05
     **/
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature  instanceof MethodSignature){
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method != null){
                return method.getAnnotation(Log.class);
            }
        }

        return null;
    }

}
