package com.zzk;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyboardMovePictureFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private ImageIcon icon = null;// ����ͼ��ͼ��
    final JLabel lb_move = new JLabel();// ͨ�����̿��Ƶı�ǩ
    
    public static void main(String args[]) {
        KeyboardMovePictureFrame frame = new KeyboardMovePictureFrame();
        frame.setVisible(true);// ��ʾ����
        frame.getContentPane().requestFocus();// ʹ�������������ý���
    }
    
    public KeyboardMovePictureFrame() {
        super();
        getContentPane().addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                int x = lb_move.getLocation().x;// ����ƶ���ǩ��x����
                int y = lb_move.getLocation().y;// ����ƶ���ǩ��y����
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    lb_move.setLocation(x - 10, y);// �����ƶ���x�����С
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    lb_move.setLocation(x, y - 10);// �����ƶ���y�����С
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    lb_move.setLocation(x + 10, y);// �����ƶ���x��������
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    lb_move.setLocation(x, y + 10);// �����ƶ���y��������
                }
            }
        });
        setTitle("ͨ�������ƶ�ͼƬ");
        getContentPane().setLayout(null);
        setBounds(100, 100, 364, 239);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = KeyboardMovePictureFrame.class
                .getResource("/image/coney.png");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        icon = new ImageIcon(img);// ����ͼ��ͼ��
        lb_move.setIcon(icon);// ָ����ǩ��ʾ��ͼ��
        lb_move.setBounds(35, 30, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // ��ͼƬ�ϵ�������Ҽ��˳�ϵͳ
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
    }
}
