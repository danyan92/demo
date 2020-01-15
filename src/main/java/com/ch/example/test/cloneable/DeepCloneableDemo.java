package com.ch.example.test.cloneable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: chenhao
 * @Date: 2019/12/13 15:42
 */
@Data
@AllArgsConstructor
public class DeepCloneableDemo implements Cloneable{
    private String id;

    private TestClass testClass;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
