package com.mytest.basic;

import java.lang.ref.Cleaner;

public class BasicCleaner {

    public static void main(String[] args) {
        Member member = new Member(); //创建被清理的对象
        // 对象 member 可能会有的一些操作
        //手动清理对象
        try(MemberCleaning mc = new MemberCleaning(member)){
            System.out.println("查看对象被清理的过程：");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //要进行清理的类，实现多线程
    static class Member implements Runnable{

        public Member() {
            System.out.println("【构造】对象创建成功");
        }

        @Override
        public void run() {
            //在单独的线程中进行对象的清理
            System.out.println("【回收】对象被清理了");
        }

    }

    //对象清理器类: 实现清理处理
    static class MemberCleaning implements AutoCloseable{

        private Cleaner cleaner = Cleaner.create();  //创建一个清理器
        private Cleaner.Cleanable cleanable;

        private Member member; //要被清理的对象

        public MemberCleaning(Member member) {
            this.member = member;  //创建对象
            //注册 ublic Cleaner.Cleanable register(Object obj, Runnable action)
            //obj: 当前操作的对象  action: 被清理的对象
            this.cleanable = this.cleaner.register(MemberCleaning.this, this.member);
        }

        @Override
        public void close() throws Exception {
            this.cleanable.clean(); //清理，启动多线程，即调用被清理类 Member 的 run 方法
        }
    }

}
