package com.mytest.utils;

//自定义异常
public class ExceptionUtils extends RuntimeException{
    public ExceptionUtils(String msg){
        super(msg);
    }
}
