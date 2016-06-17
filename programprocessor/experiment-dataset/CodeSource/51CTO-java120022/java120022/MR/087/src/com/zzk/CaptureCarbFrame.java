package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CaptureCarbFrame extends JFrame implements Runnable {
    private JLabel[] carb; // �����ʾ�з�ı�ǩ����
    private ImageIcon imgCarb; // �зͼƬ����
    private MouseCrab mouseCrab;// ��ǩ���¼�������
    
    /**
     * ��ʾ�з�ı�ǩ�ؼ�������¼�������
     * @author ������
     */
    private final class MouseCrab implements MouseListener {
        private final Cursor cursor1;// ���ͼ��1
        private final Cursor cursor2;// ���ͼ��2
        
        /**
         * ���췽��
         * 
         * @param cursor1
         * @param cursor2
         */
        private MouseCrab(Cursor cursor1, Cursor cursor2) {
            this.cursor1 = cursor1;
            this.cursor2 = cursor2;
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(cursor1);// ��갴���ͷ�ʱ���ù��Ϊcursor1
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            setCursor(cursor2);// ��갴������ʱ���ù��Ϊcursor2
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(cursor1);// ����뿪�ؼ�����ʱ���ù��Ϊcursor1
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
    
    /**
     * ��굥���¼������������ڸı�����ͼ�ꡣ
     * 
     * @author ������
     */
    private final class Catcher extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // ��ȡ�¼�Դ�����з��ǩ
            if (source instanceof JLabel) { // ����¼�Դ�Ǳ�ǩ���
                JLabel carb = (JLabel) source; // ǿ��ת��ΪJLabel��ǩ
                if (carb.getIcon() != null)
                    carb.setIcon(imgCarb2); // Ϊ�ñ�ǩ����зͼƬ
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // ��ȡ�¼�Դ�����з��ǩ
            if (source instanceof JLabel) { // ����¼�Դ�Ǳ�ǩ���
                JLabel carb = (JLabel) source; // ǿ��ת��ΪJLabel��ǩ
                carb.setIcon(null);// �����ǩ�е��зͼƬ
            }
        }
    }
    
    private ImageIcon imgCarb2;
    
    /**
     * ������ڷ���
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // ����������
                    CaptureCarbFrame frame = new CaptureCarbFrame();
                    frame.setVisible(true);// ��ʾ����
                    new Thread(frame).start();// �����̲߳�����
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * ���췽��
     */
    public CaptureCarbFrame() {
        super();
        // ������һ�����ͼ��
        ImageIcon icon = new ImageIcon(getClass().getResource("hand.jpg"));
        // �����ڶ������ͼ��
        ImageIcon icon2 = new ImageIcon(getClass().getResource("hand2.jpg"));
        // ��ȡÿ��ͼ���ͼƬ
        Image image = icon.getImage();
        Image image2 = icon2.getImage();
        // ʹ��ͼƬ����2����������
        final Cursor cursor1 = getToolkit().createCustomCursor(image,
                new Point(0, 0), "hand1");
        final Cursor cursor2 = getToolkit().createCustomCursor(image2,
                new Point(0, 0), "hand2");
        // ��ʼ����ʾ�з��ǩ������¼�������
        mouseCrab = new MouseCrab(cursor1, cursor2);
        setResizable(false); // ��ֹ���������С
        getContentPane().setLayout(null); // ���岻ʹ�ò��ֹ�����
        setTitle("��̲׽�з"); // ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ��ʼ������ͼƬ����
        ImageIcon img = new ImageIcon(getClass().getResource("background.jpg"));
        // ��ʼ���зͼƬ����
        imgCarb = new ImageIcon(getClass().getResource("crab.png"));
        imgCarb2 = new ImageIcon(getClass().getResource("crab2.png"));
        carb = new JLabel[6]; // ������ʾ�з�ı�ǩ����
        Catcher catcher = new Catcher();// ��ǩ����굥���¼�������
        for (int i = 0; i < 6; i++) { // ��������
            carb[i] = new JLabel(); // ��ʼ��ÿһ������Ԫ��
            // ���ñ�ǩ���зͼƬ��ͬ��С
            carb[i].setSize(imgCarb.getIconWidth(), imgCarb.getIconHeight());
            // Ϊ��ǩ����¼�������
            carb[i].addMouseListener(catcher);
            carb[i].addMouseListener(mouseCrab);
            getContentPane().add(carb[i]); // �����ʾ�з�ı�ǩ������
        }
        
        carb[0].setLocation(253, 315); // ����ÿ����ǩ��λ��
        carb[1].setLocation(333, 265);
        carb[2].setLocation(388, 311);
        carb[3].setLocation(362, 379);
        carb[4].setLocation(189, 368);
        carb[5].setLocation(240, 428);
        
        final JLabel backLabel = new JLabel(); // ������ʾ�����ı�ǩ
        // ���ñ�ǩ�뱳��ͼƬ��ͬ��С
        backLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        // ���ô�����Ʊ���ͼƬ��С
        setBounds(100, 100, img.getIconWidth(), img.getIconHeight() + 30);
        backLabel.setIcon(img); // ��ӱ�������ǩ
        getContentPane().add(backLabel); // ��ӱ�����ǩ������
        setCursor(cursor1);// ����Ĭ��ʹ�õ�һ�������
        addMouseListener(mouseCrab);// Ϊ����������¼�������
    }
    
    /**
     * �̵߳ĺ��ķ���
     */
    public void run() {
        while (true) { // ʹ������ѭ��
            try {
                Thread.sleep(1000); // ʹ�߳�����1��
                int index = (int) (Math.random() * 6);// ����������з����
                if (carb[index].getIcon() == null) {// ����з��ǩû������ͼƬ
                    carb[index].setIcon(imgCarb);// Ϊ�ñ�ǩ����зͼƬ
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
