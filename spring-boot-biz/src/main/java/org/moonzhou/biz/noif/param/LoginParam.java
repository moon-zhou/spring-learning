package org.moonzhou.biz.noif.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moonzhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {

    /**
     * 登录类型
     */
    private String loginType;
}
