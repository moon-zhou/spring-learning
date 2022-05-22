package org.moonzhou.aspectj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.moonzhou.aspectj.annotation.Text;
import org.moonzhou.aspectj.constant.Common;

import java.time.LocalDateTime;

/**
 * @author  moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/4/24 15:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDto extends BaseDto {
    private String id;
    private LocalDateTime createTime;

    @Text(enumCode = Common.DICT_ENUM_PROCESS_STATUS)
    private String status;

    @Text(enumCode = Common.DICT_ENUM_PRIORITY_LEVEL)
    private String priority;

}
