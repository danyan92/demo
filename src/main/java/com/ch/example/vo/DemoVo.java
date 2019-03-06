package com.ch.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/4
 **/
public class DemoVo {
    @ApiModelProperty(value = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
