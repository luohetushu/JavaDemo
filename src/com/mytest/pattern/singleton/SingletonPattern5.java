package com.mytest.pattern.singleton;

/**
 * @Author murongyunge
 * @Describe  单例模式  双重校验锁
 * @Date 2019-12-10
 */
public class SingletonPattern5 {
    //当一个变量定义为 volatile 之后：
    //1、保证此变量对所有的线程的可见性
    //2、禁止指令重排序优化
    private static volatile SingletonPattern5 singleton;

    private SingletonPattern5(){

    }

    public static SingletonPattern5 getInstance(){
        if (singleton == null){
            synchronized (SingletonPattern5.class){
                if (singleton == null){
                    singleton = new SingletonPattern5();
                }
            }
        }
        return singleton;
    }
}
