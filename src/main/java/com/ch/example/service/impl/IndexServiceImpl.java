package com.ch.example.service.impl;

import com.ch.example.mapper.IndexMapper;
import com.ch.example.service.IndexService;
import com.ch.example.vo.DemoVo;
import com.ch.example.vo.ResponsePageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenhao on 2019/2/27.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Override
    public List<DemoVo> testIndex() {
        return indexMapper.testIndex();
    }

    @Override
    public ResponsePageVo getPageList() {
        ResponsePageVo page =new ResponsePageVo();
        page.setData(indexMapper.testIndex());
        page.setCount(indexMapper.count());
        return page;
    }
}
