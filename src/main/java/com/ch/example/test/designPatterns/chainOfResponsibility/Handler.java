package com.ch.example.test.designPatterns.chainOfResponsibility;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 13:44
 */
public abstract class Handler {

    protected Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    protected abstract void handleRequest(Request request);
}
