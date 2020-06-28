package com.mytest.pattern.singleton;

/**
 * @Author murongyunge
 * @Describe  单例模式 懒汉写法（线程安全）
 * @Date 2019-12-10
 */
public class SingletonPattern2 {
    private static SingletonPattern2 singleton;

    private SingletonPattern2(){

    }

    public static synchronized SingletonPattern2 getInstance(){
        if (singleton == null){
            singleton = new SingletonPattern2();
        }
        return singleton;
    }
}
