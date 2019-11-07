package com.ch.example.test.designPatterns.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 18:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcreteSubject implements Subject {

    private String name;

    public ConcreteSubject(String name ,ConcreteObserver observer){
        this.name=name;
        observer.addSub(this);
    }

    @Override
    public void like(Observer observer) {
        observer.send(name);
    }
}
