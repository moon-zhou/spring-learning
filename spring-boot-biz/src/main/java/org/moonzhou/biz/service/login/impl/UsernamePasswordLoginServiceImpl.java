package org.moonzhou.biz.service.login.impl;

import org.apache.commons.lang3.StringUtils;
import org.moonzhou.biz.constant.LoginTypeEnum;
import org.moonzhou.biz.param.LoginParam;
import org.moonzhou.biz.param.UsernamePasswordLoginParam;
import org.moonzhou.biz.service.login.LoginService;
import org.springframework.stereotype.Service;

/**
 * 账密登录实现
 * @author moonzhou
 */
@Service
public class UsernamePasswordLoginServiceImpl extends LoginService {
    public static final String LOGIN_TYPE = LoginTypeEnum.USERNAME_PASSWORD.getValue();

    @Override
    protected boolean isThisLogin(String loginType) {
        return LOGIN_TYPE.equals(loginType);
    }

    @Override
    protected boolean checkLoginInfo(LoginParam loginParam) {

        // mock login user
        String username = "moon";
        String password = "moon";

        UsernamePasswordLoginParam usernamePasswordLoginParam = (UsernamePasswordLoginParam) loginParam;

        return StringUtils.equals(username, usernamePasswordLoginParam.getUsername())
                && StringUtils.equals(password, usernamePasswordLoginParam.getPassword());
    }
}
