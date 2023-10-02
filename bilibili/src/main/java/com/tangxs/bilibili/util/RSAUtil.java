package com.tangxs.bilibili.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
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
public class RSAUtil {

    private static final String PUBLIC_KEY = "";
    private static final String PRIVATE_KEY = "";


    public static String getPublicKeyStr(){
        return PUBLIC_KEY;
    }

    public static RSAPublicKey getPublicKey(){
        byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
        RSAPublicKey rsa = null;
        try {
            rsa = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        } catch (Exception e) {
            log.error("获取public key失败");
        }
        return rsa;
    }

    public static RSAPrivateKey getPrivateKey(){
        byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
        RSAPrivateKey rsa = null;
        try {
            rsa = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePublic(new PKCS8EncodedKeySpec(decoded));
        } catch (Exception e) {
            log.error("获取private key失败");
        }
        return rsa;
    }


    public static String encrypt(String source) {
        RSAPublicKey publicKey = getPublicKey();
        String encryptKey = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1,publicKey);
            encryptKey = Base64.encodeBase64String(cipher.doFinal(source.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error("使用公钥加密失败");
        }
        return encryptKey;
    }


    public static String decrypt(String encrypt){
        RSAPrivateKey privateKey = getPrivateKey();
        String decryptKey = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1,privateKey);
            byte[] bytes = Base64.decodeBase64(encrypt.getBytes(StandardCharsets.UTF_8));
            decryptKey = new String(cipher.doFinal(bytes));
        } catch (Exception e) {
            log.error("用私钥解密失败");
        }
        return decryptKey;
    }


}
