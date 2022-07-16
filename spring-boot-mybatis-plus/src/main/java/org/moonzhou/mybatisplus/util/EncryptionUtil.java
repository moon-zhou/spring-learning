package org.moonzhou.mybatisplus.util;

import org.moonzhou.mybatisplus.exception.AesEncryptException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 加密工具类
 *
 * AES: https://www.cnblogs.com/better-farther-world2099/p/13293291.html
 *
 * @author moon zhou
 */
public class EncryptionUtil {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 算法名称/加密模式/数据填充方式
     */
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * AES 加密
     *
     * @param content    明文
     * @param encryptKey 对称秘钥：16位字符，128bit，默认
     * @return 密文
     */
    public static String aesEncrypt(String content, String encryptKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), KEY_ALGORITHM));
            byte[] decrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));

            // 采用base64算法进行转码,避免出现中文乱码
            return Base64.getEncoder().encodeToString(decrypted);
        } catch (Exception e) {
            throw new AesEncryptException(e);
        }
    }

    /**
     * AES 解密
     *
     * @param encryptStr 密文
     * @param decryptKey 对称秘钥：16位字符，128bit，默认
     * @return 明文
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) {

        try {

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), KEY_ALGORITHM));

            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.getDecoder().decode(encryptStr);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);

            return new String(decryptBytes);
        } catch (Exception e) {
            throw new AesEncryptException(e);
        }
    }
}
