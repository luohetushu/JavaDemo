package com.mytest.basic;

import com.mytest.utils.StringUtils;
import com.mytest.utils.VerifyUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class BasicTool {
    public static void main(String[] args) {
        //当有多个线程在进行各自信息处理，很有可能出现线程信息处理交错的情况
        for (int i = 0; i < 3; i++) {
            final int finalI = i;
            new Thread(() -> {
                Message message = new Message(); //多个线程同时作用，会出现 message 的覆盖，不同步的问题
                message.setContent("第" + finalI + "个线程要发送的消息");
                Channel.setMessage(message);
                Channel.send(); //消息发送
            }, "消息发送的线程" + i).start();
        }

        //启动定时任务
        Timer timer = new Timer(); //定时器
        timer.schedule(new TimeTask(), 1000);  //启动定时任务
        timer.cancel();

        //加密与解密
        String salt = "salt"; //使用盐值操作
        String info = "这是一段需要加密的绝密内容" + "{" + salt + "}";
        //加密
        String enInfo = new String(Base64.getEncoder().encode(info.getBytes()));
        System.out.println(enInfo); //6L+Z5piv5LiA5q616ZyA6KaB5Yqg5a+G55qE57ud5a+G5YaF5a65e3NhbHR9
        //解密
        String deInfo = new String(Base64.getDecoder().decode(enInfo));
        System.out.println(deInfo.replaceAll("\\{\\w+}", "")); //这是一段需要加密的绝密内容

        String src = "这是一段需要加密的绝密内容";
        //多次加密
        String enReSrc = StringUtils.encrypt(src, 5);
        System.out.println(enReSrc);
        //多次解密
        String deReSrc = StringUtils.decrypt(enReSrc, 5);
        System.out.println(deReSrc); //这是一段需要加密的绝密内容

        //
        System.out.println(VerifyUtils.isEmail("1398009647@qq.com")); //true

        System.out.println(VerifyUtils.isIPAddress("192.168.3.10")); //true

    }

    //发送的消息体
    static class Message{
        String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    //消息的发送通道
    static class Channel{
        private static final ThreadLocal<Message> THREAD_LOCAL = new ThreadLocal<>();

        private Channel(){}

        public static void setMessage(Message message) {
            THREAD_LOCAL.set(message); //存储当前线程与处理的对象
        }

        public static void send(){
            System.out.println("[" + Thread.currentThread().getName() + "、消息发送] " + THREAD_LOCAL.get().getContent());
        }

    }

    //定时任务
    static class TimeTask extends TimerTask{
        @Override
        public void run() {  //多线程的处理方法
            System.out.println(Thread.currentThread().getName() + "、定时任务执行，当前时间："
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }

}
