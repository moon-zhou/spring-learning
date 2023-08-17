package org.moonzhou.biz.multiimpl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author moon zhou
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum DetailPermissionTypeEnum {
    NO_PERMISSION("1"),
    VIEW_PERMISSION("2"),
    APPROVAL_PERMISSION("3"),
    ;

    private final String type;

    public static DetailPermissionTypeEnum getPermissionType(String type) {
        return Arrays.stream(values())
                .filter(enumValue -> enumValue.getType().equals(type)).findFirst().orElse(null);
    }

    /**
     * true 有权限
     *
     * @param permissionType
     * @return
     */
    public static boolean hasPermission(String permissionType) {
        return DetailPermissionTypeEnum.VIEW_PERMISSION.getType().equals(permissionType)
                || DetailPermissionTypeEnum.APPROVAL_PERMISSION.getType().equals(permissionType);
    }

    /**
     * true 有权限
     *
     * @param permissionType
     * @return
     */
    public static boolean hasApprovalPermission(String permissionType) {
        return DetailPermissionTypeEnum.APPROVAL_PERMISSION.getType().equals(permissionType);
    }
}
