package com.ch.example.controller;

import com.ch.example.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenhao on 2019/2/27.
 */
@Api(value = "indexController")
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation(value = "首页")
    @GetMapping("index.do")
    public String index(){
        return String.valueOf(indexService.testIndex());
    }

}
