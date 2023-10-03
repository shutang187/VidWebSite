package com.tangxs.bilibili.util;

import com.tangxs.bilibili.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author tangxs
 * @Description RSA加密
 * @Date 2023/10/2 10:54
 **/
@Slf4j
@Component
public class RSAUtil {

    private static final String PUBLIC_KEY = "";
    private static final String PRIVATE_KEY = "";


    public static String getPublicKeyStr(){
        return PUBLIC_KEY;
    }

    public static RSAPublicKey getPublicKey() throws Exception{
        byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
        RSAPublicKey rsa = null;
        rsa = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        return rsa;
    }

    public static RSAPrivateKey getPrivateKey() throws Exception{
        byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
        RSAPrivateKey rsa = null;
        rsa = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePublic(new PKCS8EncodedKeySpec(decoded));
        return rsa;
    }


    public static String encrypt(String source) throws Exception{
        RSAPublicKey publicKey = getPublicKey();
        String encryptKey = null;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1,publicKey);
        encryptKey = Base64.encodeBase64String(cipher.doFinal(source.getBytes(StandardCharsets.UTF_8)));
        return encryptKey;
    }


    public static String decrypt(String encrypt) throws Exception {
        RSAPrivateKey privateKey = getPrivateKey();
        String decryptKey = null;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1,privateKey);
        byte[] bytes = Base64.decodeBase64(encrypt.getBytes(StandardCharsets.UTF_8));
        decryptKey = new String(cipher.doFinal(bytes));
        return decryptKey;
    }


}
