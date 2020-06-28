package com.mytest.pattern.observer;

/**
 * @Author murongyunge
 * @Describe 观察者主题对象
 * @Date 2019-12-09
 */
public interface SubjectPattern {
    /**
     * 订阅操作
     */
    void attach(ObserverPattern observer);

    /**
     * 取消订阅操作
     */
    void detach(ObserverPattern observer);

    /**
     * 通知变动
     */
    void notifyChanged();
}
