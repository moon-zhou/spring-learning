package org.moonzhou.transaction.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author moon zhou
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccountParam {
    private String userNo;
    private String name;
    private Integer age;
    private String email;
    private String dept;
}
