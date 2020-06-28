package com.mytest.basic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.*;
import java.util.*;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-31
 */
public class BasicNumber {
    public static void main(String[] args) {
        System.out.println(Math.floor(-1.6 + 0.5));
        System.out.println(Math.round(-1.6));
        System.out.println(Math.floor(1.1 + 0.5));
        System.out.println(Math.round(1.1));

        System.out.println(Math.log(Math.E + 1));
        System.out.println(Math.log1p(Math.E));

        System.out.println((int)(Math.random() * 100)); //返回[0, 100）中的随机数

        double pi = 3.1415926;
        System.out.println(new DecimalFormat("00.000").format(pi)); //03.142
        System.out.println(new DecimalFormat("##.###").format(pi)); //3.142
        System.out.println(new DecimalFormat("%##.###").format(pi)); //%314.159
        System.out.println(new DecimalFormat("%,##.###").format(pi)); //%3,14.159
        System.out.println(new DecimalFormat(",00.000%").format(pi)); //3,14.159%
        System.out.println(new DecimalFormat(",00.###%").format(pi)); //3,14.159%
        System.out.println(new DecimalFormat(",##.###%").format(pi)); //3,14.159%

        long speed = 299792459L;
        System.out.println(new DecimalFormat("00.000000E00").format(speed)); //29.979246E07
        System.out.println(new DecimalFormat("#.#######E0").format(speed)); //2.9979246E8
        System.out.println(new DecimalFormat(",000").format(speed)); //299,792,459
        System.out.println(new DecimalFormat(",###").format(speed)); //299,792,459
        //将格式嵌入文本
        System.out.println(new DecimalFormat("光速大小为每秒,###米").format(speed));  //光速大小为每秒299,792,459米

        int num = 100;
        BigInteger bi = new BigInteger(String.valueOf(num));
        System.out.printf("取商操作结果：%d, 取余操作结果：%d\n", bi.divideAndRemainder(new BigInteger("3"))[0],
                bi.divideAndRemainder(new BigInteger("3"))[1]);  //取商操作结果：33, 取余操作结果：1
        System.out.println(bi.equals(new BigInteger("125")));
        System.out.printf("左移操作结果：%d, 右移操作结果：%d\n", bi.shiftLeft(2), bi.shiftRight(2)); //左移操作结果：400, 右移操作结果：25

        BigDecimal bd = new BigDecimal(num);
        System.out.println("除法操作结果(保留 3 位小数)：" + bd.divide(new BigDecimal(3.14),3, BigDecimal.ROUND_CEILING));
        System.out.println("除法操作结果(保留 2 位小数)：" + bd.divide(new BigDecimal(3.14),2, BigDecimal.ROUND_HALF_EVEN));


        System.out.println("CST（中央标准时间）:" + new Date());
        System.out.println("CST（中央标准时间）:" + new Date(600000));  //CST（中央标准时间）:Thu Jan 01 08:10:00 CST 1970

        Calendar calendar = Calendar.getInstance();
        System.out.println("date=" + calendar.getTime());
        System.out.println("day of week in month:" + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("day of month:" + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("day of week:" + calendar.get(Calendar.DAY_OF_WEEK));
        calendar.set(2008, 8, 8);  //2008.09.08
        System.out.println("date=" + calendar.getTime());  //Mon Sep 08 09:31:08 CST 2008
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2008-08-08"));
            System.out.println("date=" + calendar.getTime());  //Fri Aug 08 00:00:00 CST 2008
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //获取不同格式化风格和中国环境的日期
        DateFormat df1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.CHINA);
        DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
        DateFormat df3 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
        DateFormat df4 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
        //将不同格式化风格的日期格式化为日期字符串
        String date1=df1.format(new Date());
        String date2=df2.format(new Date());
        String date3=df3.format(new Date());
        String date4=df4.format(new Date());
        //输出日期
        System.out.println("SHORT：" + date1);
        System.out.println("FULL：" + date2);
        System.out.println("MEDIUM：" + date3);
        System.out.println("LONG：" + date4);

        try {
            //指定格式化格式
            SimpleDateFormat fdf = new SimpleDateFormat("当前时间是 yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
            System.out.println(fdf.format(new Date()));    //将当前时间袼式化为指定的格式
            String cur = "当前时间是 2019 年 11 月 05 日 周二 14 点 38 分 44 秒";
            System.out.println(fdf.parse(cur));    //解析（文本→日期） Tue Nov 05 14:38:44 CST 2019
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Locale locale = new Locale("zh", "CN");
        System.out.println(locale); //zh_CN
        Locale locale1 = Locale.CHINA;
        System.out.println(locale1); //zh_CN
        Locale def = Locale.getDefault();  //获取默认环境
        System.out.println(def);  //zh_CN_#Hans

        //读取资源文件
//        ResourceBundle rb = ResourceBundle.getBundle("resource_zh_CN", locale);
//        String info = rb.getString("info");
//        //消息格式化
//        String result = MessageFormat.format(info,
//                new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "friends");
//        System.out.println(result);

        //UUID uuid = UUID.fromString("name"); //IllegalArgumentException: Invalid UUID string: name
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString()); //c6657494-170c-41ac-a208-138baf2bd8a9

        //Optional

        //ThreadLocal

        //TimerTask

    }
}
