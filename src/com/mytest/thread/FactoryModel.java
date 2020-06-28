package com.mytest.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-23
 */
public class FactoryModel {

    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("my-thread-factory");
        Task task = new Task();
        for (int i = 0; i < 10; i++) {
            Thread thread = myThreadFactory.newThread(task);
            thread.start();
            System.out.println(myThreadFactory.getStats());
        }
    }

    private static class MyThreadFactory implements ThreadFactory{

        private int counter;
        private String name;
        private List<String> lists;

        public MyThreadFactory(String name) {
            this.counter = 0;
            this.name = name;
            this.lists = new ArrayList<>();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "thread" + counter);
            counter ++;
            lists.add(String.format("create thread %d with name %s on %s\n", thread.getId(), thread.getName(), new Date()));
            return thread;
        }

        private String getStats(){
            StringBuffer sbf = new StringBuffer();
            Iterator<String> it = lists.iterator();
            while (it.hasNext()) {
                String string = it.next();
                sbf.append(string);
                sbf.append("\n");
            }
            return sbf.toString();
        }

    }

    private static class Task implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
