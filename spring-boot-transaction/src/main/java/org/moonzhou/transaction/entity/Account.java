package org.moonzhou.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.moonzhou.transaction.entity.base.BaseEntityPhysicalDelete;

/**
 * @author moon zhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Account extends BaseEntityPhysicalDelete {
    private String userNo;
    private String name;
    private Integer age;
    private String email;
    private String dept;
}
