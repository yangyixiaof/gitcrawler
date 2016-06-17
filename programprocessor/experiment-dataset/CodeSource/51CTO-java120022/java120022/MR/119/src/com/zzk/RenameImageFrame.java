package com.zzk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class RenameImageFrame extends JFrame {
    private JTextField tf_newFileName;
    private JTextField tf_fileName;
    private File imgFile = null;
    private BufferedImage src;
    private String path = null;// 图片的路径
    private String fileName = null;// 原图片的文件名
    private DrawImagePanel imagePanel = null; // 声明图像面板对象
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RenameImageFrame frame = new RenameImageFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public RenameImageFrame() {
        super();
        setTitle("修改图片文件名"); // 设置窗体标题
        setBounds(200, 160, 391, 288); // 设置窗体大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imagePanel = new DrawImagePanel(); // 创建图像面板对象
        add(imagePanel); // 在窗体上添加图像面板对象
        
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("选择图片：");
        panel.add(label_1);
        
        tf_fileName = new JTextField();
        tf_fileName.setPreferredSize(new Dimension(200, 25));
        panel.add(tf_fileName);
        
        final JButton button_2 = new JButton();
        panel.add(button_2);
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// 创建文件选择器
                FileFilter filter = new FileNameExtensionFilter(
                        "图像文件（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// 创建过滤器
                fileChooser.setFileFilter(filter);// 设置过滤器
                int i = fileChooser.showOpenDialog(null);// 显示打开对话框
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // 获取选中图片的File对象
                    path = imgFile.getParent();// 获得图片的路径
                    fileName = imgFile.getName();// 获得原图片文件名
                    tf_fileName.setText(path + "\\" + fileName);// 在文本框中显示图片的完整路径
                }
                if (imgFile != null) {
                    try {
                        src = ImageIO.read(imgFile);// 构造BufferedImage对象
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imagePanel.repaint();// 调用paint方法
            }
        });
        button_2.setText("选择图片");
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("输入新文件名：");
        panel_1.add(label);
        
        tf_newFileName = new JTextField();
        tf_newFileName.setPreferredSize(new Dimension(160, 25));
        panel_1.add(tf_newFileName);
        
        final JButton button = new JButton();
        panel_1.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (path != null && fileName != null) {
                    String newName = tf_newFileName.getText().trim();// 获得新输入的文件名
                    if (newName.indexOf(":") >= 0 || newName.indexOf("\\") >= 0) {// 判断文件名是否包含路径
                        JOptionPane.showMessageDialog(null, "请直接输入新文件名。\n不需要指定路径。");
                        return;
                    } else if (newName.equals("")) {// 如果没有输入文件名则进行提示
                        JOptionPane.showMessageDialog(null, "请输入新文件名。");
                        return;
                    } else {
                        if (newName.indexOf(".") > 0) {
                            imgFile.renameTo(new File(path + "\\" + newName));// 新文件名带扩展名
                        } else {
                            imgFile.renameTo(new File(path + "\\" + newName + fileName.substring(fileName.indexOf("."))));// 新文件名不带扩展名
                        }
                    }
                    JOptionPane.showMessageDialog(null, "修改成功。");
                }
            }
        });
        button.setText("重命名图片");
    }
    
    // 创建面板类
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(src, 0, 0, getWidth(), getHeight(), this); // 绘制指定的图片
        }
    }
}
