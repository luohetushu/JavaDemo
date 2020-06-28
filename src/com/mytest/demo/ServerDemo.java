package com.mytest.demo;

import com.mytest.bean.User;

import java.io.*;
import java.net.*;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-25
 */
public class ServerDemo {
    public static void main(String[] args) {
        ServerDemo server = new ServerDemo();

        //简单数据接收客户端
        //server.dataReceive();

        //接收数据对象
        //server.objectReceive();

        //UDP 程序，接收数据
        server.UDPReceive();

    }

    /**
     * 简单数据接收客户端
     */
    private void dataReceive(){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;
        int port = 5000;
        String str = null;
        try {
            serverSocket = new ServerSocket(port);    //创建服务器套接字
            System.out.println("服务器开启，等待连接。。。");
            clientSocket = serverSocket.accept();// 获得链接
            //接收客户端发送的内容
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while((str = in.readLine()) != null) {
                System.out.println("客户端发送的内容为：" + str);
                Thread.sleep(2000);
            }
        } catch(IOException | InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                //关闭客户端
                serverSocket.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 接收数据对象
     */
    private void objectReceive(){

        try {
            // 监听10000端口
            ServerSocket server = new ServerSocket(10000);
            System.out.println("服务器开启，等待连接。。。");
            //接收客户端的连接
            Socket socket = server.accept();
            //调用客户端的数据处理方法
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //创建输入流对象
                    ObjectInputStream is = null;
                    //创建输出流对象
                    ObjectOutputStream os = null;
                    try {
                        is = new ObjectInputStream(socket.getInputStream());
                        os = new ObjectOutputStream(socket.getOutputStream());
                        //读取一个对象
                        Object obj;
                        while ((obj = is.readObject()) != null){
                            //将对象转换为User类型
                            User user = (User) obj;
                            //在服务器端输出name成员和password成员信息
                            System.out.println("user: " + user.getName() + "/" + user.getPassword());
                            //修改当前对象的name成员数据
                            user.setName(user.getName() + "_new");
                            //修改当前对象的password对象数据
                            user.setPassword(user.getPassword() + "_new");
                            //将修改后的对象输出给客户端
                            os.writeObject(user);
                            os.flush();
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
                        } catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * UDP 程序，接收数据
     */
    private void UDPReceive(){
        DatagramSocket ds = null;
        DatagramPacket dpReceive = null;
        int port = 3021;

        try {
            ds = new DatagramSocket(port);
            System.out.println("UDP服务器已启动。。。");
            byte[] b = new byte[1024];
            while(!ds.isClosed()) {
                dpReceive = new DatagramPacket(b, b.length);
                ds.receive(dpReceive);
                byte[] Data=dpReceive.getData();
                int len = Data.length;
                System.out.println("UDP客户端发送的内容是：" + new String(Data, 0, len).trim());
                System.out.println("UDP客户端IP：" + dpReceive.getAddress());
                System.out.println("UDP客户端端口：" + dpReceive.getPort());
            }
        } catch(IOException  e1){
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }

    }

}
