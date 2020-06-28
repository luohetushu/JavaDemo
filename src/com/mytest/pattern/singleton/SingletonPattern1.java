package com.mytest.pattern.singleton;

/**
 * @Author murongyunge
 * @Describe  单例模式 懒汉写法（线程不安全）
 *      该类只允许提供一个实例化对象
 *      构造方法私有化
 *      Java 中使用单例设计模式的类：Runtime 类(饿汉式)，Spring 框架
 * @Date 2019-12-10
 */

public class SingletonPattern1 {
    private static SingletonPattern1 singleton;

    private SingletonPattern1(){

    }

    public static SingletonPattern1 getInstance(){
        if (singleton == null){
            singleton = new SingletonPattern1();
        }
        return singleton;
    }
}
