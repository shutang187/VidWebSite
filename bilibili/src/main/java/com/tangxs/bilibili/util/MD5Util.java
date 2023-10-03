package com.tangxs.bilibili.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @Author tangxs
 * @Description md5加密工具类
 * @Date 2023/10/3 18:23
 **/
@Component
public class MD5Util {

    public static String sign(String content, String salt, String charset) {
        content = content + salt;
        return DigestUtils.md5Hex(getContentBytes(content, charset));
    }

    public static boolean verify(String content, String sign, String salt, String charset) {
        content = content + salt;
        String mysign = DigestUtils.md5Hex(getContentBytes(content, charset));
        return mysign.equals(sign);
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (!"".equals(charset)) {
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("MD5签名过程中出现错误,指定的编码集错误");
            }
        } else {
            return content.getBytes();
        }
    }

    public static String getFileMD5(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int byteRead;
        while((byteRead = inputStream.read(buffer))>0){
            baos.write(buffer,0,byteRead);
        }
        inputStream.close();
        String md5Hex = DigestUtils.md5Hex(baos.toByteArray());
        return md5Hex;
    }

}
