package com.mytest.basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-11
 */
public class BasicGUI {
    private JFrame frmIpa;
    private JTextArea textArea;

    public BasicGUI(){
        //创建及设置窗口
        frmIpa = new JFrame();
        frmIpa.setTitle("ipa tool");
        //设置窗口的x,y位置，窗口宽高
        frmIpa.setBounds(0, 0, 500, 400);
        //设置默认的关闭窗口
        frmIpa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 面板1
        JPanel panel = new JPanel();
        frmIpa.getContentPane().add(panel, BorderLayout.NORTH);
        JButton button = new JButton("选择文件");
        // 监听button的选择路径
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示打开的文件对话框
                JFileChooser jfc = new JFileChooser();
                jfc.showSaveDialog(frmIpa);
                try {
                    // 使用文件类获取选择器选择的文件
                    File file = jfc.getSelectedFile();//
                    // TODO: 2019-12-12
                    textArea.setText("九曜升");
                } catch (Exception e2) {
                    JPanel panel3 = new JPanel();
                    JOptionPane.showMessageDialog(panel3, "没有选中任何文件", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panel.add(button);

        // 可滚动面板
        JScrollPane scrollPane = new JScrollPane();
        frmIpa.getContentPane().add(scrollPane, BorderLayout.CENTER);
        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        //这个最好放在最后，否则会出现视图问题。
        frmIpa.setVisible(true);

    }

    public static void main(String[] args) {
        new BasicGUI();
    }

}
