package com.ch.example.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.ch.example.configuration.DruidConfiguration;
import com.ch.example.entity.EsDemo;
import com.ch.example.entity.EsDemoRes;
import com.ch.example.service.IndexService;
import com.ch.example.utils.Redis.RedisUtil;
import com.ch.example.vo.DemoVo;
import com.ch.example.vo.ResponsePageVo;
import com.ch.example.vo.ResponseVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/6
 **/
@Api(value = "indexController")
@RestController
@RequestMapping("/")
public class IndexController extends BaseController{

    Logger logger = LogManager.getLogger(DruidConfiguration.class);

    @Autowired
    private IndexService indexService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EsDemoRes esDemoRes;

    @ApiOperation(value = "首页")
    @GetMapping("index.do")
    public ResponseVo<List<DemoVo>> index(DemoVo demoVo){
        try {
            List<DemoVo> result= indexService.testIndex();
            redisUtil.set("ch",result);
            return new ResponseVo("成功",result).success();
        }catch (Exception e){
            logger.error("异常"+e);
            return new ResponseVo().fail("异常");
        }

    }

    @ApiOperation(value = "分页")
    @GetMapping("index2.do")
    public ResponseVo<ResponsePageVo<List<DemoVo>>> index2(DemoVo demoVo){
        try {
            System.out.println(redisUtil.get("ch"));
            ResponsePageVo page= indexService.getPageList();
            return new ResponseVo("成功",page).success();
        }catch (Exception e){
            logger.error("异常"+e);
            return new ResponseVo().fail("异常");
        }

    }

    @GetMapping(value = "/login")
    public ResponseVo getSession() {
        request.getSession().setAttribute("username", "admin");
        return new ResponseVo("成功",null).success();
    }

    @GetMapping(value = "/getUserInfo")
    public ResponseVo get(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("username");
        return new ResponseVo("成功",userName).success();
    }

    @GetMapping(value = "/esAdd")
    public ResponseVo esAdd(EsDemo esDemo) {
        esDemoRes.save(esDemo);
        return new ResponseVo("成功",null).success();
    }

    @GetMapping(value = "/esGet")
    public ResponseVo esGet() {
        Optional result=esDemoRes.findById(1L);
        return new ResponseVo("成功",result.get()).success();
    }

    @GetMapping(value = "/esGetAll")
    public ResponseVo esGetAll() {

        Iterable<EsDemo> result=esDemoRes.findAll();
        List<EsDemo> list =new ArrayList<>();
        result.forEach(m ->{
            list.add(m);
        });
        return new ResponseVo("成功",list).success();
    }




}
