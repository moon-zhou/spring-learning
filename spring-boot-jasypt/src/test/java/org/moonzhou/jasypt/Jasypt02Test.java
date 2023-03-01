package org.moonzhou.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Jasypt02Test {

    @Autowired
    private StringEncryptor stringEncryptor;

    private static final String SPLIT_LINE = "---------";
    private static final List<String> ORIGINAL_DATA = new ArrayList<>();
    private static final List<String> ENCRYPT_DATA = new ArrayList<>();

    {
        ORIGINAL_DATA.add("aaa");

        // iam client secret
        ORIGINAL_DATA.add(SPLIT_LINE);
        ORIGINAL_DATA.add("bbb");

    }



    @Order(1)
    @Test
    public void encrypt() {
        List<String> encryptData = ORIGINAL_DATA.stream().map(data -> data.equals(SPLIT_LINE) ? data : stringEncryptor.encrypt(data)).collect(Collectors.toList());

        // print in console, with config format(ENC)
        System.out.println("encrypt data ----------------");
        encryptData.forEach(Jasypt02Test::display);
        ENCRYPT_DATA.clear();

        // store in run time jvm cache, used by following method(decryption)
        ENCRYPT_DATA.addAll(encryptData);
        System.out.println();
    }

    @Order(2)
    @Test
    public void decrypt() {
        // decrypt data, split line string not handle
        List<String> decryptDataList = ENCRYPT_DATA.stream().map(encryptData -> {
            if (encryptData.equals(SPLIT_LINE)) {
                return encryptData;
            }
            String decryptData = stringEncryptor.decrypt(encryptData);
            System.out.println(decryptData);
            return decryptData;
        }).collect(Collectors.toList());

        // assert: decrypt data from encrypt data same with the original data
        for (int i = 0; i < decryptDataList.size(); i++) {
            Assertions.assertEquals(decryptDataList.get(i), ORIGINAL_DATA.get(i));
        }

    }

    public static void display(String encryptData){
        if (encryptData.equals(SPLIT_LINE)) {
            System.out.println(encryptData);
        } else {
            System.out.println("ENC(" + encryptData + ")");
        }
    }
}
