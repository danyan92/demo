package com.ch.example.test.designPatterns.ChainOfResponsibility;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 13:48
 */
public class ConcreteHandler2 extends Handler {
    public ConcreteHandler2(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE2) {
            System.out.println(request.getName() + " is handle by ConcreteHandler2");
            return;
        }
        if(successor!=null){
            successor.handleRequest(request);
        }
    }
}
