package com.mytest.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-22
 */
public class SourceLoader {

    private static class DataSourceLoader implements Runnable{

        @Override
        public void run() {

            System.out.println("beginning data source loading: " + new Date());

            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("finishing data source loading: " + new Date());

        }

    }

    private static class NetworkSourceLoader implements Runnable{

        @Override
        public void run() {

            System.out.println("beginning network source loading: " + new Date());

            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("finishing network source loading: " + new Date());

        }

    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new DataSourceLoader());
        thread1.start();
        Thread thread2 = new Thread(new NetworkSourceLoader());
        thread2.start();

        try {
            //join (long milliseconds):让调用线程等待特定的毫秒数
            //join (long milliseconds, long nanos):接收一个毫秒数和一个纳秒数作为参数
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("configuration has been loaded");

    }

}
