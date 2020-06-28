package com.mytest.pattern.observer;


import java.util.Observable;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-09
 */
public class RealSubject2 extends Observable {
    public void makeChanged() {
        setChanged();
        notifyObservers();
    }
}
