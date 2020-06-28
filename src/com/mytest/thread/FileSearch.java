package com.mytest.thread;

import java.io.File;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-22
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()){
            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: the search is interrupted\n", Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file) throws InterruptedException {
        File[] files = file.listFiles();
        if (files != null){
            for (File s_file : files) {
                System.out.printf("path of %s is %s\n", s_file.getName(), s_file.getAbsolutePath());
                if (s_file.isDirectory()){
                    directoryProcess(s_file);
                } else {
                    fileProcess(s_file);
                }
            }
        }

        if (Thread.interrupted()){
            throw new InterruptedException();
        }

    }

    private void fileProcess(File file) throws InterruptedException {
        if (file.getName().equals(fileName)){
            System.out.printf("file %s is existed\n", file.getName());
        }

        if (Thread.interrupted()){
            throw new InterruptedException();
        }
    }

    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch("/Users/murongyunge/Desktop/IntelliJ/JavaDemo", "PrimeGenerator.java");
        Thread thread = new Thread(fileSearch);
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }

}
