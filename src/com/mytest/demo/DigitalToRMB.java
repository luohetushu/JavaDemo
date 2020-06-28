package com.mytest.demo;

import java.util.Arrays;

/**
 * @Author murongyunge
 * @Describe  数字转人民币书写
 * @Date 2019-11-08
 */
public class DigitalToRMB {

    private static String[] han_list = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static String[] unit_list = new String[]{"十", "百", "千"};

    public static void main(String[] args) {
        double money = 200007.08;

        String[] divide = divide(money);
        System.out.println("分解成整数部分和小数部分结果为：" + Arrays.toString(divide));
        System.out.printf("人民币读法为：%s元%s", integerToHan(divide[0]), fractionToHan(divide[1]));

    }

    /**
     * 把一个浮点数分解成整数部分和小数部分字符串
     * @param num
     * @return 返回分解出来的整数部分和小数部分。
     * 第一个数组元素是整数部分，第二个数组元素是小数部分
     */
    private static String[] divide(double num){
        long integer = (long) num;
        long fraction = Math.round((num - integer) * 100);
        if (fraction == 100){
            integer += 1;
            fraction = 0;
        }
        return new String[]{String.valueOf(integer), String.valueOf(fraction)};
    }

    /**
     * 把一个浮点数整数部分转化成中文RMB书写
     * @param integer
     * @return
     */
    private static String integerToHan(String integer){
        int int_len = integer.length();
        if (int_len > 12){
            System.out.println("大于12位的数字暂时不转化了");
            return "****";
        } else if (int_len > 8){  // 如果大于8位，包含单位亿
            String wan_str = numToHan(integer.substring(int_len - 8, int_len - 4));
            String ge_str = numToHan(integer.substring(int_len - 4));
            if (wan_str.isEmpty()){
                return numToHan(integer.substring(0, int_len - 8)) + "亿" + ge_str;
            } else {
                return numToHan(integer.substring(0, int_len - 8)) + "亿" + wan_str + "万" + ge_str;
            }
        } else if (int_len > 4){  // 如果大于4位，包含单位万
            String ge_str = numToHan(integer.substring(int_len - 4));
            return numToHan(integer.substring(0, int_len - 4)) + "万" + ge_str;
        } else {
            return numToHan(integer).isEmpty() ? "零" : (numToHan(integer).equals("壹十") ? "十" : numToHan(integer));
        }
    }

    /**
     * 把一个浮点数小数部分转化成中文RMB书写
     * @param fraction
     */
    private static String fractionToHan(String fraction){
        if (fraction.length() > 2){
            System.out.println("小数部分位数不对");
            return "**";
        } else if (fraction.length() == 1){
            fraction = "0" + fraction;
        }
        if (fraction.charAt(1) == '0') {
            return han_list[fraction.charAt(0) - '0'] + "角";
        } else {
            return han_list[fraction.charAt(0) - '0'] + "角" + han_list[fraction.charAt(1) - '0'] + "分";
        }
    }



    /**
     * 把数字字符串变成汉字字符串
     * @param num_str
     * @return 返回汉字字符串
     */
    private static String numToHan(String num_str){
        StringBuilder stringBuilder = new StringBuilder();

        int num_len = num_str.length();
        int zero_count = 0;
        for (int i = 0; i < num_len; i++) {
            // 把char型数字转换成int型数字，3 种方法如下：
            // 方法1：因为它们的ASCII码值恰好相差48，因此把char型数字减去48得到int型数字，例如‘4’被转换成4
            // int num = str.charAt(i) - 48;
            // 方法2：先将char型数字转换为String字符串，再用Integer（int的包装类，提供了String转换为int的方法，后面教程中我们会详细讲解）转换为int型数字
            // int num = Integer.parseInt(String.valueOf(str.charAt(i)));
            // 方法3：char型数字减去字符0来转换为int型数字
            int num = num_str.charAt(i) - '0';
            if (num == 0){
                zero_count++;
            }
            if ((i != num_len - 1) && num != 0){
                // 如果不是最后一位数字，而且数字不是零，则需要添加单位（千、百、十）
                stringBuilder.append(han_list[num]).append(unit_list[num_len - 2 - i]);
            } else {
                //否则不要添加单位
                stringBuilder.append(han_list[num]);
            }
        }

        String result = stringBuilder.toString();
        System.out.println("去零前：" + result);
        if (result.charAt(result.length() - 1) == '零'){
            do {
                result = result.substring(0, result.length() - 1); //移除末尾零
                zero_count--;
            } while (result.length() > 0 && result.charAt(result.length() - 1) == '零');
        }
        if (zero_count == 3){
            result.replace("零零零", "零");
        }
        if (zero_count == 2){
            result.replace("零零", "零");
        }
        System.out.println("去零后：" + result);

        return result;
    }



}
