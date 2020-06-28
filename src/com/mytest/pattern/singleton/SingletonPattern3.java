package com.mytest.pattern.singleton;

/**
 * @Author murongyunge
 * @Describe  单例模式 饿汉写法
 * @Date 2019-12-10
 */
public class SingletonPattern3 {
    private static final SingletonPattern3 SINGLETON = new SingletonPattern3();

    private SingletonPattern3(){

    }

    public static SingletonPattern3 getInstance(){
        return SINGLETON;
    }
}
