package org.moonzhou.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JasyptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    private static final String ORIGIN_DATA = "a handsome boy";
    private static String encryptData;

    @Order(1)
    @Test
    public void encrypt() {
        encryptData = stringEncryptor.encrypt(ORIGIN_DATA);
        System.out.println("encrypt data ----------------");
        System.out.println(encryptData);
    }

    @Order(2)
    @Test
    public void decrypt() {
        String decryptData = stringEncryptor.decrypt(encryptData);

        System.out.println();
        System.out.println("decrypt data ----------------");
        System.out.println(decryptData);

        Assertions.assertTrue(ORIGIN_DATA.equals(decryptData));
    }
}
