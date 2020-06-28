package com.mytest.basic;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-18
 */
public class BasicStream {
    public static void main(String[] args) {

        BasicStream stream = new BasicStream();

        //使用 System.in 读取字节数组
        //stream.systemInRead();

        //获取文件属性
        //stream.printFileInfo();

        //创建和删除文件
        //stream.createOrDeleteFile();

        //创建和删除目录
        //stream.createOrDeleteDir();

        //遍历目录
        //stream.listFile();

        //RandomAccessFile 动态读取文件内容
        //stream.rafReadFile();

        //FileInputStream 类读取并输出文件的内容
        //stream.fisReadFile();

        //FileReader 类 读取文件内容
        //stream.frReadFile();

        //使用 BufferedReader 类中的 readLine() 方法逐行读取文件中的内容
        stream.brReadFile();

        //从一个字节数组中读取数据，再转换为 int 型进行输出
        //stream.byteToInt();

    }

    //使用 System.in 读取字节数组
    private void systemInRead(){
        byte[] byteData = new byte[100];    //声明一个字节数组
        System.out.println("请输入英文：");
        try {
            System.in.read(byteData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("您输入的内容如下：");
        for (byte byteDatum : byteData) {
            System.out.print((char) byteDatum);
        }
    }

    //获取文件属性
    private void printFileInfo() {
        //指定文件所在的目录
        String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";
        //建立File变量,并设定由f变量引用
        File f = new File(path,"thread.txt");

        //获取文件属性
        System.out.println("/Users/murongyunge/Desktop/IntelliJ/JavaDemo/thread.txt 文件信息如下：");
        System.out.println("文件长度：" + f.length() + "字节");
        System.out.println("文件或者目录：" + (f.isFile() ? "是文件" : "不是文件"));
        System.out.println("文件或者目录：" + (f.isDirectory() ? "是目录" : "不是目录"));
        System.out.println("是否可读：" + (f.canRead() ? "可读取" : "不可读取"));
        System.out.println("是否可写：" + (f.canWrite() ? "可写入" : "不可写入"));
        System.out.println("是否隐藏：" + (f.isHidden() ? "是隐藏文件" : "不是隐藏文件"));
        System.out.println("最后修改日期：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(f.lastModified())));
        System.out.println("文件名称：" + f.getName());
        System.out.println("文件路径：" + f.getPath());
        System.out.println("父路径名: " + f.getParent());
        System.out.println("绝对路径：" + f.getAbsolutePath());

    }

    //创建和删除文件
    private void createOrDeleteFile(){
        try {
            //指定文件所在的目录
            String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";
            File file = new File(path, "create.txt");
            //判断文件是否存在
            if(file.exists()) {
                //存在则先删除
                file.delete();
            }
            //再创建
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建和删除路径
    private void createOrDeleteDir(){
        //指定文件所在的目录
        String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";
        File file = new File(path, "input");
        //判断文件是否存在
        if(file.exists()) {
            //存在则先删除
            file.delete();
        }
        //再创建
        file.mkdir();
    }

    //遍历目录
    private void listFile(){
        //指定文件所在的目录
        String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";
        File file = new File(path);
        System.out.println("文件名称\t\t文件类型\t\t文件大小");
        //调用不带参数的list()方法
        //String[] fileList = file.list();
        //仅包含符合 filter 过滤器的文件和目录
        String[] fileList = file.list(new Filter());
        assert fileList != null;
        for (String s : fileList) {
            //遍历返回的字符数组
            System.out.print(s + "\t\t");
            System.out.print((new File(path, s)).isFile() ? "文件" + "\t\t" : "文件夹" + "\t\t");
            System.out.println((new File(path, s)).length() + "字节");
        }

    }

    static class Filter implements FilenameFilter{

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".sys") || name.endsWith(".txt") || name.endsWith(".bak");
        }
    }

    //RandomAccessFile 动态读取文件内容
    private void rafReadFile(){
        //指定文件所在的目录
        String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";

        File file = new File(path, "message.txt");

        //Files files = new Files();


        try {
            if (!file.exists()){
                file.createNewFile();

            }
            //创建 RandomAccessFile 对象，以读写方式操作 File 对象
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            //写入的字符串
            String txt = "愿千帆过尽，我们还能相视一笑";
            //写入文件
            raf.write(txt.getBytes());

            System.out.println("当前文件指针的位置：" + raf.getFilePointer());
            raf.seek(6);    //移动文件指针
            System.out.println("从文件头跳过6个字节，现在文件内容如下：");
//            System.out.print(new String(raf.readLine()
//                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));    //输出文件内容
            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf.read(buffer)) != -1){
                System.out.println(new String(buffer, 0, len));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    //FileInputStream 类读取并输出文件的内容
    private void fisReadFile(){
        //指定文件所在的目录
        String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";
        //建立File变量,并设定由f变量引用
        File srcFile = new File(path,"message.txt");
        //创建目标文件对象
        File tarFile = new File(path, "copy_words.txt");
        //声明FileInputStream对象fis
        FileInputStream fis = null;
        //声明FileOutputStream对象fos
        FileOutputStream fos = null;
        try {
            //因为File没有读写的能力,所以需要有个InputStream
            fis = new FileInputStream(srcFile);
            //实例化FileOutputStream对象
            fos = new FileOutputStream(tarFile);
            //定义一个字节数组
            byte[] bytes = new byte[1024];

            System.out.println("/Users/murongyunge/Desktop/IntelliJ/JavaDemo/message.txt 文件内容如下：");
            int i = fis.read(bytes); //得到实际读取到的字节数
            while(i != -1) {
                //将数组中从下标0到n的内容给s
                System.out.println(new String(bytes,0, i));

                fos.write(bytes,0, i);    //向 copy_words.txt 文件中写入 message.txt 文件的内容
                i = fis.read(bytes);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();    //关闭FileInputStream对象
                fos.close();    //关闭FileOutputStream对象
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    //FileReader 类 读取文件内容
    private void frReadFile(){
        //指定文件所在的目录
        String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";
        File file = new File(path, "message.txt");
        FileReader fr = null;
        FileWriter fw = null;
        try {

            fw = new FileWriter(file, true);
            //String txt = "\n挥泪作别，别后两宽；有莺莺燕燕，有孤燕难留";
            //fw.write(txt);

            fr = new FileReader(file);    //创建FileReader对象
            int i;
            System.out.println("/Users/murongyunge/Desktop/IntelliJ/JavaDemo/message.txt 文件内容如下：");
            while((i = fr.read()) != -1) {
                //循环读取
                System.out.print((char) i);    //将读取的内容强制转换为char类型
            }
        } catch(Exception e) {
            System.out.print(e);
        } finally {
            try {
                fr.close();    //关闭对象
                fw.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    //使用 BufferedReader 类中的 readLine() 方法逐行读取文件中的内容
    private void brReadFile(){
        //指定文件所在的目录
        String path = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";
        File file = new File(path, "message.txt");
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);    //创建 FileReader 对象
            br = new BufferedReader(fr);    //创建 BufferedReader 对象
            System.out.println("/Users/murongyunge/Desktop/IntelliJ/JavaDemo/message.txt 文件内容如下：");
            String strLine;
            while((strLine = br.readLine())!=null) {
                //循环读取每行数据
                System.out.println(strLine);
            }
        } catch(IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                fr.close();    //关闭 FileReader 对象
                br.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    //从一个字节数组中读取数据，再转换为 int 型进行输出
    private void byteToInt(){
        //创建数组
        byte[] b = new byte[]{1, -1, 25, -22, -5, 23};
        //创建字节数组输入流
        ByteArrayInputStream bais = new ByteArrayInputStream(b,1,4);
        //字节类型的 -1，二进制形式为 11111111，转换为 int 类型后的二进制形式为 00000000 00000000 00000000 11111111，对应的十进制数为 255
        //负数的二进制形式以补码形式存在
        //字节类型的数转换成 int 类型的数时，如果是正数，则数值不变；如果是负数，二进制形式前面直接补了 24 个 0
        int i = bais.read();    //从输入流中读取下一个字节，并转换成int型数据
        while(i != -1) {    //如果不返回-1，则表示没有到输入流的末尾
            System.out.println("原值=" + (byte)i + "\t转换为int类型=" + i);
            i = bais.read();    //读取下一个
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //将字节数组b中的前4个字节元素写到输出流中
        baos.write(b,1,4);
        //输出缓冲区中的字节数
        System.out.println("数组中一共包含：" + baos.size() + "字节");  //4
        //将输出流中的当前内容转换成字节数组
        byte[] newByteArray = baos.toByteArray();
        //输出数组中的内容
        System.out.println(Arrays.toString(newByteArray));    // [-1, 25, -22, -5]

    }


}
