package org.moonzhou.biz.multiimpl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/8/17 21:04
 */
@Data
@NoArgsConstructor
public class ApprovalBaseFeatureDto extends ApprovalBaseDTO {
    private String futureTelephone;

    private String futureHomeAddress;

    private String futurePostCode;

    private String futurePrivateEmail;

    private String futureEmergencyPersonName;

    private String futureEmergencyPersonTelephone;
}
