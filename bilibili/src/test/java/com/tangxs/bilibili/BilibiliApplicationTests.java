package com.tangxs.bilibili;

import com.tangxs.bilibili.util.MinioUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BilibiliApplicationTests {

    @Autowired
    private MinioUtil minioUtil;
    @Test
    void test() {
        Boolean video = minioUtil.bucketExists("video");
        System.out.println(video);
    }

}
