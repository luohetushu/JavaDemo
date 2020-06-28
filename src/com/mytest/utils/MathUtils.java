package com.mytest.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class MathUtils {

    protected double PI = 3.1415926;  //在主方法中，protected 属性只能通过子类去调用

    private MathUtils(){}

    public static int add(int ... args){
        int sum = 0;
        for (int temp : args) {
            sum += temp;
        }
        return sum;
    }

    public static int sub(int x, int y){
        return (x - y);
    }

    public static int adv(int x, int y) throws ExceptionUtils{
        int ans = x * y;
        if (ans > 100){
            throw new ExceptionUtils("数太大了");
        }
        assert ans != 100 : "如果断言不成立，将抛出AssertionError:该引号内内容"; //断言程序并非必须执行
        return ans;
    }

    public static double div(int x, int y) throws ArithmeticException {
        double ans = 0;
        System.out.println("division start");
        try {
            ans = x / y;
        } catch (ArithmeticException e){
            throw e; //向上抛出异常
        } finally {
            System.out.println("division end");
        }
        return ans;
    }

    /**
     * 实现数据的四舍五入
     * @param num
     * @param scare 四舍五入保留的小数点位数
     * @return
     */
    public static double round(double num, int scare){
        return Math.round(num * Math.pow(10, scare)) / Math.pow(10, scare);
    }

    /**
     * 使用大数字类 BigDecimal 实现数据的四舍五入
     * @param num
     * @param scare 四舍五入保留的小数点位数
     * @return
     */
    public static double round2(double num, int scare){
        return new BigDecimal(num).divide(new BigDecimal(1.0), scare, RoundingMode.HALF_UP).doubleValue();
    }

    //BigInteger

}
