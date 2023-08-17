package org.moonzhou.biz.multiimpl.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.enums.ApprovalStepEnum;
import org.moonzhou.biz.multiimpl.util.DataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author moon zhou
 * @version 1.0
 * @description: 表单发起节点，后面只需要查看
 */
@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class WriteFormApprovalService implements ApprovalService {

    @Override
    public ApprovalBaseDTO detail(Long id) {
        ApprovalBaseDTO approvalBaseDTO = DataGenerator.initApprovalBaseFeatureDto(id);

        approvalBaseDTO.setInfo(ApprovalStepEnum.WRITE_FORM.getInfo());

        return approvalBaseDTO;
    }

}
