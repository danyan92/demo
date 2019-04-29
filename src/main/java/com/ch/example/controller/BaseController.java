package com.ch.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/6
 **/
public class BaseController {

    @Autowired
    protected HttpServletRequest request ;

}
