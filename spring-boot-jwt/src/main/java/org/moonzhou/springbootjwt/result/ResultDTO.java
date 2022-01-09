package org.moonzhou.springbootjwt.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/5/5 21:08
 * @since 1.0
 */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class ResultDTO<T> extends AbstractResultDTO {
    private static final long serialVersionUID = 5516592590193599541L;

    @ApiModelProperty(value = "业务数据", position = 1)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data", index = 2)
    public T getData() {
        return this.data;
    }

    private void setData(T data) {
        this.data = data;
    }

    public ResultDTO() {
    }

    ResultDTO(Status status) {
        this.status = status;
    }

    public static <T> ResultDTO<T> success() {
        ResultDTO<T> result = new ResultDTO<T>(Status.success);
        return result;
    }

    public static <T> ResultDTO<T> failure(ResultError... errors) {
        ResultDTO<T> result = new ResultDTO<T>(Status.failure);
        result.setErrors(errors);
        return result;
    }

    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> result = new ResultDTO<T>(Status.success);
        result.setData(data);
        return result;
    }

    public static <T> ResultDTO<T> failure(T data, ResultError... errors) {
        ResultDTO<T> result = new ResultDTO<T>(Status.failure);
        result.setData(data);
        result.setErrors(errors);
        return result;
    }
}
