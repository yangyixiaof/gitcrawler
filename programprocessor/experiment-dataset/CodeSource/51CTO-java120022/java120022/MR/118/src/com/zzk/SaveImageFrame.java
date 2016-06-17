package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveImageFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private DrawImagePanel imagePanel = null; // ����ͼ��������
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SaveImageFrame frame = new SaveImageFrame();
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
    public SaveImageFrame() {
        super();
        setTitle("����ͼƬ�ļ�"); // ���ô������
        setBounds(200, 160, 316, 237); // ���ô����С��λ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = SaveImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imagePanel = new DrawImagePanel(); // ����ͼ��������
        add(imagePanel); // �ڴ��������ͼ��������
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveImageFrame.this), img
                        .getHeight(SaveImageFrame.this),
                        BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
                Graphics g = bufferImage.getGraphics();// ��û�ͼ�����Ķ���
                g.drawImage(img, 0, 0, null);// �ڻ���ͼ���ϻ���ͼ��
                try {
                    ImageIO.write(bufferImage, "jpg", new File("c:/image.jpg"));// ������ͼ�񱣴浽����
                    JOptionPane.showMessageDialog(null,
                            "�ѽ�ͼƬimage.jpg\n�ɹ��ر��浽C:��");// ��ʾ��ʾ��Ϣ
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("����ͼƬ");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        panel.add(button_1);
        //
    }
    
    // ���������
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, this); // ����ָ����ͼƬ
        }
    }
}
