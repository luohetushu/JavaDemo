package com.mytest.thread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-21
 */
public class Calculator implements Runnable{

    private int number;

    public Calculator(int number) {
        super();
        this.number = number;
    }

    /**
     * 在Java中，我们有2个方式创建线程：
     * 1、通过直接继承thread类，然后覆盖run()方法。
     * 2、构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
        }
    }

    /**
     * Thread类的对象属性：
     * ID: 每个线程的独特标识。
     * Name: 线程的名称。
     * Priority: 线程对象的优先级。优先级别在1-10之间，1是最低级，10是最高级。不建议改变它们的优先级，但是你想的话也是可以的。
     * Status: 线程的状态。在Java中，线程只能有这6种中的一种状态： new, runnable, blocked, waiting, time waiting, terminated.
     */
    public static void main(String[] args) throws IOException {
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];

        for (int i = 0; i < threads.length; i++) {
            //设置每个线程优先级及名称
            threads[i] = new Thread(new Calculator(i));
            threads[i].setPriority(i + 1);
            threads[i].setName("thread" + i);
        }

        //将线程状态的改变写入文档
        //com+option+t 包含代码块
        FileWriter file = new FileWriter("thread.txt");  //当前项目文件夹下
        PrintWriter writer = new PrintWriter(file);
        for (int i = 0; i < threads.length; i++) {
            System.out.println("Thread : state of thread" + i + ": " + threads[i].getState());
            states[i] = threads[i].getState();
        }
        writer.flush();

        for (int i = 0; i < threads.length; i++) {
            //执行线程
            threads[i].start();
        }

        boolean finish = false;
        while (!finish){
            for (int i = 0; i < threads.length; i++) {
                if (threads[i].getState() != states[i]){
                    writeThreadInfo(writer, threads[i], states[i]);
                    states[i] = threads[i].getState();
                }
            }
            for (int i = 0; i < threads.length; i++) {
                finish = states[i] == Thread.State.TERMINATED;
                if (!finish){
                    break;
                }
            }
        }

    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        // TODO Auto-generated method stub
        pw.printf("Thread : Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Thread : Priority: %d\n", thread.getPriority());
        pw.printf("Thread : Old State: %s\n", state);
        pw.printf("Thread : New State: %s\n", thread.getState());
        pw.printf("Thread : ************************************\n");
        pw.flush();
    }

}
