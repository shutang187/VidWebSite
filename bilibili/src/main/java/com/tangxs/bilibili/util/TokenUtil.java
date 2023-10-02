package com.tangxs.bilibili.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tangxs.bilibili.constant.RedisKeyPrefix;
import com.tangxs.bilibili.constant.enums.ExceptionCode;
import com.tangxs.bilibili.domain.model.LoginUser;
import com.tangxs.bilibili.exception.GlobalException;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author tangxs
 * @Description token工具类
 * @Date 2023/10/2 10:01
 **/
@Slf4j
public class TokenUtil {

    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    public static final String ISSUER = "tangxs";

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private static int expireTime;

    @Autowired
    private RedisCacheUtil redisCache;


    public static String generateToken(String userId){
        Algorithm rsa256 = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MINUTE,expireTime);
        return JWT.create().withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(instance.getTime())
                .sign(rsa256);
    }

    public static Long verifyToken(String token){
        try {
            Algorithm rsa256 = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier jwtVerifier = JWT.require(rsa256).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            String keyId = jwt.getKeyId();
            return Long.valueOf(keyId);
        } catch (JWTVerificationException e) {
            throw new GlobalException(ExceptionCode.TOKEN_VERIFY_ERROR);
        }
    }

    private String getTokenKey(Long userId){
        if (userId == null) {
            return null;
        }
        return RedisKeyPrefix.LOGIN_TOKRN_KEY_PREFIX + String.valueOf(userId);
    }



    public LoginUser getLoginUser(HttpServletRequest request){
        String token = request.getHeader(header);
        Long userId = verifyToken(token);
        if(userId != null && userId > 0){
            LoginUser user = (LoginUser) redisCache.getCacheObject(getTokenKey(userId));
            return user;
        }
        return null;
    }


    public void setLoginUser(LoginUser user){
        if(Objects.nonNull(user)){
            refreshToken(user);
        }
    }

    private void refreshToken(LoginUser user) {
        user.setLoginTime(System.currentTimeMillis());
        user.setExpireTime(user.getLoginTime() + expireTime * MILLIS_MINUTE);
        String tokenKey = getTokenKey(user.getUserId());
        redisCache.setCacheObject(tokenKey,user,expireTime, TimeUnit.MINUTES);
    }

    public void setUserAgent(LoginUser loginUser){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String ip = IpUtil.getIpAddr(request);
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AdressUtil.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

}
