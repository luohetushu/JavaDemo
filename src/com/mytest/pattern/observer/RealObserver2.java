package com.mytest.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-09
 */
public class RealObserver2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("调用了-->");
    }
}
