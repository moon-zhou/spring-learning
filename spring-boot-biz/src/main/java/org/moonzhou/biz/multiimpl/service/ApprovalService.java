package org.moonzhou.biz.multiimpl.service;

import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.param.ApprovalBaseParam;
import org.moonzhou.biz.multiimpl.util.ValidatorUtil;

import java.util.Map;

/**
 * @author moon zhou
 * @version 1.0
 * @description: 抽象服务
 */
public interface ApprovalService {

    ApprovalBaseDTO detail(Long id);

    default ApprovalBaseParam convert(Map<String, Object> param) {
        return null;
    }

    default void validate(ApprovalBaseParam param) {
        ValidatorUtil.validate(param);
    }

    default ApprovalBaseParam approval(ApprovalBaseParam param) {
        return null;
    }
}
