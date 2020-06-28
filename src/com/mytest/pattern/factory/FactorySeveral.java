package com.mytest.pattern.factory;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-10
 */
public class FactorySeveral {
    public Sender getMailInstance(){
        return new SenderMail();
    }

    public Sender getSmsInstance(){
        return new SenderSms();
    }
}
