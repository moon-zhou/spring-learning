package org.moonzhou.biz.noif.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 账密登录参数
 * @author moonzhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UsernamePasswordLoginParam extends LoginParam {
    private String username;
    private String password;

    public UsernamePasswordLoginParam(String loginType, String username, String password) {
        super(loginType);
        this.username = username;
        this.password = password;
    }
}
