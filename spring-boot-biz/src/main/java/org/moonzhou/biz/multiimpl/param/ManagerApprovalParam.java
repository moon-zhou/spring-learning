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
public class ManagerApprovalParam extends ApprovalBaseParam {
    @NotNull
    private String confirmLastWorkingDay;

    private LocalDateTime approveDateTime;
}
