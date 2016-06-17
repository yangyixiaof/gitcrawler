package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomBackgroundImage extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6482396499176910249L;
    private JPanel contentPane;
    private BackgroundPanel panel;
    private Image[] images;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RandomBackgroundImage frame = new RandomBackgroundImage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public RandomBackgroundImage() {
        initPhotoArray();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                do_this_windowActivated(arg0);
            }
        });
        setTitle("����������屳��");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 412);// ���ô���λ��
        contentPane = new JPanel();// �����������
        setContentPane(contentPane);// ���ô����������
        contentPane.setLayout(new BorderLayout(0, 0));
        panel = new BackgroundPanel();
        contentPane.add(panel);// �ѱ��������ӵ������������
    }
    
    private void initPhotoArray() {
        images = new Image[6];// ��ʼ������ͼƪ����
        String photoPath = "";
        for (int i = 0; i < images.length; i++) {// �������鲢��ʼ������Ԫ��
            photoPath = "/com/mingrisoft/photo" + (i + 1) + ".jpg";// �����ļ���
            images[i] = getToolkit()
                    .getImage(getClass().getResource(photoPath));// ��ʼ������Ԫ��
        }
    }
    
    protected void do_this_windowActivated(WindowEvent arg0) {
        Random random = new Random();// �������������
        int num = random.nextInt(6);// ���������
        panel.setImage(images[num]);// ������屳��ͼƬ
        repaint();// �ػ洰�����
    }
}
