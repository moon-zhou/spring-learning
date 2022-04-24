package org.moonzhou.aspectj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.moonzhou.aspectj.annotation.Text;

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
public class ProcessDto {
    private String id;
    private LocalDateTime createTime;

    @Text(enumCode = "STATUS")
    private String status;

    @Text(enumCode = "PRIORITY")
    private String priority;
}
