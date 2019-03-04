package com.ch.example.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/4
 **/
public class ResponseVo<T> {
    @ApiModelProperty(value = "状态码")
    private int code;
    @ApiModelProperty(value = "消息")
    private String message;
    @ApiModelProperty(value = "data对象")
    private Object data;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public ResponseVo(int code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
