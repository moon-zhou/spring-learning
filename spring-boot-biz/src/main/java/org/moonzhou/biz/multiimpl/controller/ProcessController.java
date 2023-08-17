package org.moonzhou.biz.multiimpl.controller;

import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.dto.ResultDto;
import org.moonzhou.biz.multiimpl.enums.DetailPermissionTypeEnum;
import org.moonzhou.biz.multiimpl.factory.ApprovalProcessFactory;
import org.moonzhou.biz.multiimpl.param.ApprovalBaseParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author moon zhou
 * @since 2023-07-28
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    private final ApprovalProcessFactory approvalProcessFactory;

    public ProcessController(ApprovalProcessFactory approvalProcessFactory) {
        this.approvalProcessFactory = approvalProcessFactory;
    }

    @GetMapping("/detail/{id}/{step}/{permission}")
    public ApprovalBaseDTO detail(@PathVariable Long id, @PathVariable String step, @PathVariable String permission) {

        ApprovalBaseDTO detail = new ApprovalBaseDTO();
        DetailPermissionTypeEnum permissionType = DetailPermissionTypeEnum.getPermissionType(permission);
        switch (permissionType) {
            // read permission
            case VIEW_PERMISSION:
                // detail = approvalProcessFactory.detail(id);
                break;
            // write permission
            case APPROVAL_PERMISSION:
                detail = approvalProcessFactory.detail(id, step);
                break;
            // no permission
            default:
                throw new RuntimeException("no permission!!!");
        }

        return detail;
    }


    @PostMapping("/approve/{step}")
    public ResultDto<ApprovalBaseParam> approve(@RequestBody Map<String, Object> param, @PathVariable String step) {
        return approvalProcessFactory.approval(param, step);
    }

}
