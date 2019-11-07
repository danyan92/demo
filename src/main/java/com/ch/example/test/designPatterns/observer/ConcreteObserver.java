package com.ch.example.test.designPatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 18:19
 */
public class ConcreteObserver implements Observer{

    List<ConcreteSubject> subjectList = new ArrayList<ConcreteSubject>();

    @Override
    public void addSub(ConcreteSubject subject) {
        subjectList.add(subject);
    }

    @Override
    public void send(String message) {
        for(ConcreteSubject subject:subjectList){
            System.out.println(subject.getName()+":"+message);
        }
    }
}
