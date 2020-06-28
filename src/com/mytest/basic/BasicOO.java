package com.mytest.basic;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-12
 */
class BasicOO {
    public static void main(String[] args){
        BasicOO basicOO = new BasicOO();
        /**
         * Object 类具有一个 toString() 方法，该方法是个特殊的方法，创建的每个类都会继承该方法，它返回一个 String 类型的字符串。
         * 如果一个类中定义了该方法，则在调用该类对象时，将会自动调用该类对象的 toString() 方法返回一个字符串
         */
        System.out.println(basicOO);  //所求者小，所谋者大

        //方法的可变参数
        basicOO.printName("Angel", "Anna", "Alyssa", "Allen", "Andy");
        //basicOO.printName("Kiva", "Decade", "W", "OOO", "Fourze", "Wizard", "Drive", "Ghost", "Ex-Aid", "Build");


        //basicOO = null;
        //由于 finalize() 方法的不确定性，所以在程序中可以调用 System.gc() 或者 Runtime.gc() 方法提示垃圾回收器尽快执行垃圾回收操作
        //System.gc();    // 清理内存

        basicOO.method();

    }

    //方法的可变参数
    private void printName(String... names){
        System.out.printf("本次记录有%d人，名单如下\n", names.length);
        for(int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }

    protected void finalize() {
        // 析构方法
        System.out.println("对象销毁：惜之惜之，奈何奈何");
    }

    @Override
    public String toString() {
        return "所求者小，所谋者大";
    }

    int a = 10;
    int d = 50;
    public void method() {
        int b = 20;
        final int c = 30;
        final int d = 40;
        class Inner {
            int a2 = a;    //访问外部类中的成员
            int b2 = b;    //访问方法中的成员
            int c2 = c;    //访问方法中的成员
            int d2 = d;    //访问方法中的成员
            int d3 = BasicOO.this.d;    //访问外部类中的成员
        }
        Inner i = new Inner();
        System.out.println(i.b2);
        System.out.println(i.d2);    //输出10
        System.out.println(i.d3);    //输出0
    }

}
