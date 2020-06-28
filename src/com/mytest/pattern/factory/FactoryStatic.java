package com.mytest.pattern.factory;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-10
 */
public class FactoryStatic {
    public static Sender getMailInstance(){
        return new SenderMail();
    }

    public static Sender getSmsInstance(){
        return new SenderSms();
    }
}
