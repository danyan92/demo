package com.ch.example.test.designPatterns.factory;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 14:55
 */
public abstract class EventHandler {
    private static Map<String,EventHandler> handlers = new HashMap<>();
    //事件类型
    public abstract String type();
    //子类注册入口
    public static void register(EventHandler eh){
        handlers.put(eh.type(),eh);
    }
    //获取不同的实现
    public static EventHandler getHandler(Event e){
    return handlers.get(e.getType());

    }

    public abstract void doSomething();
}
