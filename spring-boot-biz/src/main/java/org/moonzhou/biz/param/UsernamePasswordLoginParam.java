package org.moonzhou.biz.param;

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
}
