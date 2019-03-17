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
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseVo() {
    }


    public ResponseVo(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseVo(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVo success(){
        this.code = Constants.RESPONSE_SUCCESS;
        return this;
    }

    public ResponseVo fail(String message){
        this.code = Constants.RESPONSE_FAIL;
        this.message = message;
        return this;
    }
}
