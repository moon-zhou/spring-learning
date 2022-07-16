package org.moonzhou.mybatisplus.util;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionUtilTest {

    @Test
    public void testAesEncryption() throws Exception {
        /*KeyGenerator kg = KeyGenerator.getInstance("AES");
        // 下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
        kg.init(128);
        SecretKey sk = kg.generateKey();*/
        String key = "1234567890abdcef";

        String content = "test";
        String encryptContent = EncryptionUtil.aesEncrypt(content, key);
        String decryptContent = EncryptionUtil.aesDecrypt(encryptContent, key);

        Assert.assertEquals(content, decryptContent);
    }
}