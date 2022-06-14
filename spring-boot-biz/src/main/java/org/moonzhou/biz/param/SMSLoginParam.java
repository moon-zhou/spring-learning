package org.moonzhou.biz.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信登录
 * @author moonzhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SMSLoginParam extends LoginParam {
    private String username;
    private String sms;

    public SMSLoginParam(String loginType, String username, String sms) {
        super(loginType);
        this.username = username;
        this.sms = sms;
    }
}
