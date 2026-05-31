package org.moonzhou.mybatisplusadvance;

import com.baomidou.mybatisplus.core.toolkit.AES;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringBootMyBatisPlusAdvanceApplicationTest {

    /**
     * 测试mybatis aes加密
     * 在应用中使用时需要注意拼接前缀 mpw:
     * 注意密钥与密文的隔离：密文在代码（或jar包）中，密钥在启动命令中或者系统环境变量中
     */
    @Test
    public void testMyBatisEncrypt() {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();

        // 随机密钥加密
        String data = "hello world";
        String encryptData = AES.encrypt(data, randomKey);
        String decryptData = AES.decrypt(encryptData, randomKey);

        log.info("key: {}, data original: {}, encryptData: {}, decryptData: {}.", randomKey, data, encryptData, decryptData);

        // 断言
        Assertions.assertNotNull(encryptData);
        Assertions.assertNotNull(decryptData);
        Assertions.assertEquals(data, decryptData);
    }
}