package com.mytest.utils;

import java.io.*;

public class FileUtils {

    private File srcFile;  //源文件
    private File desFile;  //目标文件

    public FileUtils(String srcPath, String desPath){
        this(new File(srcPath), new File(desPath));
    }

    public FileUtils(File srcFile, File desFile) {
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    /**
     * 文件拷贝
     * @return
     */
    public boolean copyFile(){
        if (!this.srcFile.exists()){
            return false;
        }
        if (!this.desFile.getParentFile().exists()){
            this.desFile.getParentFile().mkdirs();
        }
        byte[] result = new byte[1024];  //开辟缓冲区
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(srcFile);  //源文件输入流
            os = new FileOutputStream(desFile);  //目标文件输出流
            int len;
            while ((len = is.read(result)) != -1){ //将读取数据存储在 result 中
                os.write(result, 0, len);
            }
            return true;
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 使用转存的方式拷贝文件：
     * JDK 1.9 之后
     *    java.io.InputStream : public long transferTo(OutputStream out) throws IOException{}
     *    java.io.Reader : public long transferTo(Writer out) throws IOException{}
     * @return
     */
    public boolean transferToFile(){
        if (!this.srcFile.exists()){
            return false;
        }
        return this.copyFileImpl(srcFile, desFile);
    }

    /**
     * 拷贝文件夹，包括其中的子文件
     * @return
     */
    public void copyDirectory(){
        this.copyDirectoryImpl(this.srcFile);
    }

    /**
     * 判断该文件是否是路径
     * @param file
     */
    private void copyDirectoryImpl(File file){
        if (file.isDirectory()){  //是路径
            File[] files = file.listFiles();
            if (files != null){
                for (File temp : files) {
                    copyDirectoryImpl(temp);  //递归操作
                }
            }
        } else {  // 是文件
            String newFilePath = file.getPath().replace(this.srcFile.getPath() + File.separator, "");
            File newFile = new File(this.desFile, newFilePath);  //目标路径
            //拷贝
            this.copyFileImpl(file, newFile);
        }
    }

    /**
     * 拷贝文件
     * @param srcFile  源文件
     * @param desFile  目标文件
     * @return
     */
    private boolean copyFileImpl(File srcFile, File desFile){
        if (!desFile.getParentFile().exists()){
            desFile.getParentFile().mkdirs();
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(srcFile);  //源文件输入流
            os = new FileOutputStream(desFile);  //目标文件输出流
            is.transferTo(os); //文件转存
            return true;
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
