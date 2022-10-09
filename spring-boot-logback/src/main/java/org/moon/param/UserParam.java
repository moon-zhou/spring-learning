package org.moon.param;

import lombok.Getter;
import lombok.Setter;
import org.moon.base.BaseParam;

/**
 * @author moon zhou
 * @version 1.0
 * @description: user param
 * @date 2022/10/9 17:24
 */
@Getter
@Setter
public class UserParam extends BaseParam {
    private String name;
    private int age;
}
