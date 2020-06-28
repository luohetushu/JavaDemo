package com.mytest.pattern.factory;

/**
 * @Author murongyunge
 * @Describe  简单工厂类
 * @Date 2019-12-10
 */
public class FactorySimple {
    public Sender getSenderInstance(String type){
        if (type.equals("mail")){
            return new SenderMail();
        } else if (type.equals("sms")){
            return new SenderSms();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}
