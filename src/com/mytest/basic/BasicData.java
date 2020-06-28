package com.mytest.basic;

import java.util.Scanner;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-25
 */
public class BasicData {
    public static void main(String[] args) {

        int i = 50;
        System.out.println(i++ * 2); //先取值再加一 100
        System.out.println(++i * 2); //先加一再取值 104

        //boolean flag = 10 % 2 == 1 & 10 / 3 == 0 & 1 / 0 == 0;  // java.lang.ArithmeticException: / by zero
        boolean flag = 10 % 2 == 1 && 10 / 3 == 0 && 1 / 0 == 0;  //程序正常运行
        System.out.println(flag ? "makn" : "yookn");

        int num = 48;
        char ch = (char) num;    //A 65  a 97
        System.out.println(ch);  //0

        char c0 = 65;
        System.out.println("c = " + c0);  //c = A

        int int1 = 2147483647;
        System.out.println(int1 + 1); //-2147483648
        short short1 = 32767;
        System.out.println(short1 + 1); //32768
        System.out.println((short)(short1 + 1)); //-32768
        System.out.printf("int1=%x, short1=%x\n", int1, short1); //0x7fffffff  0x7fff

        System.out.println("here is tui ge\b"); //here is tui g

        /**
         * Math.floor:求一个浮点数的地板，向下 求一个最接近它的整数，它的值肯定会小于或等于这个浮点数 Math.floor(-1.5): -2.0
         * Math.ceil:向上 取接近的整数，它返回的肯定会大于或等于函数参数 Math.ceil(-1.5): -1.0
         * Math.rint:返回最接近参数的整数，如果有2个数同样接近，则会返回偶数的那个 Math.rint(-1.5): -2.0
         * Math.round(x) = (int) Math.floor(x + 0.5)  Math.round(-1.5): -1
         */
        int moveNum = -11;
        int movePow = 3;
        System.out.println(moveNum << movePow);  //-88
        System.out.println((int) (moveNum * Math.pow(2, movePow))); //-88
        System.out.println(moveNum >>> movePow);   //536870910
        System.out.println(moveNum >> movePow);   //-2
        System.out.println((int) Math.floor(moveNum / Math.pow(2, movePow)));  //-2

        int a = 5;
        int b = 4;
        int c = a++ - --b * ++a / b-- >> 2 % a--;
        System.out.println(c);  //-1


//        int number1; // 定义变量，保存输入的数
//        System.out.print("请输入一个整数(number1)：");
//        Scanner input = new Scanner(System.in);
//        number1 = input.nextInt(); // 输入第一个数
//        System.out.printf("number1=%d\n", number1); // 输出这个数

        String s1 = "runoob";
        String s2 = "runoob";
        s1.hashCode();
        System.out.println("s1 == s2 is:" + s1 == s2);
        System.out.println(("s1 == s2 is:" + s1).equals(s2));  //false

        String str1 = "通话";
        String str2 = "重地";
        System.out.println(String.format("str1：%d | str2：%d",  str1.hashCode(),str2.hashCode()));

        System.out.println((int) (-1.6 + 0.5));
        System.out.println((int) Math.floor(-1.6 + 0.5));

    }

    abstract static class Cat {
        public static void sayHi() {
            System.out.println("hi~");
        }
    }


}
