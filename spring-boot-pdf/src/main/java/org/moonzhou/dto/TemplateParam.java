package org.moonzhou.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/5/12 20:55
 **/
@Data
@AllArgsConstructor
public class TemplateParam {

    private String applicationFormCode;

    private String applicant;
    private String associateId;
    private String associateName;
    private String associateEmail;
    private String associateDepartment;
    private String associatePosition;
    private String associateLineManager;
    private LocalDate associateJoinDate;

    private String personnelClassification;
    private String eventClassification;
    private String difficultyDescription;
    private String payoutSummaryList;

    private BigDecimal applyAmount;
    private String paymentPeriodText;
    private String applyReason;
}
