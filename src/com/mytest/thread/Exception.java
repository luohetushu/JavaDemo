package com.mytest.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-23
 */
public class Exception {
    /**
     * Java有2种异常:
     * 检查异常（Checked exceptions）: 该异常必须强制捕获并处理，或者放在throws子句中。 eg：IOException、ClassNotFoundException
     * 未检查异常（Unchecked exceptions）: 此类异常不用强制捕获 eg：NumberFormatException
     */

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        //捕获和处理线程对象抛出的未检测异常来避免程序终结
        thread.setUncaughtExceptionHandler(new ExceptionHandle());
        thread.start();

        UnsafeTask unsafeTask = new UnsafeTask();
        for (int i = 0; i < 3; i++) {
            //多个Thread对象使用同样的Runnable对象，全部的线程都共享同样的属性
            Thread thread0 = new Thread(unsafeTask);
            //所有的这些线程都有着相同的结束时间。没有使用本地线程变量以前
            thread0.start();
        }

    }

    private static class Task implements Runnable{

        @Override
        public void run() {
            int number = Integer.parseInt("aaa");//未检查异常
        }

    }

    private static class ExceptionHandle implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("an exception has been captured");
            System.out.println("thread " + t.getId());
            System.out.println("exception :" + e.getClass().getName() + ":" + e.getMessage());
            e.printStackTrace(System.out);
            System.out.println("thread status "+ t.getState());
        }
    }

    private static class UnsafeTask implements Runnable{

        private Date startDate;
        //本地线程变量机制
        private static ThreadLocal<Date> localDate = ThreadLocal.withInitial(() -> new Date());


        @Override
        public void run() {
            startDate = new Date();
            System.out.println("starting localDate thread:" + Thread.currentThread().getId() + ":" + localDate.get());
            System.out.println("starting startDate thread:" + Thread.currentThread().getId() + ":" + startDate);
            try {
                /**
                 * Math.floor:求一个浮点数的地板，向下 求一个最接近它的整数，它的值肯定会小于或等于这个浮点数 Math.floor(-1.5): -2.0
                 * Math.ceil:向上 取接近的整数，它返回的肯定会大于或等于函数参数 Math.ceil(-1.5): -1.0
                 * Math.rint:返回最接近参数的整数，如果有2个数同样接近，则会返回偶数的那个 Math.rint(-1.5): -2.0
                 * Math.round(x) = Math.floor(x + 0.5)  Math.round(-1.5): -1
                 */
                TimeUnit.SECONDS.sleep((long) Math.rint(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("stoping localDate thread:" + Thread.currentThread().getId() + ":" + localDate.get());
            System.out.println("stoping startDate thread:" + Thread.currentThread().getId() + ":" + startDate);
        }
    }

}
