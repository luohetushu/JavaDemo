package com.mytest.basic;

import java.io.IOException;
import java.io.InputStream;

public class BasicSystem {

    /**
     * 系统类：java.lang.System
     *        public final class System{}
     *        常量：1、标准输入（键盘）：public static final InputStream in;
     *             2、标准输出（控制台）：public static final PrintStream out;
     *             3、错误输出：public static final PrintStream err;
     * @param args
     */
    //System;

    public static void main(String[] args) {
        System.err.println("Hah");

        try {
            //键盘输入
            InputStream is = System.in;
            System.out.print("请输入任意数据：");
            byte[] data = new byte[1024];
            int len = is.read(data);
            System.out.println("键盘输入的数据为：" + new String(data, 0, len));
            is.close();
        } catch (IOException e){
            e.printStackTrace();
        }



    }
}
