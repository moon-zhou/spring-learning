package org.moonzhou.biz.multiimpl.util;

import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.dto.ApprovalBaseFeatureDto;
import org.moonzhou.biz.multiimpl.dto.CommissionerApprovalDTO;
import org.moonzhou.biz.multiimpl.dto.HRManagerApprovalDTO;

import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @version 1.0
 * @date 2023/8/17 2Ï1:33
 */
public class DataGenerator {
    public static ApprovalBaseDTO initApprovalBaseDTO(Long id) {
        ApprovalBaseDTO approvalBaseDTO = new ApprovalBaseDTO();

        // 实际场景，通过id查询表单数据详情，此处使用mock数据
        approvalBaseDTO.setId(id);

        approvalBaseDTO.setUserNo("001");
        approvalBaseDTO.setUserName("moon zhou");
        approvalBaseDTO.setTelephone("111");

        approvalBaseDTO.setCreateTime(LocalDateTime.now());

        return approvalBaseDTO;
    }

    public static ApprovalBaseFeatureDto initApprovalBaseFeatureDto(Long id) {
        ApprovalBaseFeatureDto approvalBaseFeatureDto = new ApprovalBaseFeatureDto();

        approvalBaseFeatureDto.setId(id);

        approvalBaseFeatureDto.setUserNo("001");
        approvalBaseFeatureDto.setUserName("moon zhou");
        approvalBaseFeatureDto.setTelephone("111");

        approvalBaseFeatureDto.setFutureTelephone("333");

        approvalBaseFeatureDto.setCreateTime(LocalDateTime.now());

        return approvalBaseFeatureDto;
    }

    public static ApprovalBaseFeatureDto initHRManagerApprovalDTO(Long id) {
        HRManagerApprovalDTO hrManagerApprovalDTO = new HRManagerApprovalDTO();

        hrManagerApprovalDTO.setId(id);

        hrManagerApprovalDTO.setUserNo("001");
        hrManagerApprovalDTO.setUserName("moon zhou");
        hrManagerApprovalDTO.setTelephone("111");

        hrManagerApprovalDTO.setFutureTelephone("333");

        hrManagerApprovalDTO.setNextCompany("My Company");

        hrManagerApprovalDTO.setCreateTime(LocalDateTime.now());

        return hrManagerApprovalDTO;
    }

    public static CommissionerApprovalDTO initCommissionerApprovalDTO(Long id) {
        CommissionerApprovalDTO commissionerApprovalDTO = new CommissionerApprovalDTO();

        // 实际场景，通过id查询表单数据详情，此处使用mock数据
        commissionerApprovalDTO.setId(id);

        commissionerApprovalDTO.setUserNo("001");
        commissionerApprovalDTO.setUserName("moon zhou");
        commissionerApprovalDTO.setTelephone("111");

        commissionerApprovalDTO.setValuation("good");

        commissionerApprovalDTO.setCreateTime(LocalDateTime.now());

        return commissionerApprovalDTO;
    }
}
