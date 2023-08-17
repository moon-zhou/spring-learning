package org.moonzhou.biz.multiimpl.service;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.enums.ApprovalStepEnum;
import org.moonzhou.biz.multiimpl.param.ApprovalBaseParam;
import org.moonzhou.biz.multiimpl.param.CommissionerApprovalParam;
import org.moonzhou.biz.multiimpl.util.DataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * @author moon zhou
 * @version 1.0
 * @description: manager approval service
 */
@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class CommissionerApprovalService implements ApprovalService {


    @Override
    public ApprovalBaseDTO detail(Long id) {
        ApprovalBaseDTO approvalBaseDTO = DataGenerator.initCommissionerApprovalDTO(id);

        approvalBaseDTO.setInfo(ApprovalStepEnum.COMMISSIONER.getInfo());

        return approvalBaseDTO;
    }

    @Override
    public ApprovalBaseParam convert(Map<String, Object> param) {
        CommissionerApprovalParam commissionerApprovalParam = JSON.parseObject(JSON.toJSONString(param), CommissionerApprovalParam.class);

        validate(commissionerApprovalParam);

        return commissionerApprovalParam;
    }

    @Override
    public void validate(ApprovalBaseParam param) {
        ApprovalService.super.validate(param);

        // other biz validate
    }

    @Override
    public ApprovalBaseParam approval(ApprovalBaseParam param) {
        CommissionerApprovalParam commissionerApprovalParam = (CommissionerApprovalParam) param;

        commissionerApprovalParam.setApproveDateTime(LocalDateTime.now());

        return commissionerApprovalParam;
    }
}
