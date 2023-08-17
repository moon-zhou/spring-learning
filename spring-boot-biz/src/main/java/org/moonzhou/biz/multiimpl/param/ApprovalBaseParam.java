package org.moonzhou.biz.multiimpl.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 */
@Data
public class ApprovalBaseParam {
    @NotNull
    private Long id;

    @Size(max = 100)
    private String approvalComment;

    @Size(max = 2)
    private String approvalAction;
}
