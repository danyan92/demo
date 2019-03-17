package com.ch.example.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/11
 **/
@WebFilter(urlPatterns = "/*",filterName = "自定义过滤器")
@Order(1)
public class MyFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("web过滤器---------");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
