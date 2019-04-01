package com.example.configserver.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wenhuaping on 2019/3/29.
 * @author: wenhuaping
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTests {

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void test() {
        System.out.println(stringEncryptor.encrypt("aa12345678"));
    }

}
