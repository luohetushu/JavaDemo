package com.mytest.utils;

public class Outer {
    int a = 0;    //实例变量
    static int b = 0;    //静态变量

    static class Inner {
        //静态内部类
        int a = 0;    //实例变量a
        static int b = 0;    //静态变量 b

        Outer o = new Outer();
        int a2 = o.a;    //访问实例变量
        int b2 = b;    //访问静态变量
        int b3 = Outer.b;    //访问外部类静态变量
    }
}
