package com.mytest.demo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-11
 */
public class GUIDemo {
    Frame f = new Frame("Event Test");
    TextField tf = new TextField(60);
    Button ok = new Button("OK");



    //ActionListener是Java中关于事件处理的一个接口，继承自EventListener。
    class OkListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tf.setText("Hello，girl!");
        }
    }

    private void init() {
        f.add(tf);//在Frame中添加框架
        f.add(ok, BorderLayout.NORTH);//按钮放置的位置
        ok.addActionListener(new OkListener());//OK按钮实现事件处理
        //ActionListener用于接收操作事件的侦听器接口。
        // 对处理操作事件感兴趣的类可以实现此接口，而使用该类创建的对象可使用组件的 addActionListener 方法向该组件注册。
        // 在发生操作事件时，调用该对象的 actionPerformed 方法。
        f.pack();//根据放置的组件设定窗口的大小，使之正好能容纳放置的所有组件
        f.setVisible(true);//显示
    }
    public static void main(String[] args) {
        new GUIDemo().init();
    }
}
