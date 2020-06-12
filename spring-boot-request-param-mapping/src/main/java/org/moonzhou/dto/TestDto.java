package org.moonzhou.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author moon-zhou
 * @date 2020/6/12 18:06
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Setter
@Getter
public class TestDto {

    private String userName;

    private int age;

    private boolean handSome;

    private Long id;

    private Date birthday;
}
