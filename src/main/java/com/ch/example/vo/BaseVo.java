package com.ch.example.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/4
 **/
public class BaseVo {
    @ApiModelProperty(value = "每页数量")
    private int pageSize;
    @ApiModelProperty(value = "页码")
    private int pageNum;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
