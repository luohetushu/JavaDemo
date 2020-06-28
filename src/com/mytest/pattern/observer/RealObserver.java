package com.mytest.pattern.observer;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-09
 */
public class RealObserver implements ObserverPattern {
    @Override
    public void update() {
        System.out.println("接收到了通知");
    }
}
