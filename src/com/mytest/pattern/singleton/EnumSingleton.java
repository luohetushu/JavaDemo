package com.mytest.pattern.singleton;

/**
 * @Author murongyunge
 * @Describe 单例模式 枚举
 *         * 避免反射攻击
 *         * 避免序列化
 * @Date 2019-12-10
 */
public enum EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}
