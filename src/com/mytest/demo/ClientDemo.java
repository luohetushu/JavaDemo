package com.mytest.demo;

import com.mytest.bean.User;

import java.io.*;
import java.net.*;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-25
 */
public class ClientDemo {

    public static void main(String[] args) {
        ClientDemo client = new ClientDemo();

        //简单数据传输服务端
        //client.dataTransfer();

        //传输对象数据
        //client.objectTransfer();

        //UDP 程序，发送数据
        client.UDPTransfer();

    }

    /**
     * 简单数据传输服务端
     */
    private void dataTransfer(){
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String serverIP = "127.0.0.1";    //服务器端ip地址
        int port = 5000;    //服务器端端口号

        try{
            socket = new Socket(serverIP,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            int number;
            do {
                number = (int)(Math.random() * 10) + 1;
                System.out.println("客户端正在发送的内容为：" + number);
                out.println(number);
                Thread.sleep(2000);
            } while (number < 8);
        } catch(IOException | InterruptedException e){
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                //关闭客户端
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 传输对象数据
     */
    private void objectTransfer(){
        //创建客户端Socket
        Socket socket = null;
        //创建输入流
        ObjectOutputStream os = null;
        //创建输出流
        ObjectInputStream is = null;

        try {

            //连接服务器
            socket = new Socket("localhost", 10000);
            //接收输出流中的数据
            os = new ObjectOutputStream(socket.getOutputStream());

            User user;

            for (int i = 0; i < 5; i++) {
                //创建一个User对象
                user = new User("user_" + i, "password_" + i);
                //将User对象写入输出流
                os.writeObject(user);
                os.flush();
            }

            //接收输入流中的数据
            is = new ObjectInputStream(socket.getInputStream());
            //读取输入流中的数据
            Object obj;
            while ((obj = is.readObject()) != null){
                user = (User) obj;
                System.out.println("user: " + user.getName() + "/" + user.getPassword());
            }

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                //关闭输入流
                is.close();
                //关闭输出流
                os.close();
                //关闭客户端
                socket.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * UDP 程序，发送数据
     */
    private void UDPTransfer(){

        DatagramSocket ds = null;

        try {

            DatagramPacket dpSend = null;
            InetAddress ia = InetAddress.getByName("127.0.0.1");
            int port = 3021;

            ds = new DatagramSocket();
            for(int i = 0; i < 5; i++) {
                byte[] data=("我是 UDP 客户端" + i).getBytes();
                dpSend = new DatagramPacket(data, data.length, ia, port);
                ds.send(dpSend);
                Thread.sleep(1000);
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            ds.close();
        }

    }

}
