package com.mytest.basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-22
 */
public class BasicNetwork {

    public static void main(String[] args) {

        try {
            //创建一个 InetAddress 对象
            InetAddress ia1 = InetAddress.getByName("www.qq.com");
            //System.out.println(new String(ia1.getAddress(), StandardCharsets.ISO_8859_1));
            System.out.println(ia1.getCanonicalHostName()); //61.151.166.146
            System.out.println("主机名：" + ia1.getHostName());  //www.qq.com
            System.out.println("IP 地址：" + ia1.getHostAddress());  //61.151.166.146

            InetAddress ia2 = InetAddress.getByName("61.135.169.105");
            System.out.println(ia2.getHostName());  //61.135.169.105
            System.out.println(ia2.getHostAddress());  //61.135.169.105

            InetAddress ia3 = InetAddress.getLocalHost();
            System.out.println("主机名：" + ia3.getHostName());  //主机名：murongyungedeMacBook-Pro.local
            System.out.println("本地ip地址：" + ia3.getHostAddress());  //本地ip地址：10.0.100.101

        } catch (UnknownHostException e) {
            //包含互联网的地址，运行时需要连网，否则会出现异常
            e.printStackTrace();
        }

        try {
            //在8888端口创建一个服务器端套接字
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务器端Socket创建成功");
            System.out.println("等待客户端的连接请求");
        } catch(IOException e) {
            e.printStackTrace();
        }

        try
        {
            URL url=new URL("http://www.baidu.com/");
            System.out.println("协议：" + url.getProtocol());
            System.out.println("主机：" + url.getHost());
            System.out.println("端口：" + url.getPort());
            InputStream in;
            URLConnection uc = url.openConnection();
            in = uc.getInputStream();
            byte[] b = new byte[1024];
            int len;
            while((len=in.read(b)) != -1)
            {
                System.out.println(new String(b,0,len));
            }
            in.close();
        } catch(IOException e) {
            //TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

}
