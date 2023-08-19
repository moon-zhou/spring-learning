package org.moonzhou.springcache.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/8/19 21:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizParam {

    private Long id;

    private String name;

    private Integer age;

    private LocalDate birthDay;

    private LocalDateTime createTime;
}
