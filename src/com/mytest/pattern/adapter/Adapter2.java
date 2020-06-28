package com.mytest.pattern.adapter;

/**
 * @Author murongyunge
 * @Describe  对象适配器模式：适配器与适配者之间是关联关系
 * @Date 2019-12-09
 */
public class Adapter2 implements TargetIn {

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.adapteeRequest();
    }
}
