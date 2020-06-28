package com.mytest.pattern.factory;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-10
 */
public class SenderMail implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mail sender!");
    }
}
