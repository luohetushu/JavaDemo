package com.mytest.basic;

import java.util.Arrays;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-28
 */
public class BasicString {
    public static void main(String[] args) {
        char a[] = {'H','e','l','l','o'};
        String sChar = new String(a); //hello
        String tChar = new String(a, 1, 3); //ell
        System.out.println("sChar=" + sChar + ",tChar=" + tChar);
        System.out.println(sChar.charAt(1)); //'e'
        System.out.println(sChar.codePointAt(1)); //101  e 在 Unicode 编码表中对应的代码值
        System.out.println(sChar.offsetByCodePoints(1, 3));  //4（1+3）

        System.out.println(sChar.compareTo("h"));  //-32（72-104）  H 72 h 104
        System.out.println(sChar.compareToIgnoreCase("h")); //4
        System.out.println(sChar.compareTo("Hel"));  //2
        System.out.println(sChar.compareTo("Hl")); //-7(101-108)   e 101 l 108
        System.out.println(sChar.compareTo("world"));  //-47（72-119）  H 72 w 119

        System.out.println(Arrays.toString(sChar.chars().toArray())); //[72, 101, 108, 108, 111]
        System.out.println(Arrays.toString(sChar.codePoints().toArray())); //[72, 101, 108, 108, 111]

        int[] points = new int[]{100, 104, 101, 108, 108, 111};
        String hChar = new String(points, 1, 5);   //hello (h 104 e 101 l 108 l 108 o 111)
        System.out.println(hChar);

        System.out.println(sChar.equals(hChar));  //false
        System.out.println(sChar.equalsIgnoreCase(hChar));  //true（忽略大小写）

        System.out.println(hChar.indexOf(108));  //2
        System.out.println(hChar.lastIndexOf(108, 3));  //3
        System.out.println(hChar.lastIndexOf(108, 1));  //-1  往1之前查找，不存在故返回-1

        System.out.println(sChar.codePointCount(2, 5));  // 3 5-1-2+1

        String num = "442334.43.";
        System.out.println(num.substring(0, num.length() - 8));
        System.out.println(num.substring(num.length() - 8, num.length() - 4));
        System.out.println(num.substring(num.length() - 4));

        String[] delimiters = new String[]{"w", "o", "r", "l", "d"};
        String jChar = String.join("、", delimiters);
        System.out.println(jChar);  //w、o、r、l、d
        System.out.println(jChar.split("、", 3).length); //3 w o r、l、d 限制元素个数为3
        System.out.println(jChar.replace("w、o", "wo")); //wo、r、l、d
        System.out.println(jChar.replaceAll("w、o", "wo"));  //wo、r、l、d

        String eString = "Hello, World";
        String encryptResult = encryptAndDecrypt(eString, '8');
        System.out.println("加密后的内容：" + encryptResult);  //p]TTWoWJT\
        System.out.println("解密后的内容：" + encryptAndDecrypt(encryptResult, '8')); //Hello, World

        System.out.println(new StringBuffer("java").delete(1, 2)); //jva
        System.out.println(new StringBuffer("java").delete(1, 2).insert(1, "a")); //java

        CharSequence cs = "Kamen Rider Drive!";  //String 类向上转型父接口 CharSequence

        //AutoCloseable

    }

    //加密解密
    public static String encryptAndDecrypt(String value, char secret) {
        byte[] bt = value.getBytes(); // 将需要加密的内容转换为字节数组
        for (int i = 0; i < bt.length; i++) {
            bt[i] = (byte) (bt[i] ^ (int) secret); // 通过异或运算进行加密
        }
        String newResult = new String(bt, 0, bt.length); // 将加密后的字符串保存到 newresult 变量中
        return newResult;
    }

}
