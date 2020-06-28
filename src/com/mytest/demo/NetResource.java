package com.mytest.demo;

public class NetResource implements IResource {

    String resource;

    public NetResource(String resource) {
        this.resource = resource;
    }

    public boolean open(){
        System.out.println("资源通道开启");
        return true;
    }

    @Override
    public void send() {
        if (open()){
            System.out.println("资源消息发送: " + this.resource);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("资源通道关闭");
    }
}
