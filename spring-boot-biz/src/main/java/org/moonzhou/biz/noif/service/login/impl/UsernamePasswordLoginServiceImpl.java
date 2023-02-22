package org.moonzhou.biz.noif.service.login.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.moonzhou.biz.noif.constant.LoginTypeEnum;
import org.moonzhou.biz.noif.param.LoginParam;
import org.moonzhou.biz.noif.param.UsernamePasswordLoginParam;
import org.moonzhou.biz.noif.service.login.LoginService;
import org.springframework.stereotype.Service;

/**
 * 账密登录实现
 * @author moonzhou
 */
@Slf4j
@Service
public class UsernamePasswordLoginServiceImpl extends LoginService {
    public static final String LOGIN_TYPE = LoginTypeEnum.USERNAME_PASSWORD.getValue();

    @Override
    protected boolean isThisLogin(LoginParam loginParam) {
        return LOGIN_TYPE.equals(loginParam.getLoginType());
    }

    @Override
    protected boolean checkLoginInfo(LoginParam loginParam) {
        log.info("username and password login!");

        // mock login user
        String username = "moon";
        String password = "moon";

        UsernamePasswordLoginParam usernamePasswordLoginParam = (UsernamePasswordLoginParam) loginParam;

        return StringUtils.equals(username, usernamePasswordLoginParam.getUsername())
                && StringUtils.equals(password, usernamePasswordLoginParam.getPassword());
    }
}
