package com.tangxs.bilibili.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.tangxs.bilibili.annotation.Log;
import com.tangxs.bilibili.constant.enums.ExceptionCode;
import com.tangxs.bilibili.constant.enums.ResultStatus;
import com.tangxs.bilibili.domain.dao.SysOperLog;
import com.tangxs.bilibili.domain.model.LoginUser;
import com.tangxs.bilibili.exception.GlobalException;
import com.tangxs.bilibili.manager.AsyncManager;
import com.tangxs.bilibili.manager.factory.AsyncFactory;
import com.tangxs.bilibili.util.IpUtil;
import com.tangxs.bilibili.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author tangxs
 * @Description 操作日志处理切面
 * @Date 2023/10/1 22:14
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    private static final int DELAY_TIME = 1;


    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AsyncManager asyncManager;

    /**
     * @Description 切入点
     * @Date 2023/10/1 22:35
     **/
    @Pointcut("@annotation(com.tangxs.bilibili.annotation.Log)")
    public void logPointCut(){
    }


    /**
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
        LoginUser loginUser = null;
        try {
            loginUser = tokenUtil.getLoginUser();
        } catch (Exception ex) {
            throw new GlobalException(ExceptionCode.TOKEN_VERIFY_ERROR);
        }

        SysOperLog sysOperLog = new SysOperLog();
        sysOperLog.setOperIp(IpUtil.getIpAddr(TokenUtil.getRequest()));
        sysOperLog.setJsonResult(JSONUtil.toJsonStr(res));
        sysOperLog.setOperUrl(TokenUtil.getRequest().getRequestURI());
        if(loginUser != null){
            sysOperLog.setOperName(loginUser.getUser().getNick());
        }

        sysOperLog.setStatus(ResultStatus.SUCCESS.ordinal());
        if(e!=null){
            sysOperLog.setStatus(ResultStatus.FAIL.ordinal());
            sysOperLog.setErrorMsg(StrUtil.sub(e.getMessage(),0,2000));
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        sysOperLog.setMethod(className+"."+methodName);
        sysOperLog.setRequestMethod(TokenUtil.getRequest().getMethod());
        getControllerMethodDescription(joinPoint,logAnnotation,sysOperLog);
        asyncManager.execute(AsyncFactory.recordOper(sysOperLog),DELAY_TIME, TimeUnit.SECONDS);
    }

    /**
     * @Description 设置注解中的信息
     * @Date 2023/10/15 9:29
     **/
    private void getControllerMethodDescription(JoinPoint joinPoint, Log logAnnotation, SysOperLog sysOperLog) {
        sysOperLog.setBusinessType(logAnnotation.businessType().ordinal());
        sysOperLog.setTitle(logAnnotation.title());

        if(logAnnotation.isSaveRequestData()){
            setRequestParam(joinPoint,sysOperLog);
        }
    }

    /**
     * @Description 设置请求参数
     * @Date 2023/10/15 9:34
     **/
    private void setRequestParam(JoinPoint joinPoint, SysOperLog sysOperLog) {
        String requestMethod = sysOperLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            sysOperLog.setOperParam(StrUtil.sub(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) TokenUtil.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            sysOperLog.setOperParam(StrUtil.sub(paramsMap.toString(), 0, 2000));
        }
    }


    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (StrUtil.isNotEmpty(paramsArray[i].toString()) && !isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
                return iter.next() instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry entry = (Map.Entry) iter.next();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }


    /**
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
