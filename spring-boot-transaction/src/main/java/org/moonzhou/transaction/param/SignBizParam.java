package org.moonzhou.transaction.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moon zhou
 * @version 1.0
 * @description: sign biz param, include sign and sign retry
 * @date 2022/11/3 10:22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignBizParam {

    private SignParam signParam;

    private SignRetryParam signRetryParam;

}
