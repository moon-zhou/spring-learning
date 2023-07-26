package org.moonzhou.springsession.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/7/26 21:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String name;
    private Integer age;
}
