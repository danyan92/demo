package com.ch.example.service.impl;

import com.ch.example.mapper.IndexMapper;
import com.ch.example.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenhao on 2019/2/27.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Override
    public int testIndex() {
        return indexMapper.testIndex();
    }
}
