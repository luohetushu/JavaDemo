package com.mytest.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-23
 */
public class GroupTask {

    public static void main(String[] args) {
        //线程分组。允许我们按线程组作为一个单位来处理
        ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
        SearchTask searchTask = new SearchTask(new Result());
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(threadGroup, searchTask);
            thread.start();
         }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        System.out.println("number of thread::" + threadGroup.activeCount());
        System.out.println("information about the threadGroup");
        threadGroup.list();
        Thread[] threads = new Thread[threadGroup.activeCount()];//activeCount获取线程个数
        //与ThreadGroup对象关联的线程的列表
        threadGroup.enumerate(threads);
        for (int i = 0; i < threads.length; i++) {
            System.out.println("thread="+ threads[i].getName() + ":thread status=" + threads[i].getState());
        }
        waitFinish(threadGroup);//等待其中一个线程结束
        threadGroup.interrupt();//阻断其它线程

    }

    private static class SearchTask implements Runnable{

        private Result result;

        public SearchTask(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("thread: start::" + name);
            try {
                doTask();
                result.setName(name);
            } catch (InterruptedException e) {
                System.out.println("thread is interrupted:" + name);
                return;
            }
            System.out.println("thread end ::" + name);
        }

        private void doTask() throws InterruptedException {
            Random random = new Random((new Date()).getTime());
            int value = (int)(random.nextDouble()*100);
            System.out.println("thread : "+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(value);
        }

    }

    private static void waitFinish(ThreadGroup threadGroup) {
        //TODO Auto-generated method stub
        while (threadGroup.activeCount()>9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        }
    }

    static class Result{
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
