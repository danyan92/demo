package com.ch.example.vo;

import io.swagger.annotations.ApiModelProperty;



/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/4
 **/
public class ResponsePageVo<T> {
    @ApiModelProperty(value = "总数")
    private int count;
    @ApiModelProperty(value = "数据")
    private Object data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponsePageVo(){
    }

    public ResponsePageVo(int count,Object data){
        this.count = count;
        this.data = data;
    }
}
