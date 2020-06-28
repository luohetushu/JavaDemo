package com.mytest.pattern.factory;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-10
 */
public class SenderSmsFactory implements Factory {
    @Override
    public Sender getInstance() {
        return new SenderSms();
    }
}
