package com.tangxs.bilibili.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
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

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDBOWLdVBTQs0UN+lqKg2YqRvSkcIvAdYJiO1B0JYgDLvt5cPvLcXNgQDXdQ8qJzOMuroIlPZjs9VHaQVHNXMG+7pmEJBi49NBx0KselzRGe4gnvsCr2wgo3kFF9jZ7buG6DmqwSzeKXayyCDBF3vnbkveD+7LuQfB4mzDCmTyVQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIME5Yt1UFNCzRQ36WoqDZipG9KRwi8B1gmI7UHQliAMu+3lw+8txc2BANd1DyonM4y6ugiU9mOz1UdpBUc1cwb7umYQkGLj00HHQqx6XNEZ7iCe+wKvbCCjeQUX2Nntu4boOarBLN4pdrLIIMEXe+duS94P7su5B8HibMMKZPJVAgMBAAECgYBOf0OMD2hlyhMWtvz452MoAMGzojKmeUIkik4j7XXUk+yQduWySklFe7HmZLMGPWBsQ3+sHpuUVq1E0I4iAMr+G1DRqb+iOAC60ttlcnh+9uJ6deqPPMd2r08hmsfe5N4iTuo+DODuqWWyEuzPU2IZybbLLCGqiasXbnmVpOmcjQJBAMF6as+b/9HWy3HyFefC43cbALfvbtakx9gHvSV2VepvYQfpB+3AANxWtRANTnI1OSaenxbQ5SD1sFOu5uSaSuMCQQCtW4Vv7Yju4JxcBAYvnjcoSDslbTKQCF9Q5BhmAqnmseUTWtv789PMvcERW9/qjX9sYs27LgNqd4xzv+MHf7tnAkBeve9n0mAnBSOCnKUVZF22iUaEy35fJTmnE8MPgS75Kak+EmKLjaXeP6dSQPHVAzMFMk99ALXyudyqXc86ykM5AkBK+vzaDoldmLTIb80lJ29/8s3yQCrQdW6UlDOhW6GU8Hi+SFxBu5JuSdyJ340X7B+jB5EoVpyPnimXnvVZNvwdAkAr87+HzKAUD0hSCtXSNxVbaCX8XfrMYfMAu71HTdFLXK0F5Kc+H+Z8ZzY4TkMKQBIFn3yOuMOs2kUGbFttJARf";

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
        rsa = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
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
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2,privateKey);
        byte[] bytes = Base64.decodeBase64(encrypt.getBytes(StandardCharsets.UTF_8));
        String decryptKey = new String(cipher.doFinal(bytes));
        return decryptKey;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("12345678");
        System.out.println(encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println(decrypt);
    }
}
