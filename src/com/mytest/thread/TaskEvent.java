package com.mytest.thread;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-22
 */
public class TaskEvent {

    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }
        Thread thread = new Thread(new CleanTask(deque));
        //设置为守护线程
        thread.setDaemon(true);
        //thread.start();
    }

    private static class WriterTask implements Runnable{

        private Deque<Event> deque;

        public WriterTask(Deque<Event> deque) {
            this.deque = deque;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Event event = new Event();
                event.setDate(new Date());
                event.setEvent(String.format("the thread %d has generated an event", Thread.currentThread().getId()));
                deque.addFirst(event);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class CleanTask implements Runnable{

        private Deque<Event> deque;

        public CleanTask(Deque<Event> deque) {
            this.deque = deque;
        }

        @Override
        public void run() {
            while (true){
                Date date = new Date();
                Clean(date);
            }
        }

        private void Clean(Date date){
            if (deque.size() == 0){
                return;
            }
            long difference;
            boolean delete = false;
            do {
                Event event = deque.getLast();
                difference = date.getTime() - event.getDate().getTime();
                if (difference > 10000){
                    System.out.println("cleaner: " + event.getEvent());
                    deque.removeLast();
                    delete = true;
                }
            } while (difference > 10000);
            if (delete){
                System.out.println("cleaner: size of the deque::" + deque.size());
            }
        }

    }

    static class Event{
        Date date;
        String event;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
    }

}
