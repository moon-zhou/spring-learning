package org.moonzhou.validation.param;

import lombok.Data;
import org.moonzhou.validation.check.onoff.EnumValid;
import org.moonzhou.validation.check.onoff.OnOffEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class StatusParam {

    @NotNull
    @NotEmpty
    @EnumValid(message = "on off value error!", enumClass = OnOffEnum.class)
    private String status;
}