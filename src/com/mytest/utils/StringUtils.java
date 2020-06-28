package com.mytest.utils;

import java.util.Base64;

public class StringUtils {

    private static final String SALT = "public_salt";  //使用盐值操作

    /**
     * 对数据进行多次加密
     * @param src  要加密的原始数据
     * @param repeat 要加密的次数
     * @return
     */
    public static String encrypt(String src, int repeat){
        String temp = src + "{" + SALT + "}"; //使用盐值操作
        byte[] bytes = temp.getBytes();
        for (int i = 0; i < repeat; i++) {
            bytes = Base64.getEncoder().encode(bytes);  //多次加密
        }
        return new String(bytes);
    }

    /**
     * 对加密数据进行多次解密
     * @param enSrc  加密的数据
     * @param repeat 解密的次数
     * @return 返回原始数据
     */
    public static String decrypt(String enSrc, int repeat){
        byte[] bytes = enSrc.getBytes();
        for (int i = 0; i < repeat; i++) {
            bytes = Base64.getDecoder().decode(bytes);
        }
        return new String(bytes).replaceAll("\\{\\w+}", "");
    }

}
