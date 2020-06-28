package com.mytest.basic;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-14
 */
public class BasicException {

    public static void main(String[] args) {
        BasicException exception = new BasicException();

        //自定义异常
        exception.exceptionDemo();


        System.out.println(exception.getInt());

    }

    private void exceptionDemo(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入您的年龄：");
        try {
            int age = input.nextInt();    //获取年龄
            if(age < 0) {
                throw new MyException("您输入的年龄为负数！输入有误！");
            } else if(age > 100) {
                throw new MyException("您输入的年龄大于100！输入有误！");
            } else {
                System.out.println("您的年龄为：" + age);
            }
        } catch(InputMismatchException e1) {
            System.out.println("输入的年龄不是数字！");
        } catch(MyException e2) {
            System.out.println(e2.getMessage());
        }
    }

    /**
     * 自定义异常
     */
    static class MyException extends Exception{
        public MyException() {
        }

        public MyException(String message) {
            super(message);
        }
    }

    /**
     * java面试题--如果catch里面有return语句，finally里面的代码还会执行吗？
     * 会执行，在 return 前执行
     * @return
     */
    private int getInt() {
        int a;
        try {
            a = 20;
            System.out.println(a / 0);
        } catch (ArithmeticException e) {
            a = 30;
            return a;
            /*
             * return a 在程序执行到这一步的时候，这里不是return a 而是 return 30；这个返回路径就形成了
             * 但是呢，它发现后面还有finally，所以继续执行finally的内容，a=40
             * 再次回到以前的路径,继续走return 30，形成返回路径之后，这里的a就不是a变量了，而是常量30
             */
        } finally {
            System.out.println("finally");
            a = 40;
            //return a; //如果这样，就又重新形成了一条返回路径，由于只能通过1个return返回，所以这里直接返回40
        }

        return a;
    }

}
