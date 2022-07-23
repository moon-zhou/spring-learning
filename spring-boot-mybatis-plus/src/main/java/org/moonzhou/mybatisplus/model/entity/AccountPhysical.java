package org.moonzhou.mybatisplus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.moonzhou.mybatisplus.model.base.BaseEntityPhysicalDelete;

/**
 * test common field and logic delete
 * @author moon zhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccountPhysical extends BaseEntityPhysicalDelete {
    private String userNo;
    private String name;
    private Integer age;
    private String email;
    private String dept;
}
