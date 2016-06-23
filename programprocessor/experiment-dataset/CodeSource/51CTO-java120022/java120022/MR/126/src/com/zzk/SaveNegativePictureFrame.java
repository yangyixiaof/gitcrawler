package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveNegativePictureFrame extends JFrame {
    private BufferedImage image;// 声明缓冲图像对象
    private NegativePicturePanel negativePicturePanel = null; // 声明图像面板对象
    private boolean negativeFlag = false;// 反向标记
    public static void main(String args[]) {
        SaveNegativePictureFrame frame = new SaveNegativePictureFrame();
        frame.setVisible(true);
    }
    
    public SaveNegativePictureFrame() {
        super();
        setTitle("反向并保存图片"); // 设置窗体标题
        setBounds(200, 160, 516, 458); // 设置窗体大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativePicturePanel = new NegativePicturePanel(); // 创建图像面板对象
        add(negativePicturePanel); // 在窗体上添加图像面板对象
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256];// 创建表示颜色反向的分量数组
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// 为数组赋值
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// 创建查找表对象
                LookupOp op = new LookupOp(table,null);// 创建实现从源到目标查找操作的LookupOp对象
                image = op.filter(image, null);// 调用LookupOp对象的filter()方法，实现图像反向功能
                repaint();  // 调用paint()方法
                negativeFlag = !negativeFlag;// 标记是否已反向
                if (negativeFlag){
                    button.setText("还原图片");
                }else{
                    button.setText("反向图片");
                }
            }
        });
        button.setText("反向图片");
        panel.add(button);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!negativeFlag) {
                    JOptionPane.showMessageDialog(null,"还没执行反向操作。");// 显示提示信息
                    return;
                }
                FileDialog dialog = new FileDialog(SaveNegativePictureFrame.this,"保存");// 创建对话框
                dialog.setMode(FileDialog.SAVE);// 设置对话框为保存对话框
                dialog.setVisible(true);// 显示保存对话框
                String path = dialog.getDirectory();// 获得文件的保存路径
                String fileName = dialog.getFile();// 获得保存的文件名
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// 文件扩展名,不含点
                String pathAndName = path + "\\" + fileName;// 文件的完整路径
                try {
                    ImageIO.write(image, fileExtName, new File(pathAndName));// 将缓冲图像保存到磁盘
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"保存失败\n"+e1.getMessage());// 显示提示信息
                }
            }
        });
        button_2.setText("保存图片");
        panel.add(button_2);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
    }
    
    
    // 创建面板类
    class NegativePicturePanel extends JPanel {
        public NegativePicturePanel(){
            Image img = null;// 声明创建图像对象
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // 创建图像对象
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
            image.getGraphics().drawImage(img, 0, 0,  null);// 在缓冲图像对象上绘制图像
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// 绘制缓冲图像对象
            }
        }
    }
}
