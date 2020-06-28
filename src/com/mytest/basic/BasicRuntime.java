package com.mytest.basic;

public class BasicRuntime {

    public static void main(String[] args){

        Runtime run = Runtime.getRuntime(); //获取实例化对象

        //public native int availableProcessors(); 获取本机的 CPU 内核数
        System.out.println(run.availableProcessors()); //获取本机的 CPU 内核数

        //public native long maxMemory(); 获取最大可用内存空间：默认为系统配置内存的 4 分之 1
        System.out.println(run.maxMemory());

        //public native long totalMemory(); 获取可用内存空间：默认为系统配置内存的 64 分之 1
        System.out.println(run.totalMemory());

        //public native long freeMemory(); 获取空闲内存空间
        System.out.println(run.freeMemory());

        //public native void gc(); 手动进行 GC 处理
        //注意：调用 System.gc() 或者 Runtime.gc() 方法也不能保证回收操作一定执行，它只是提高了 Java 垃圾回收器尽快回收垃圾的可能性
        //run.gc();
    }

}
