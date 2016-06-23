package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EdgeDetectImageFrame extends JFrame {
    private BufferedImage image;// ��������ͼ�����
    private EdgeDetectImagePanel edgeDetectImagePanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        EdgeDetectImageFrame frame = new EdgeDetectImageFrame();
        frame.setVisible(true);
    }
    
    public EdgeDetectImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ������Ե��Ч"); // ���ô������
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        edgeDetectImagePanel = new EdgeDetectImagePanel(); // ����ͼ��������
        this.add(edgeDetectImagePanel); // �ڴ��������ͼ��������
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = {0.0f,-1.0f,0.0f,-1.0f,4.0f,-1.0f,0.0f,-1.0f,0.0f};// ������ʾ���ط���������
                convolve(elements);// ���÷���ʵ��ͼƬ������Ե����
            }
        });
        button.setText("������Ե");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        panel.add(button_1);
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);             // ���� Kernel����
        ConvolveOp op = new ConvolveOp(kernel);         // ����ConvolveOp����
        if (image == null) {
            return;
        }
        image = op.filter(image, null);                     // ���˻���ͼ�����
        repaint();                                  // ����paint()����
    }

    
    // ���������
    class EdgeDetectImagePanel extends JPanel {
        public EdgeDetectImagePanel(){
            Image img = null;// ��������ͼ�����
            try {
                img = ImageIO.read(new File("src/img/image.jpg"));  // ����ͼ�����
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0,  null);// �ڻ���ͼ������ϻ���ͼ��
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// ���ƻ���ͼ�����
            }
        }
    }
}
