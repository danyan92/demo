package com.ch.example.entity;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author chenhao
 * @Date 2020/3/19
 **/
@Component
public interface EsDemoRes extends ElasticsearchRepository<EsDemo,Long> {
}
