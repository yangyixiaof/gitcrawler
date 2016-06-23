package com.zzk;

import static java.lang.Math.random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author ������
 */
public class MainFrame extends JFrame {
    private static long score = 0;// ����
    private static Integer ammoNum = 5;// �ӵ�����
    private static JLabel scoreLabel;// ����
    private BackgroundPanel backgroundPanel;
    private static JLabel ammoLabel;
    private static JPanel infoPane;
    
    /**
     * �ӷַ���
     * 
     * @param score
     */
    public synchronized static void appScore(int num) {
        score += num;
        scoreLabel.setText("������" + score);
    }
    
    /**
     * �����ӵ��ķ���
     * 
     * @param num
     */
    public synchronized static void useAmmo() {
        synchronized (ammoNum) {
            ammoNum--;// �ӵ������ݼ�
            ammoLabel.setText("�ӵ�������" + ammoNum);
            if (ammoNum <= 0) {// �ж��ӵ��Ƿ�С��0
                new Thread(new Runnable() {
                    public void run() {
                        // ��ʾ��ʾ��Ϣ���
                        infoPane.setVisible(true);
                        try {
                            // 1����װ���ӵ���ʱ��
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ammoNum = 5;// �ָ��ӵ�����
                        // �޸��ӵ�������ǩ���ı�
                        ammoLabel.setText("�ӵ�������" + ammoNum);
                        infoPane.setVisible(false);// ������ʾ��Ϣ���
                    }
                }).start();
            }
        }
    }
    
    /**
     * �ж��ӵ��Ƿ���
     * 
     * @return
     */
    public synchronized static boolean readyAmmo() {
        synchronized (ammoNum) {
            return ammoNum > 0;
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                    frame.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * ���췽��
     */
    public MainFrame() {
        super();
        setResizable(false);// ���Ƶ��������С
        setTitle("��ɽ������Ϸ");
        infoPane = (JPanel) getGlassPane();// ��ȡ�������
        JLabel label = new JLabel("װ���ӵ�����");// ������ʾ��ǩ���
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("����", Font.BOLD, 32));
        label.setForeground(Color.ORANGE);
        infoPane.setLayout(new BorderLayout());
        infoPane.add(label);// �����ʾ��ǩ������������
        
        setAlwaysOnTop(true);// �Ǵ��屣�������
        setBounds(100, 100, 573, 411);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgroundPanel = new BackgroundPanel();// ���������������
        backgroundPanel.setImage(new ImageIcon(getClass()
                .getResource("background.jpg")).getImage());// ���ñ���ͼƬ
        getContentPane().add(backgroundPanel,
                BorderLayout.CENTER);
        // �������¼�������
        addMouseListener(new FrameMouseListener());
        scoreLabel = new JLabel();// ��ʾ�����ı�ǩ���
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.ORANGE);
        scoreLabel.setText("������");
        scoreLabel.setBounds(25, 15, 120, 18);
        backgroundPanel.add(scoreLabel);
        ammoLabel = new JLabel();// ��ʾ�Զ������ı�ǩ���
        ammoLabel.setForeground(Color.ORANGE);
        ammoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ammoLabel.setText("�ӵ�������" + ammoNum);
        ammoLabel.setBounds(422, 15, 93, 18);
        backgroundPanel.add(ammoLabel);
    }
    
    /**
     * ������Ϸ�ķ���
     */
    public void start() {
        new PigThread().start();
        new BirdThread().start();
    }
    
    /**
     * ���������¼�������
     * 
     * @author ������
     */
    private final class FrameMouseListener extends MouseAdapter {
        public void mousePressed(final MouseEvent e) {
            Component at = backgroundPanel.getComponentAt(e
                    .getPoint());
            if (at instanceof BackgroundPanel) {// ����㵽���Ҳ�۳��ӵ�
                MainFrame.useAmmo();// �����ӵ�
            }
        }
    }
    
    /**
     * �������ɫ���߳�
     * 
     * @author ������
     */
    class PigThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // ��������Ұ��ı�ǩ�ؼ�
                PigLabel pig = new PigLabel();
                pig.setSize(120, 80);// ���ÿؼ���ʼ��С
                backgroundPanel.add(pig);// ��ӿؼ����������
                try {
                    // �߳��������һ��ʱ��
                    sleep((long) (random() * 3000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * �������ɫ���߳�
     * 
     * @author ������
     */
    class BirdThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // ��������С��ı�ǩ�ؼ�
                BirdLabel bird = new BirdLabel();
                bird.setSize(50, 50);// ���ÿؼ���ʼ��С
                backgroundPanel.add(bird);// ��ӿؼ����������
                try {
                    // �߳��������һ��ʱ��
                    sleep((long) (Math.random() * 3000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
