package org.moonzhou.aspectj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.moonzhou.aspectj.annotation.Text;
import org.moonzhou.aspectj.constant.Common;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/5/22 19:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String name;

    @Text(enumCode = Common.DICT_ENUM_POSITION_LEVEL)
    private String levelId;

    private UserDto leader;
}
