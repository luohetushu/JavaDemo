package com.mytest.demo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-26
 */
public class BaseDemo {
    public static void main(String[] args) {
        //机票价格
        //planeTicketDemo();

        //使用 break 语句实现 goto 的功能（break 带标签）
        //breakDemo();

        //强制外循环执行下一次迭代（continue 带标签）
        //continueDemo();

        //字符串替换
        //replaceDemo();

        //正则表达式验证电话号码
        //regexPhoneDemo();

        //正则表达式验证电话号码
        //regexIPDemo();

        //打印当前月份日历
        //printCalendar();

        //从键盘输入字符并显示出来
        //inputAndPrint();

        //输出杨辉三角形
        //printPascalTriangle();

        System.out.print("\"身边人，明天可能就要远去；远游人，明天可能就要还乡；心上人，明天可能就要。。。。\b\"\n");

        int[] myArray = {1, 2, 3, 4, 5};
        LowHighSwap.doIt(myArray);
        for (int value : myArray) {
            System.out.print(value + " ");
        }

        String x = null;
        LowHighSwap.giveMeAString(x);
        System.out.println(x);

        Integer i = Integer.valueOf(10);
        Integer j = Integer.valueOf(20);
        LowHighSwap.swap(i, j);
        System.out.println("i = " + i + ", j = " + j);

        String obj1 = new String("runoob");
        String obj2 = new String("runoob");

        if(obj1.hashCode() == obj2.hashCode())
            System.out.println("object1 与 object2 哈希码相等");

        if(obj1 == obj2)
            System.out.println("object1 与 object2 内存地址一样");

        if(obj1.equals(obj2))
            System.out.println("object1 与 object2 值相等");

        //实现资源自动关闭
        //要想实现资源关闭，除了实现 AutoCloseable 接口，还需要结合异常处理语句（try catch）才行
        try (IResource ir = new NetResource("Rider OOO")) {
            ir.send();
        } catch (Exception e){
            e.printStackTrace();
        }



    }

