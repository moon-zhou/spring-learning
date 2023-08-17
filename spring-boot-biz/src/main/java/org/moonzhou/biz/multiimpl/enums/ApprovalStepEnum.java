package org.moonzhou.biz.multiimpl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author moon zhou
 * @version 1.0
 * @description: all approval step
 */
@Getter
@AllArgsConstructor
public enum ApprovalStepEnum {
    WRITE_FORM("00", "writeFormApprovalService", "applicant submit"),
    MANAGER("01", "managerApprovalService", "manager approve"),
    COMMISSIONER("02", "commissionerApprovalService", "commissioner approve"),
    HR_MANAGER("03", "hrManagerApprovalService", "hr manager approve"),
    ;

    private final String step;
    private final String serviceName;
    private final String info;

    public static String getServiceName(String step) {
        ApprovalStepEnum approvalStepEnum = Arrays.stream(values())
                .filter(enumValue -> StringUtils.equals(enumValue.getStep(), step))
                .findFirst().orElse(null);
        return null != approvalStepEnum ? approvalStepEnum.getServiceName() : null;
    }
}
