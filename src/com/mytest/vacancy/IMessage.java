package com.mytest.vacancy;

//泛型接口
@FunctionalInterface //函数式接口：接口中只提供一个抽象方法，能被 Lambda 表达式所使用
public interface IMessage<T> {
    public void send(T msg);

    public default void print() {

    }

    public static void receive(){

    }

}
