package com.mytest.vacancy;

//泛型类
public class NorMessage<T> implements IMessage<T> {
    T content;

    public T getContent() {
        return content;
    }

    public void setContent(T t) {
        this.content = t;
    }

    @Override
    public void send(T t) {
        System.out.println("消息发送：" + t);
    }
}
