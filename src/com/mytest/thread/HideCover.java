package com.mytest.thread;

import java.util.Objects;

public class HideCover {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //在生成对象的过程中，会先初始化对象的成员变量，然后再执行构造器
        //父类的构造器调用以及初始化过程一定在子类的前面
        Shape shape = new Circle();  //向上转型
        //java.lang.Class shapeClass = java.lang.Class.forName("com.mytest.thread.Circle");
        //Shape shape = (Circle) shapeClass.newInstance();
        //隐藏是针对成员变量和静态方法的，而覆盖是针对普通方法的
        System.out.println(shape.name); //隐藏 shape
        shape.printType(); //覆盖  this is circle
        Shape.printName();  //隐藏 shape

        System.out.println(shape); //等同于 System.out.println(shape.toString());


    }


}

class Shape {
    public String name = "shape";

    public Shape(){
        System.out.println("shape constructor");
    }

    public Shape(String name) {
        this.name = name;
    }

    public void printType() {
        System.out.println("this is shape");
    }

    public static void printName() {
        System.out.println("shape");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!(o instanceof Shape)) return false;
        Shape shape = (Shape) o;
        return Objects.equals(name, shape.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Circle extends Shape {
    public static String name = "circle";

    public Circle() {
        System.out.println("circle constructor");
    }

    public Circle(String name){
        super(name);
    }

    public void printType() {
        System.out.println("this is circle");
    } //覆写

    public static void printName() {
        System.out.println(name);
    }

    final void think(){

    }
}

//public class HideCover {
//    String name; // 成员变量、实例变量
//    int age; // 成员变量、实例变量
//    static final String website = "C语言中文网"; // 成员变量、静态变量(类变量)
//    static String URL = "http://c.biancheng.net"; // 成员变量、静态变量(类变量)
//}


