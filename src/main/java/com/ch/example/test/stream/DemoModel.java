package com.ch.example.test.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: chenhao
 * @Date: 2020/1/2 16:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DemoModel {
    private int id;
    private String name;
    private int age;
    private double salary;
    private Status status;


    public enum Status {
        FREE, BUSY, VOCATION;
    }
}