    private static void planeTicketDemo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入出行的月份：");
        int month = sc.nextInt();
        while (month < 1 || month > 12){
            System.out.println("日期选择有误，请重新输入:");
            month = sc.nextInt();
        }
        System.out.println("选择头等舱还是经济舱？数字1为头等舱，数字2为经济舱");
        int kind = sc.nextInt();
        while (kind != 1 && kind != 2){
            System.out.println("选择种类有误，请重新选择：数字1为头等舱，数字2为经济舱");
            kind = sc.nextInt();
        }
        double result = 60000; // 原始价格
        // 旺季的票价计算
        if (month <= 11 && month >= 4) {
            if (kind == 1) { // 旺季头等舱
                result = result * 0.9;
            } else { // 旺季经济舱
                result = result * 0.8;
            }
        }
        // 淡季的票价计算 (month >= 1 && month <= 3) || month == 12
        else {
            if (kind == 1) { // 淡季头等舱
                result = result * 0.5;
            } else { // 淡季经济舱
                result = result * 0.4;
            }
        }
        System.out.println("您选择的机票价格为：" + result);
    }

    /**
     * 使用 break 语句实现 goto 的功能（break 带标签）
     */
    private static void breakDemo(){

        /**
         * label 是标签的名称，可以为 Java 语言中任意合法的标识符
         * 标签语句必须和循环匹配使用，使用时书写在对应的循环语句的上面，标签语句以冒号结束
         */
        label:for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(j);
                if (j % 2 != 0) {
                    break label;
                }
            }
        }
    }

    /**
     * 强制外循环执行下一次迭代（continue 带标签）
     */
    private static void continueDemo(){
        label:
        for (int x = 0; x < 5; x++) {
            for (int y = 5; y > 0; y--) {
                if (y == x) {
                    continue label;
                }
                System.out.println(x + "," + y);
            }
        }
        System.out.println("Game Over!");
    }

    private static void replaceDemo() {
        // 定义原始字符串
        String intro = "今天时星其天，外面时下雨天。妈米去买菜了，漏网在家写作业。" + "语文作业时”其”写 5 行，数学使第 10 页。";
        // 将文本中的所有"时"和"使"都替换为"是"
        String newStrFirst = intro.replaceAll("[时使]", "是");
        // 将文本中的所有"妈米"改为"妈妈"
        String newStrSecond = newStrFirst.replaceAll("妈米", "妈妈");
        // 将文本中的所有"漏网"改为"留我"
        String newStrThird = newStrSecond.replaceAll("漏网", "留我");
        // 将文本中第一次出现的"其"改为"期"
        String newStrFourth = newStrThird.replaceFirst("[其]", "期");
        // 输出最终字符串
        System.out.println(newStrFourth);
    }

    //正则表达式验证电话号码
    private static void regexPhoneDemo(){
        //String regex = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";
        //^	行的开头    $	行的结尾
        String regex = "^([+]?86)?((13\\d)|(14[579])|(15([0-3]|[5-9]))|(166)|(17[[0-9]&&[^249]])|(18[0-9])|(19[8|9]))\\d{8}$";
        String answer;
        do {
            System.out.print("请留下您的电话号码：");
            Scanner scan = new Scanner(System.in);
            String phone = scan.next();    // 接收用户在控制台输入的电话号码
            Pattern pattern = Pattern.compile(regex);    // 编译正则表达式
            Matcher matcher = pattern.matcher(phone);    // 创建给定输入模式的匹配器
            if(matcher.matches()) { // 如果验证通过
                System.out.println("输入的电话号码格式正确。");
                answer = "N";
            } else {
                System.out.println("输入的电话号码无效，格式不正确。");
                System.out.print("是否继续输入？（Y/N 或者 y/n）");
                answer = scan.next();
            }
        }while(answer.equalsIgnoreCase("Y"));
        System.out.println("注册结束。");
    }

    //正则表达式验证IP地址
    private static void regexIPDemo(){
        String regex = "(([0-1]?[0-9]?[0-9])|(2[0-5][0-5]))\\.(([0-1]?[0-9]?[0-9])|(2[0-5][0-5]))\\." +
                "(([0-1]?[0-9]?[0-9])|(2[0-5][0-5]))\\.(([0-1]?[0-9]?[0-9])|(2[0-5][0-5]))";
        String answer;
        do {
            System.out.print("请输入 IP 地址：");
            Scanner scan = new Scanner(System.in);
            String phone = scan.next();    // 接收用户在控制台输入的电话号码
            Pattern pattern = Pattern.compile(regex);    // 编译正则表达式
            Matcher matcher = pattern.matcher(phone);    // 创建给定输入模式的匹配器
            boolean bool = matcher.matches();
            if(bool) {   // 如果验证通过
                System.out.println("输入的 IP 地址正确。");
                answer = "N";
            } else {
                System.out.println("输入的IP地址格式错误。");
                System.out.print("是否继续输入？（Y/N 或者 y/n)");
                answer = scan.next();
            }

        }while(answer.equalsIgnoreCase("Y"));
        System.out.println("程序结束。");
    }

    //打印当前月份日历
    private static void printCalendar(){
        char[] title = {'日','一','二','三','四','五','六'};    //存放曰历的头部
        int[][] days = new int[6][7];//存放日历的数据
        int day = 1;  //月天数，累加

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;  //当月1号是一周中第几天
        System.out.println("day of week:" + dayOfWeek);

        //填充日历第一行
        for (int i = dayOfWeek; i < 7; i++) {
            days[0][i] = day++;
        }
        //当月总天数
        int daysOfMonth = getDaysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
        System.out.println("days of month:" + daysOfMonth);
        //填充剩下行
        label: for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (day > daysOfMonth){
                    break label;
                }
                days[i][j] = day++;
            }
        }

        //打印当月日历
        //头部
        for (int i = 0; i < title.length; i++) {
            System.out.print(title[i] + "\t");
        }
        System.out.print("\n");
        //月天数
        label: for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (days[i][j] == 0){
                    if (i != 0){
                        break label;
                    }
                    System.out.print("\t");
                    continue;
                }
                System.out.print(days[i][j] + "\t");

            }
            System.out.print("\n");
        }

    }

    //获取当月总天数
    private static int getDaysOfMonth(int year, int month){
        int days;

        if (month == 2){
            if (year % 100 == 0){
                if (year % 400 == 0){
                    days = 29;
                } else {
                    days = 28;
                }
            } else {
                if (year % 4 == 0){
                    days = 29;
                } else {
                    days = 28;
                }
            }

        } else if (month <= 7){
            days = month % 2 == 1 ? 31 : 30;
        } else {
            days = month % 2 == 0 ? 31 : 30;
        }

        return days;
    }

    //从键盘输入字符并显示出来
    private static void inputAndPrint(){
        System.out.println("请输入字符，按回车键结束输入:");
        int ch;
        try {
            //ch = System.in.read();    //读取输入的字符
            InputStreamReader in = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            ch = in.read();
            while(ch != '\r') {    //判断输入的字符是不是回车
                System.out.print((char) ch);    //输出字符
                //ch = System.in.read();
                ch = in.read();
                //break;

            }
        } catch(IOException e) {
            System.out.println(e.toString());
        } finally {
            System.err.println();
        }
    }

    //输出杨辉三角形
    private static void printPascalTriangle(){
        //行数
        int row = 8;
        // 根据行数定义好二维数组，由于每一行的元素个数不同，所以不定义每一行的个数
        int[][] arr = new int[row][];
        // 遍历二维数组
        for (int i = 0; i < row; i++) {
            // 初始化每一行的这个一维数组
            arr[i] = new int[i + 1];
            for (int j = 1; j <= row - i; j++) {
                System.out.print(" ");
            }
            // 遍历这个一维数组，添加元素
            for (int j = 0; j <= i; j++) {
                // 每一列的开头和结尾元素为1，开头的时候，j=0，结尾的时候，j=i
                if (j == 0 || j == i) {
                    arr[i][j] = 1;
                } else {
                    // 每一个元素是它上一行的元素和斜对角元素之和
                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class LowHighSwap {
        static void doIt( int[] z ) {
//            int temp = z[z.length-1];
//            z[z.length-1] = z[0];
//            z[0] = temp;

            int[] A = z;
            A[0] = 99;

            //z = null;
        }

        static void giveMeAString(String x) {
            String y = x;
            y = "RUNOOB";
        }

        public static void swap(Integer i, Integer j) {
            Integer temp = Integer.valueOf(i);
            i = j;
            j = temp;
        }

    }

}
