package com.mytest.utils;

public class VerifyUtils {

    private VerifyUtils(){}

    /**
     * 验证是否是邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (email == null || email.equals("")){
            return false;
        }
        String regex = "[a-zA-Z0-9]\\w+@\\w+\\.(com|cn)";
        return email.matches(regex);
    }

    /**
     * 验证是否是 IP 地址
     * @param ipAddress
     * @return
     */
    public static boolean isIPAddress(String ipAddress){
        if (ipAddress == null || ipAddress.equals("")){
            return false;
        }
        String regex = "([1-2]?[0-9]?[0-9]\\.){3}([1-2]?[0-9]?[0-9])";
        if (ipAddress.matches(regex)){
            String[] temp = ipAddress.split("\\.");
            for (String str : temp) {
                Integer intStr = Integer.valueOf(str);
                if (intStr <= 0 || intStr > 255){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 验证是否是 6～12 密码 密码由数字、字母
     * @param password
     * @return
     */
    public static boolean isPasswordRight(String password){
        if (password == null || password.equals("")){
            return false;
        }
        String regex = "[a-zA-Z0-9]\\w+";
        if (password.matches(regex)){
            return password.length() >= 6 && password.length() <= 12;
        }
        return false;
    }

}
