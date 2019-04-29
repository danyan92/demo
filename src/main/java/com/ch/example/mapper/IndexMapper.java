package com.ch.example.mapper;

import com.ch.example.vo.DemoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by chenhao on 2019/2/27.
 */
@Mapper
public interface IndexMapper {
    List<DemoVo> testIndex();

    int count();
}
