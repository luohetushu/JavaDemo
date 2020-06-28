package com.mytest.pattern.proxy;

/**
 * @Author murongyunge
 * @Describe  具体主题类
 * @Date 2019-12-10
 */
public class ConcreteSubject implements Subject {
    /**
     * 具体的业务逻辑实现
     */
    @Override
    public void request() {
        //业务处理逻辑
        System.out.println("我们想找专业婚介举办婚礼～");
    }

    @Override
    public void marry() {
        System.out.println("我们结婚啦～");
    }
}
