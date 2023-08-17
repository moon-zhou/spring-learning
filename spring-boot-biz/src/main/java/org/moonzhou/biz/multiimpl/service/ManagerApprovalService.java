package org.moonzhou.biz.multiimpl.service;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.enums.ApprovalStepEnum;
import org.moonzhou.biz.multiimpl.param.ApprovalBaseParam;
import org.moonzhou.biz.multiimpl.param.ManagerApprovalParam;
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
@Service("managerApprovalService")
public class ManagerApprovalService implements ApprovalService {


    @Override
    public ApprovalBaseDTO detail(Long id) {
        ApprovalBaseDTO approvalBaseDTO = DataGenerator.initApprovalBaseDTO(id);

        approvalBaseDTO.setInfo(ApprovalStepEnum.MANAGER.getInfo());

        return approvalBaseDTO;
    }

    @Override
    public ApprovalBaseParam convert(Map<String, Object> param) {

        ManagerApprovalParam managerApprovalParam = JSON.parseObject(JSON.toJSONString(param), ManagerApprovalParam.class);

        validate(managerApprovalParam);

        return managerApprovalParam;
    }

    @Override
    public void validate(ApprovalBaseParam param) {
        ApprovalService.super.validate(param);

        // other biz validate
    }

    @Override
    public ApprovalBaseParam approval(ApprovalBaseParam param) {
        ManagerApprovalParam managerApprovalParam = (ManagerApprovalParam) param;

        managerApprovalParam.setApproveDateTime(LocalDateTime.now());

        return managerApprovalParam;
    }
}
