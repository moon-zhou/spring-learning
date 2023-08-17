package org.moonzhou.biz.multiimpl.factory;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.moonzhou.biz.multiimpl.dto.ApprovalBaseDTO;
import org.moonzhou.biz.multiimpl.dto.ResultDto;
import org.moonzhou.biz.multiimpl.enums.ApprovalStepEnum;
import org.moonzhou.biz.multiimpl.param.ApprovalBaseParam;
import org.moonzhou.biz.multiimpl.service.ApprovalService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author moon zhou
 * @version 1.0
 * @description: factory, manage all approval implements
 */
@AllArgsConstructor
@Component
public class ApprovalProcessFactory {

    private final Map<String, ApprovalService> approvalServiceMap;

    /**
     * 待审批单子，查看单子详情，用于审批
     * @param id
     * @param step
     * @return
     */
    public ApprovalBaseDTO detail(Long id, String step) {
        ApprovalService approvalService = getApprovalService(step);
        return approvalService.detail(id);
    }

    public ResultDto<ApprovalBaseParam> approval(Map<String, Object> param, String step) {
        ApprovalService approvalService = getApprovalService(step);

        ApprovalBaseParam approvalParam = approvalService.convert(param);

        return ResultDto.success(approvalService.approval(approvalParam));
    }

    /**
     * 查看处理过的单子
     * @param id
     * @return
     */
    /* public Map<String, Object> detail(Long id) {
        // 根据当前登录人，查询其关联的审批节点，根据节点找到对应的service实现，查询detail结果返回
        List<String> allActivityNameList = historyService.currentUserAllActivity(id);
        List<ApprovalService> approvalServiceList = getApprovalService(allActivityNameList);

        Map<String, Object> result = new HashMap<>();
        // 根据现有的
        approvalServiceList.forEach(approvalService -> {
            result.putAll(approvalService.detail(id));
        });

        return result;
    } */

    private ApprovalService getApprovalService(String step) {
        String serviceName = ApprovalStepEnum.getServiceName(step);
        Assert.notNull(serviceName, "approval step error, cannot mapping service...");

        // 根据前端步骤获取对应的实现类
        return approvalServiceMap.get(serviceName);
    }

    private List<ApprovalService> getApprovalService(List<String> allActivityNameList) {
        List<ApprovalService> approvalServiceList = new ArrayList<>();

        allActivityNameList.forEach(activityName -> {
            String serviceName = ApprovalStepEnum.getServiceName(activityName);
            if (StringUtils.isNotBlank(serviceName)) {
                approvalServiceList.add(approvalServiceMap.get(serviceName));
            }
        });

        Assert.notEmpty(approvalServiceList, "user has no history operation...");
        return approvalServiceList;
    }
}
