package org.moonzhou.biz.multiimpl.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 */
@Data
public class HRManagerApprovalParam extends ApprovalBaseParam {
    @NotNull
    private String nextCompany;

    private LocalDateTime approveDateTime;
}
