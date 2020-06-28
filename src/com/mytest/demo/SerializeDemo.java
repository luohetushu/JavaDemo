package com.mytest.demo;

import com.mytest.bean.Employee;

import java.io.*;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-27
 */
public class SerializeDemo {

    public static void main(String[] args) {
        SerializeDemo serialize = new SerializeDemo();

        //当序列化一个对象到文件
        //serialize.fileSerialize();

        //反序列化对象
        serialize.fileDeserialize();

    }

    /**
     * 什么情况下需要序列化：
     * a）当你想把的内存中的对象状态保存到一个文件中或者数据库中时候；
     * b）当用套接字在网络上传送对象的时候；
     * c）当通过RMI传输对象的时候
     *
     * 序列化一个对象到文件
     */
    private void fileSerialize(){
        Employee e = new Employee();
        e.name = "Reyan Ali";
        e.address = "Phokka Kuan, Ambehta Peer";
        e.SSN = 11122333;
        e.number = 101;
        try {
            FileOutputStream fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /tmp/employee.ser");
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * 反序列化对象
     */
    private void fileDeserialize(){
        Employee e = null;
        try {
            FileInputStream fileIn = new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException i) {
            i.printStackTrace();
        } catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        System.out.println("Deserialize Employee...");
        System.out.println("Name: " + e.name);
        System.out.println("Address: " + e.address);
        System.out.println("SSN: " + e.SSN);
        System.out.println("Number: " + e.number);
    }

}
