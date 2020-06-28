package com.mytest.basic;

import java.net.InetAddress;
import java.util.concurrent.*;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-20
 */
public class BasicThread {

    public static void main(String[] args)  {
        System.out.println("主线程开始时间=" + System.currentTimeMillis());

        //继承 Thread 类
        MyThread myThread = new MyThread();    //创建一个线程类
        myThread.start();

        //实现 Runnable 接口：
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "Runnable 类多线程1").start(); //启动多线程 //这是线程类 MyRunnable：Runnable 类多线程1
        new Thread(myRunnable, "Runnable 类多线程2").start(); //启动多线程 //这是线程类 MyRunnable：Runnable 类多线程2
        //或使用 ExecutorService 启动多线程
        //ExecutorService service =  Executors.newSingleThreadExecutor();
        //service.submit(myRunnable);
        //service.execute(myRunnable);
        myRunnable.run(); //这是线程类 MyRunnable：main currentThread() 是主线程
        //主线程可以创建若干个子线程，创建子线程的目的是处理一些功能逻辑复杂或者耗时的线程
        //主线程负责整体流程，子线程负责处理耗时操作

        Runnable run1 = () -> {
            System.out.println("使用匿名内部类转换成的 Lambda 表达式创建的多线程run1");
        };
        new Thread(run1).start();

        new Thread(() -> {
            try {
                System.out.println("使用匿名内部类转换成的 Lambda 表达式创建的多线程run2，省去了定义");
                Thread.sleep(1000); //休眠的特点是可以自动实现线程的唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Lambda 表达式创建的多线程run2").start();

        try {
            //通过 Callable 和 Future 创建线程：
            MyCaller myCaller = new MyCaller();
            FutureTask<String> futureTask = new FutureTask<>(myCaller);
            Thread thread1 = new Thread(futureTask);
            thread1.start();
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //休眠中断，拋出 InterruptedException 异常
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": 休眠开始");
            try {
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + ": 休眠结束");
            } catch (InterruptedException e){
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + ": 休眠被打断了");
            }
        }, "被中断的线程对象");
        thread1.start();
        try {
            Thread.sleep(1000);
            if (!thread1.isInterrupted()){
                thread1.interrupt(); //中断线程
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //强制某一线程执行
        Thread mainThread = Thread.currentThread(); //主线程
        System.out.println(mainThread.getName() + ": 优先级：" + mainThread.getPriority()); // 5 获取主线程的优先级
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                if (i > 2){
                    try {
                        mainThread.join(); //主线程强制执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("主线程强制执行被中断");
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": i=" + i);
            }
        }, "被强制执行的线程对象");
        thread2.start();
//        try {
//            Thread.sleep(1000);
//            if (!thread2.isInterrupted()){
//                thread2.interrupt(); //中断主线程强制执行
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //线程的礼让
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                if (i == 2){
                    Thread.yield(); //线程礼让
                    System.out.println(Thread.currentThread().getName() + ": 礼让了一次");
                }
                System.out.println(Thread.currentThread().getName() + ": i=" + i);
            }
        }, "礼让的线程对象").start();

        //在主线程中输出一个字符串
        System.out.println("主线程结束时间=" + System.currentTimeMillis());

    }

    //自定义线程类为 MyThread
    static class MyThread extends Thread{
        private int count = 5;
        @Override
        public void run() {
            super.run();
            System.out.println("正在运行的线程名称：" + currentThread().getName() +
                    " 开始时间=" + System.currentTimeMillis());
            count--;
            System.out.println("正在运行的线程名称：" + currentThread().getName() +
                    " 结束时间=" + System.currentTimeMillis());
        }
    }

    //自定义线程类为 MyRunnable
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            //获取当前线程 public static native Thread currentThread();
            System.out.println("这是线程类 MyRunnable：" + Thread.currentThread().getName());
        }
    }

    //自定义线程类为 MyCaller
    static class MyCaller implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("这是线程类 MyCaller");
            return "这是线程类 MyCaller 结束后返回结果";
        }
    }

    private static String username;
    private static String password;

    //使用 synchronized 关键字修饰 doPost() 方法，不允许多个线程同时修改 doPost() 方法中的变量
    synchronized private static void doPost(String _username,String _password) {
        try {
            username = _username;
            if (username.equals("admin")) {
                Thread.sleep(1000);
            }
            password = _password;
            System.out.println("username=" + username + ", password=" + password);
        } catch(InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
