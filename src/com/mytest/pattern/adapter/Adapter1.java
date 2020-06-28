package com.mytest.pattern.adapter;

/**
 * @Author murongyunge
 * @Describe  类适配器模式：  适配器与适配者之间是继承关系
 * @Date 2019-12-09
 */
public class Adapter1 extends Adaptee implements TargetIn {
    @Override
    public void request() {
        super.adapteeRequest();
    }
}
