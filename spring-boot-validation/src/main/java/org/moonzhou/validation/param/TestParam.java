package org.moonzhou.validation.param;

import lombok.Data;
import org.moonzhou.validation.annotation.IdNum;
import org.moonzhou.validation.annotation.MobilePhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author moon zhou
 * @version 1.0
 * @description: id num param
 * @date 2022/9/21 16:50
 */
@Data
public class TestParam {
    @Size(max = 6, min = 3, message = "size error")
    private String id;

    private String name;

    @MobilePhoneNumber
    private String phoneNumber;

    @Email
    @NotNull
    private String email;

    @IdNum
    private String idNum;

}
