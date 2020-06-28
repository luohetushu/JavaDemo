package com.mytest.basic;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BasicFile {

    private static final String BASE_PATH = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";

    public static void main(String[] args) {
        /**
         * java.io.File 类：文件操作类
         *     public class File implements Serializable, Comparable<File>{}
         * 注：在 java.io 包中，File 类是唯一与文件或文件路径本身操作（创建、删除、重命名等）有关的类
         *    要想使用 File 类，需要提供文件的完整路径
         */
        //java.io.File 类：
        /**
         * java.io.File 类：常量 public static final String separator;
         * 在不同的系统，文件路径有不同的分隔符，Windows("\")、Linux("/")，为适配不同系统，
         */
        String path = BASE_PATH.replaceAll("/", File.separator);
        //构造方法一：public File(String pathname); //pathname 文件完整路径
        //File file = new File(BASE_PATH + "/res/source.txt");
        //构造方法二：public File(String parent, String child); //设置父路径与子目录
        File file = new File(path, "/res/" + UUID.randomUUID() + ".txt");



    }

    /**
     * 创建给定 File 文件，首先判断父路径是否存在，再判断该文件是否存在
     * @param file
     */
    public static void createFile(File file){
        //获取父路径：public File getParentFile(){}
        if (!file.getParentFile().exists()){ //判断父路径是否存在
            //创建路径：public boolean mkdirs(){}
            file.getParentFile().mkdirs();
        }
        //判断文件是否存在：public boolean exists(){}
        if (file.exists()){
            //删除文件：public boolean delete(){}
            file.delete();
        } else {
            try {
                //创建新文件：public boolean createNewFile() throws IOException{}
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打印出给定 File 目录下的所有文件，包含子目录中的文件
     * @param file
     */
    private static void listFile(File file){
        if (file.isDirectory()){ //是否是路径
            File[] result = file.listFiles();  //当前路径下所有文件（包含路径）
            if (result != null){
                for (File temp : result) {
                    listFile(temp);
                }
            }
        }
        System.out.println(file);
    }

    /**
     * 重命名给定 File 目录下的所有文件，包含子目录中的文件，并改为指定后缀suffix
     * @param file
     * @param suffix 指定后缀，如txt
     */
    private static void renameFile(File file, String suffix){
        if (file.isDirectory()){
            File[] files = file.listFiles();  //当前路径下所有文件（包含路径）
            if (files != null){
                for (File temp : files) {
                    renameFile(temp, suffix);
                }
            }
        } else {
            if (file.isFile()){
                String fileName;
                if (file.getName().contains(".")){
                    fileName = file.getName().substring(0, file.getName().lastIndexOf(".")) + "." + suffix;
                } else {
                    fileName = file.getName() + "." + suffix;
                }
                File newFile = new File(file.getParentFile(),fileName);
                file.renameTo(newFile); //将当前 File 对象指定的文件更名为给定参数 File 指定的路径名
                System.out.println(file);
            }
        }
    }

}
