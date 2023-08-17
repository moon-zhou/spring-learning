package org.moonzhou.biz.multiimpl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @since 2023-08-17
 */
@Data
@NoArgsConstructor
public class ApprovalBaseDTO {

    private Long id;

    private String formId;

    private String formStatus;

    private String userNo;

    private String userName;

    private String department;

    private String telephone;

    private LocalDate lastWorkingDay;


    private LocalDateTime createTime;
    private String createUser;
    private String info;

}
