package org.moonzhou.biz.multiimpl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moon zhou
 * @since 2023-08-17
 */
@Data
@NoArgsConstructor
public class CommissionerApprovalDTO extends ApprovalBaseDTO {

    private String valuation;

}
