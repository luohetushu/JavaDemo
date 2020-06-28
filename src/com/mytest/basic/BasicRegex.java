package com.mytest.basic;

import java.util.regex.*;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-30
 */
public class BasicRegex {
    public static void main(String[] args) {
        String hello = "hempo, new world";
        System.out.println(hello.replaceAll("[a-z&&[mp]]", "l")); //hello

        String str = "hello,java!";
        // 贪婪模式的正则表达式  w*:该字符串前面的所有单词字符都被它匹配到，直到遇到","
        System.out.println(str.replaceFirst("\\w*" , "■"));    //输出■,java!
        // 勉强模式的正则表达式  使用勉强模式，数量表示符*会尽量匹配最少字符，即匹配 0 个字符
        System.out.println(str.replaceFirst("\\w*?" , "■"));   //■hello,java!

        // 使用字符串模拟从网络上得到的网页源码
        String netStr = "我想找一套适合自己的JAVA教程，尽快联系我13500006666；交朋友，电话号码是13611125565；出售二手电脑，联系方式15899903312";
        // 创建一个Pattern对象，并用它建立一个Matcher对象
        // 该正则表达式只抓取13X和15X段的手机号 实际要抓取哪些电话号码，只要修改正则表达式即可
        Matcher netMat = Pattern.compile("((13\\d)|(15\\d))\\d{8}").matcher(netStr);
        // 将所有符合正则表达式的子串（电话号码）全部输出
        while (netMat.find()) {
            System.out.println(netMat.group());
        }

        String strDemo = "$#dcdfv$%12dfg5$#ddf#2";
        String regexDemo = "[^a-zA-Z0-9]+";
        System.out.println(strDemo.replaceAll(regexDemo, "")); //dcdfv12dfg5ddf2
        String regexSplit = "\\d+";
        String[] resultSplit = strDemo.split(regexSplit);
        for (String s : resultSplit) {
            System.out.print(s + "、"); //$#dcdfv$%、dfg、$#ddf#、
        }
        System.out.println();

        // 创建一个Pattern对象，并用它建立一个Matcher对象
        String regStr = "Java is very easy!";
        System.out.println("目标字符串是：" + regStr);
        Pattern pat = Pattern.compile("\\w+");
        Matcher wMat = pat.matcher(regStr);
        while (wMat.find()) {
            System.out.println(wMat.group() + "子串的起始位置：" + wMat.start() + "，其结束位置：" + wMat.end());
        }

        String[] mails = { "kongyeeku@163.com", "kongyeeku@gmail.com", "ligang@crazyit.org", "wawa@abc.xx" };
        String mailRegEx = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
        Pattern mailPattern = Pattern.compile(mailRegEx);
        Matcher matcher = null;
        for (String mail : mails) {
            if (matcher == null) {
                matcher = mailPattern.matcher(mail);
            } else {
                matcher.reset(mail);
            }
            String result = mail + (matcher.matches() ? "是" : "不是") + "一个有效的邮件地址！";
            System.out.println(result);
        }

        String[] msgs = { "Java has regular expressions in 1.4", "regular expressions now expressing in Java",
                "Java represses ore oracular expressions" };
        Pattern p = Pattern.compile("re\\w*");  //“re”之后空格之前的单词
        Matcher repMat = null;
        for (int i = 0; i < msgs.length; i++) {
            if (repMat == null) {
                repMat = p.matcher(msgs[i]);
            } else {
                repMat.reset(msgs[i]);
            }
            //把字符串中所有与正则表达式匹配的子串替换成“哈哈:)”
            System.out.println(repMat.replaceAll("哈哈:)"));
        }

    }
}
