package org.moonzhou.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.moonzhou.transaction.entity.base.BaseEntityPhysicalDelete;

import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 14:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SignRetry extends BaseEntityPhysicalDelete {
    private String userNo;
    private LocalDateTime signTime;
    private String parameter;
    private String type;
}
