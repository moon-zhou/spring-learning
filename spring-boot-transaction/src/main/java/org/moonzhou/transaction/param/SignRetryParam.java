package org.moonzhou.transaction.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignRetryParam {
    private String userNo;
    private LocalDateTime signTime;
    private String parameter;
    private String type;
}