package com.mytest.bean;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-27
 */
public class Employee implements java.io.Serializable {
    public String name;
    public String address;
    //如果有一个属性不是可序列化的，则该属性必须注明是短暂的
    public transient int SSN;
    public int number;

    public void mailCheck() {
        System.out.println("Mailing a check to " + name
                + " " + address);
    }
}
