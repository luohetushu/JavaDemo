package com.mytest.vacancy;

//静态内部接口
//具有相关功能或关系的接口的包装
public interface IMessageWrap {  //总包装
    static interface IMessage{
        public String getContent();
    }
    static interface IChannel{
        public boolean isConnect();
    }
    public static void send(IMessage iMessage, IChannel iChannel){
        if (iChannel.isConnect()){
            System.out.println(iMessage.getContent());
        } else {
            System.out.println("消息通道未连接，消息发送失败");
        }
    }
}
