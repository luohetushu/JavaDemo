package com.mytest.vacancy;

import com.mytest.utils.MathUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mytest.utils.MathUtils.*;  //静态导入

public class Iterate {
    public static void main(String[] args) {
        Iterate ite = new Iterate();

        //
        ite.objectDemo();

        //泛型类实例化
//        NorMessage<Integer> msg1 = new NorMessage<>();
//        msg1.setContent(10);
//        ite.transmitGeneric(msg1); //泛型对象传递 泛型通配符"?"
//        NorMessage<String> msg2 = new NorMessage<>();
//        msg2.setContent("www.baidu.com");
//        ite.transmitGeneric(msg2);

        IMessage<String> iMessage = new IMessage<String>() {
            @Override
            public void send(String msg) {
                System.out.println("匿名内部类：发送消息--" + msg);
            }
        };  //匿名内部类
        iMessage.send("匿名内部类：如何换成 Lambda 表达式呢");
        //等同于
        //Lambda 表达式
        IMessage<String> iMessage1 = msg -> System.out.println("Lambda 表达式：发送消息--" + msg);
        iMessage1.send("Lambda 表达式：面向对象设计换成 Lambda 表达式");

        //工程模式
        IMessage<String> msg3 = new MessageFactory().getInstance("NorMessage", "nor");
        msg3.send("hha");
        NorMessage<String> norMessage = (NorMessage<String>) msg3;
        norMessage.setContent("www");
        ite.transmitGeneric(norMessage);

        //泛型方法
        Integer[] num = ite.fun(1, 2, 3);
        for (int temp : num) {
            System.out.print(temp + "、");
        }
        System.out.println();

        //静态包导入 静态方法 //主方法直接调用静态方法
        ite.importStatic();

        //枚举类
        System.out.println(Color.valueOf("GREEN").ordinal()); //1
        //Color.valueOf("yellow").ordinal(); //java.lang.IllegalArgumentException
        for (Color color :
                Color.values()) {
            System.out.println(color);
        }

        //静态内部接口
        //非 static 内部类实例化对象格式：Outer.Inner i = new Outer().new Inner();    //需要创建外部类实例
        IMessageWrap.send(ite.new DefaultMessage(), ite.new DefaultChannel());

        //方法内部类
        ite.new OuterFun().send("加油啊，少年！！");

        List<String> lists = new ArrayList<>();
        System.out.println(lists.contains(null));

    }

    private void objectDemo(){
        //object 类可以接收所有的类，包括数组
        Object obj = new int[]{1, 2, 3};  //向上转型
        if (obj instanceof int[]){
            int[] data = (int[]) obj; //向下转型
            for (int num : data) {
                System.out.print(num + "、");
            }
            System.out.println();
        }
    }

    //泛型类对象传递
    //泛型通配符"?"：方法里只允许获取，不允许修改
    private void transmitGeneric(NorMessage<?> msg){
        System.out.println(msg.getContent());
    }

    //泛型方法
    private <T> T[] fun(T... args){
        return args;
    }

    //静态包导入 静态方法
    private void importStatic(){
        try {
            System.out.println(add(10, 20, 30));  //主方法直接调用静态方法
            //throw new Exception("可能出现算法错误异常");
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(sub(20, 10));
        try {
            System.out.println(adv(20, 5));
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println(div(10, 2));
        } catch (Exception e){
            e.printStackTrace();
        }

        //四舍五入
        System.out.println(MathUtils.round(20.5345, 3));
        //四舍五入
        System.out.println(MathUtils.round(39.6473, 2));

    }

    private static void dataDemo(){
        //静态初始化数组：方法二
        //String dogs[3] = {"Jimmy","Gougou","Doggy"}; //error 一个数组的大小将在数组使用new关键字真正创建时被给定

        Integer i1 = 200;
        Integer i2 = 200;
        System.out.println(i1 == i2);  // false 值在(-128,128]范围外，会执行new Integer(200)，对象不同
        Integer i3 = new Integer(100);
        Integer i4 = new Integer(100);
        System.out.println(i3 == i4);  //false

        Iterate iterate1 = new Iterate();
        Iterate iterate2 = new Iterate();
        System.out.println(iterate1.equals(iterate2));  //没有覆写equals，比较地址，false

        //浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals 来判断
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false
        //使用使用 BigDecimal 来定义浮点数的值，再进行浮点数的运算操作
        BigDecimal bd1 = new BigDecimal("1.0");
        BigDecimal bd2 = new BigDecimal("0.9");
        BigDecimal bd3 = new BigDecimal("0.8");
        BigDecimal x = bd1.subtract(bd2);// 0.1
        BigDecimal y = bd2.subtract(bd3);// 0.1
        System.out.println(x.equals(y));// true
        System.out.println(bd1.compareTo(bd2)); // 1  返回 -1 表示小于，0 表示 等于， 1表示 大于

        int[] myArray = { 1, 2, 3 };
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size()); //1
        System.out.println(myList.get(0)); //数组地址值 [I@50cbc42f

        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3"));
        list.removeIf(item -> item.equals("1"));
    }

    //泛型的获取数组最小值的函数
    //注意：Number并没有实现Comparable
    private static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (values == null || values.length == 0) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) min = values[i];
        }
        return min;
    }

    //内部静态接口
    class DefaultMessage implements IMessageWrap.IMessage{

        @Override
        public String getContent() {
            return "发送消息～～";
        }
    }

    class DefaultChannel implements IMessageWrap.IChannel{
        @Override
        public boolean isConnect() {
            return true;
        }
    }

    //方法内部类
    class OuterFun {
        private String message = "外部类私有属性";

        public void send(final String info){ //JDK 1.8 以前需要加 final 才能在内部类中调用
            final long time = 1034872L; //JDK 1.8 以前需要加 final 才能在内部类中调用
            class Inner {
                public void print(){
                    System.out.println(OuterFun.this.message); //外部类私有属性
                    System.out.println("外部类方法参数：" + info);
                    System.out.println("外部类方法中属性：" + time);
                }
            }
            new Inner().print(); //实例化内部类
        }

    }

}