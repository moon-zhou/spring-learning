package org.moonzhou.springbootjwt.result;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/5/5 21:02
 * @since 1.0
 */
public class ResultError implements Serializable {
    private static final long serialVersionUID = -5248331626316118570L;

    @ApiModelProperty(value = "字段名（字段校验类异常）", position = 0)
    private String field;

    @ApiModelProperty(value = "异常消息", position = 1)
    private String errmsg;

    @ApiModelProperty(value = "异常编码", position = 2)
    private String errcode;
    public ResultError() {
    }

    public ResultError(String errmsg, String field) {
        this.field = field;
        this.errmsg = errmsg;
    }

    public ResultError(String errcode, String errmsg, String field) {
        this.field = field;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public ResultError(UserError userError){
        this.errcode = userError.getErrorCode();
        this.errmsg = userError.getErrorMessage();
    }

    public String getField() {
        return this.field;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public String getErrcode() {
        return this.errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "ResultError{" +
                "field='" + field + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", errcode='" + errcode + '\'' +
                '}';
    }
}
