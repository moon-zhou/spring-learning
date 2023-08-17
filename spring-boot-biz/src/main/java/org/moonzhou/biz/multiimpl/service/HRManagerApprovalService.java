package org.moonzhou.biz.multiimpl.service;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.enums.ApprovalStepEnum;
import org.moonzhou.biz.multiimpl.param.ApprovalBaseParam;
import org.moonzhou.biz.multiimpl.param.HRManagerApprovalParam;
import org.moonzhou.biz.multiimpl.util.DataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * @author moon zhou
 * @version 1.0
 * @description: hr manager approval service
 */
@Slf4j
@AllArgsConstructor
@Transactional
@Service("hrManagerApprovalService")
public class HRManagerApprovalService implements ApprovalService {


    @Override
    public ApprovalBaseDTO detail(Long id) {
        ApprovalBaseDTO approvalBaseDTO = DataGenerator.initHRManagerApprovalDTO(id);

        approvalBaseDTO.setInfo(ApprovalStepEnum.HR_MANAGER.getInfo());

        return approvalBaseDTO;
    }

    @Override
    public ApprovalBaseParam convert(Map<String, Object> param) {
        HRManagerApprovalParam hrManagerApprovalParam = JSON.parseObject(JSON.toJSONString(param), HRManagerApprovalParam.class);

        validate(hrManagerApprovalParam);

        return hrManagerApprovalParam;
    }

    @Override
    public void validate(ApprovalBaseParam param) {
        ApprovalService.super.validate(param);

        // other biz validate
    }

    @Override
    public ApprovalBaseParam approval(ApprovalBaseParam param) {
        HRManagerApprovalParam hrManagerApprovalParam = (HRManagerApprovalParam) param;

        hrManagerApprovalParam.setApproveDateTime(LocalDateTime.now());

        return hrManagerApprovalParam;
    }
}
