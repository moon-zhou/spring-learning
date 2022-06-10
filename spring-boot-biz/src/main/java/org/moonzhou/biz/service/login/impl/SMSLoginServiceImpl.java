package org.moonzhou.biz.service.login.impl;

import org.moonzhou.biz.constant.LoginTypeEnum;
import org.moonzhou.biz.param.LoginParam;
import org.moonzhou.biz.service.login.LoginService;
import org.springframework.stereotype.Service;

/**
 * 短信登录实现
 * @author moonzhou
 */
@Service
public class SMSLoginServiceImpl extends LoginService {

    public static final String LOGIN_TYPE = LoginTypeEnum.SMS.getValue();
    @Override
    protected boolean isThisLogin(String loginType) {
        return LOGIN_TYPE.equals(loginType);
    }

    @Override
    protected boolean checkLoginInfo(LoginParam loginParam) {
        return false;
    }
}
