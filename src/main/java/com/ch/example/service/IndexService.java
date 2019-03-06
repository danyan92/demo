package com.ch.example.service;

import com.ch.example.vo.DemoVo;
import com.ch.example.vo.ResponsePageVo;

import java.util.List;

/**
 * Created by chenhao on 2019/2/27.
 */
public interface IndexService {
    List<DemoVo> testIndex();

    ResponsePageVo getPageList();
}
