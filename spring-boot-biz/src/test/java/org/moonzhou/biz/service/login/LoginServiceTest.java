package org.moonzhou.biz.service.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private List<LoginService> loginServiceList;

    @Test
    public void testUsernamePasswordLogin() {

    }

    @Test
    public void testSMSLogin() {

    }
}