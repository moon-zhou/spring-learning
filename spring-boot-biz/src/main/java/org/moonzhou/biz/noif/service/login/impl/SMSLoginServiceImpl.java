package org.moonzhou.biz.noif.service.login.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.moonzhou.biz.noif.constant.LoginTypeEnum;
import org.moonzhou.biz.noif.param.LoginParam;
import org.moonzhou.biz.noif.param.SMSLoginParam;
import org.moonzhou.biz.noif.service.login.LoginService;
import org.springframework.stereotype.Service;

/**
 * 短信登录实现
 * @author moonzhou
 */
@Slf4j
@Service
public class SMSLoginServiceImpl extends LoginService {

    public static final String LOGIN_TYPE = LoginTypeEnum.SMS.getValue();
    @Override
    protected boolean isThisLogin(LoginParam loginParam) {
        return LOGIN_TYPE.equals(loginParam.getLoginType());
    }

    @Override
    protected boolean checkLoginInfo(LoginParam loginParam) {
        log.info("sms login!");

        // mock login user
        String username = "moon";
        String sms = "999999";

        SMSLoginParam smsLoginParam = (SMSLoginParam) loginParam;

        return StringUtils.equals(username, smsLoginParam.getUsername())
                && StringUtils.equals(sms, smsLoginParam.getSms());
    }
}
