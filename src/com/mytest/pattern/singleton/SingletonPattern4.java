package com.mytest.pattern.singleton;

/**
 * @Author murongyunge
 * @Describe   单例模式 静态内部类
 * @Date 2019-12-10
 */
public class SingletonPattern4 {
    private static class SingletonHolder{
        private static final SingletonPattern4 SINGLETON = new SingletonPattern4();
    }

    private SingletonPattern4(){

    }

    public static SingletonPattern4 getInstance(){
        return SingletonHolder.SINGLETON;
    }
}
