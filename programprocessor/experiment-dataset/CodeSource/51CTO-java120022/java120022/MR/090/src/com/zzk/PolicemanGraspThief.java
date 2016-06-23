package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class PolicemanGraspThief extends JFrame {
    private JLabel label;
    private JButton button;
    final JLabel lb_thief = new JLabel(); // ��ʾС͵�ı�ǩ
    final JLabel lb_policeman = new JLabel(); // ��ʾ����ı�ǩ
    // �����̶߳���
    private Thread thread = new Thread(new GraspThiefThread());
    private boolean stop = false; // Ϊtrueʱ����ʾ��ʾ�ı�Ϊ�������ˡ��ı�ǩ��Ϊfalseʱ����ʾ
    private final JLabel lb_tip = new JLabel("������");
    
    public PolicemanGraspThief() {
        super();
        setTitle("����ץС͵");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 80, 808, 584);
        
        final BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setFocusable(false);
        backgroundPanel.setImage(SwingResourceManager.getImage(
                PolicemanGraspThief.class, "/image/background.png"));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        lb_thief.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/С͵.png"));
        lb_thief.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
                stop = true; // stopΪtrueʱ����ʾ��ʾ�ı�Ϊ�������ˡ��ı�ǩ
                JOptionPane.showMessageDialog(null, "������..."); // ��ʾ��Ϣ��
                lb_tip.setVisible(false);
            }
        });
        lb_thief.setBounds(350, 150, 50, 50);
        backgroundPanel.add(lb_thief);
        
        lb_tip.setBounds(350, 210, 66, 50);
        lb_tip.setForeground(new Color(0, 0, 255));
        lb_tip.setFont(new Font("", Font.BOLD, 16));
        backgroundPanel.add(lb_tip);
        
        lb_policeman.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/����.png"));
        lb_policeman.setBounds(0, 131, 95, 88);
        backgroundPanel.add(lb_policeman);
        lb_tip.setVisible(false);
        backgroundPanel.add(getButton());
        backgroundPanel.add(getLabel());
        thread.start();
        
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.setIcon(SwingResourceManager.getIcon(
                    PolicemanGraspThief.class, "/icon/zailai.png"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent arg0) {
                    if (thread == null) { // �̶߳���Ϊ��
                        thread = new Thread(new GraspThiefThread()); // �����̶߳���
                    }
                    if (!thread.isAlive()) { // ���ǻ�߳�
                        stop = false; // stopΪfalseʱ����ʾ��ʾ�ı�Ϊ�������ˡ��ı�ǩ
                        lb_tip.setVisible(false); // ������ʾ�ı�Ϊ�������ˡ��ı�ǩ
                        thread.start(); // ���������̶߳���
                    }
                }
            });
            button.setBounds(616, 485, 149, 47);
        }
        return button;
    }
    
    /**
     * @author ������
     *         ʹС͵�˶����߳�
     */
    private class GraspThiefThread implements Runnable {
        boolean flag = false; // ��ʶС͵�����˶����������˶��ı���
        int x = 400; // С͵��ǩ���߽�ĺ�����
        
        public void run() {
            while (true) {
                if (stop) { // stopΪtrueʱ����ʾ��ʾ�ı�Ϊ�������ˡ���ǩ
                    int x = lb_thief.getX(); // ���С͵��ǩ�ĺ�����
                    int y = lb_thief.getY(); // ���С͵��ǩ��������
                    lb_tip.setBounds(x, y + 60, 50, 50); // ������ʾ�ı�Ϊ�������ˡ���ǩ����ʾλ�úʹ�С
                    lb_tip.setVisible(true); // ��ʾ��ʾ�ı�Ϊ�������ˡ��ı�ǩ
                    thread = null; // �ͷ��߳���Դ
                    break; // �˳�ѭ���������̵߳�ִ��
                }
                if (flag == false) { // flagΪfalse�����˶�
                    x += 20; // x��ֵ���ӱ�ʾ�����˶�
                    if (x == 640) { // ��С͵��ǩ���߽�ĺ�������640ʱ
                        flag = true; // ��flag��ֵΪtrue
                    }
                } else { // flagΪtrue�����˶�
                    x -= 20; // x��ֵ���ٱ�ʾ�����˶�
                    if (x == 100) { // ��С͵��ǩ���߽�ĺ�������100ʱ
                        flag = false; // ��flag��ֵΪfalse
                    }
                }
                // ����100-200֮��������������������С͵��ǩ�ϱ߽��������
                int y = (int) (Math.random() * 100) + 100;
                lb_thief.setLocation(x, y); // ����С͵��ǩ����ʾλ��
                try {
                    Thread.sleep(200); // ����200����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        PolicemanGraspThief frame = new PolicemanGraspThief();
        frame.setVisible(true);
    }
    
    /**
     * @return
     */
    protected JLabel getLabel() {
        if (label == null) {
            label = new JLabel();
            label.setForeground(new Color(255, 0, 0));
            label.setFont(new Font("", Font.BOLD, 26));
            label.setText("ע�⣺��ʹ����굱ǹ������С͵��");
            label.setBounds(40, 428, 468, 80);
        }
        return label;
    }
    
}
