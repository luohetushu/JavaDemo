package com.mytest.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-09
 */
public class RealSubject implements SubjectPattern {

    private List<ObserverPattern> observerList = new ArrayList<>();

    @Override
    public void attach(ObserverPattern observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(ObserverPattern observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyChanged() {
        for (ObserverPattern observer : observerList) {
            observer.update();
        }
    }
}
