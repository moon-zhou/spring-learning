package org.moonzhou.biz.noif.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.moonzhou.biz.noif.constant.LoginTypeEnum;
import org.moonzhou.biz.noif.service.login.LoginService;
import org.moonzhou.biz.noif.param.SMSLoginParam;
import org.moonzhou.biz.noif.param.UsernamePasswordLoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 单元测试
 * https://www.cnblogs.com/myitnews/p/12330297.html
 */
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private List<LoginService> loginServiceList;

    @Test
    public void testUsernamePasswordLogin() {
        UsernamePasswordLoginParam usernamePasswordLoginParam = new UsernamePasswordLoginParam(LoginTypeEnum.USERNAME_PASSWORD.getValue(),
                "moon", "moon");

        loginServiceList.forEach(loginService -> {
            if (loginService.isThisLogin(usernamePasswordLoginParam)) {
                String login = loginService.login(usernamePasswordLoginParam);
                Assertions.assertEquals("1", login);
            }
        });

    }

    @Test
    public void testSMSLogin() {
        SMSLoginParam smsLoginParam = new SMSLoginParam(LoginTypeEnum.SMS.getValue(),
                "moon", "999999");

        loginServiceList.forEach(loginService -> {
            if (loginService.isThisLogin(smsLoginParam)) {
                String login = loginService.login(smsLoginParam);
                Assertions.assertEquals("1", login);
            }
        });
    }
}