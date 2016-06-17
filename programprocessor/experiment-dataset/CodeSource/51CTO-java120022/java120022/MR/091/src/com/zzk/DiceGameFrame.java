package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class DiceGameFrame extends JFrame {
    private boolean flag = false; // ���ڱ�ʶѹ��ѹС�ı�����true��ʾѹ��false��ʾѹС
    private int v1 = 0; // ��һ�����ӵĵ���
    private int v2 = 0; // �ڶ������ӵĵ���
    private int v3 = 0; // ���������ӵĵ���
    private int stopIndex = 0; // ��stopIndexΪ50ʱ����ʾ���յĵ���
    private final JLabel lb_dice_1 = new JLabel();
    private final JLabel lb_dice_2 = new JLabel();
    private final JLabel lb_dice_3 = new JLabel();
    final JLabel lb_follow = new JLabel();
    Thread thread = null; // �����ж����ӵ������߳�
    Thread okThread = null; // ����ȷ�϶�ע���߳�
    // �������ֵ
    private int ten = 10;
    private int twenty = 20;
    private int fifty = 50;
    private int hundred = 100;
    // �������ֵ���
    private boolean tenFlag = false;
    private boolean twentyFlag = false;
    private boolean fiftyFlag = false;
    private boolean hundredFlag = false;
    // ����ұ��
    private boolean moneyFlag = false;
    // �ӱ���ע�ı��
    private boolean doubleFlag = false;
    // ����ҳ��ۼƽ��
    int totalMoney = 0;
    // ȷ����ע���
    private boolean ok = false;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DiceGameFrame frame = new DiceGameFrame();
        frame.setVisible(true);
    }
    
    public DiceGameFrame() {
        super();
        setTitle("������");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(673, 577);
        final BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setImage(SwingResourceManager.getImage(DiceGameFrame.class,
                "/image/background.jpg"));
        getContentPane().add(bgPanel, BorderLayout.CENTER);
        
        lb_dice_1.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/1.png"));
        lb_dice_1.setBounds(253, 143, 57, 55);
        bgPanel.add(lb_dice_1);
        
        lb_dice_3.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/1.png"));
        lb_dice_3.setBounds(304, 231, 57, 55);
        bgPanel.add(lb_dice_3);
        
        lb_dice_2.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/1.png"));
        lb_dice_2.setBounds(354, 143, 57, 55);
        bgPanel.add(lb_dice_2);
        
        final JButton button = new JButton();
        button.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/С.png"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (ok == true) {
                    if (thread == null) {
                        flag = false; // Ϊfalse��ʾѹС
                        thread = new Thread(new DiceThread()); // �����ж����ӵ������߳�
                        thread.start(); // �����߳�
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "����ע�����ȷ�ϡ�");
                }
            }
        });
        button.setBounds(216, 337, 106, 28);
        bgPanel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/��.png"));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (ok == true) {
                    if (thread == null) {
                        flag = true; // Ϊtrue��ʾѹ��
                        thread = new Thread(new DiceThread()); // �����ж����ӵ������߳�
                        thread.start(); // �����߳�
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "����ע�����ȷ�ϡ�");
                }
            }
        });
        button_1.setBounds(339, 337, 106, 28);
        bgPanel.add(button_1);
        
        final JButton btn_10 = new JButton();
        btn_10.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!tenFlag) {
                    // �������ֵ���
                    tenFlag = true;
                    // ����ұ��
                    moneyFlag = true;
                    // ����ҳ��ۼƽ��
                    totalMoney = totalMoney + ten;
                }
            }
        });
        btn_10.setText("10Ԫ");
        btn_10.setBounds(215, 386, 106, 28);
        bgPanel.add(btn_10);
        
        final JButton btn_20 = new JButton();
        btn_20.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!twentyFlag) {
                    // �������ֵ���
                    twentyFlag = true;
                    // ����ұ��
                    moneyFlag = true;
                    // ����ҳ��ۼƽ��
                    totalMoney = totalMoney + twenty;
                }
            }
        });
        btn_20.setText("20Ԫ");
        btn_20.setBounds(339, 386, 106, 28);
        bgPanel.add(btn_20);
        
        final JButton btn_50 = new JButton();
        btn_50.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!fiftyFlag) {
                    // �������ֵ���
                    fiftyFlag = true;
                    // ����ұ��
                    moneyFlag = true;
                    // ����ҳ��ۼƽ��
                    totalMoney = totalMoney + fifty;
                }
            }
        });
        btn_50.setText("50Ԫ");
        btn_50.setBounds(216, 420, 106, 28);
        bgPanel.add(btn_50);
        
        final JButton btn_100 = new JButton();
        btn_100.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!hundredFlag) {
                    // �������ֵ���
                    hundredFlag = true;
                    // ����ұ��
                    moneyFlag = true;
                    // ����ҳ��ۼƽ��
                    totalMoney = totalMoney + hundred;
                }
            }
        });
        btn_100.setText("100Ԫ");
        btn_100.setBounds(339, 420, 106, 28);
        bgPanel.add(btn_100);
        
        final JButton btn_double = new JButton();
        btn_double.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!doubleFlag) {
                    if (moneyFlag == true) {
                        totalMoney = totalMoney * 2;
                        doubleFlag = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "ѡ������Һ���ܼӱ���ע��");
                    }
                }
            }
        });
        btn_double.setText("�ӱ�");
        btn_double.setBounds(339, 454, 106, 28);
        bgPanel.add(btn_double);
        
        final JButton btn_ok = new JButton();
        btn_ok.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/xiazhu.png"));
        btn_ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (moneyFlag == true) { // ������ȷ����ע�˾�ִ������Ĵ���
                    ok = true;
                    if (okThread == null) {
                        okThread = new Thread(new OkAnteThread()); // ����ȷ�϶�ע���̶߳���
                    }
                    if (!okThread.isAlive()) {
                        okThread.start(); // �����̶߳���
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "����ע�����ȷ�ϡ�");
                }
            }
        });
        btn_ok.setBounds(339, 505, 106, 28);
        bgPanel.add(btn_ok);
        
        lb_follow.setFont(new Font("", Font.BOLD, 16));
        lb_follow.setForeground(new Color(0, 0, 255));
        lb_follow.setText("����......");
        lb_follow.setBounds(121, 478, 137, 55);
        lb_follow.setVisible(false);
        bgPanel.add(lb_follow);
        
    }
    
    /**
     * @author ������
     * �ж����ӵ������߳�
     */
    private class DiceThread implements Runnable {
        public void run() {
            while (true) {
                stopIndex++;
                v1 = (int) (Math.random() * 6 + 1); // ���������һ�����ӵĵ���
                v2 = (int) (Math.random() * 6 + 1); // ��������ڶ������ӵĵ���
                v3 = (int) (Math.random() * 6 + 1); // ����������������ӵĵ���
                // ��ʾ���ӵ�ͼƬ
                lb_dice_1.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v1 + ".png"));
                lb_dice_2.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v2 + ".png"));
                lb_dice_3.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v3 + ".png"));
                int totalValues = v1 + v2 + v3; // ���ӵĵ����ܺ�
                if (stopIndex == 50) { // ��stopIndexΪ50ʱ����ʾ��Ϣ�򣬲���ʾ���յĵ���
                    if (flag == true) {
                        if (totalValues > 9) { // ���ӵĵ���Ϊ��ʱ��ʾ����ʾ��Ϣ
                            JOptionPane.showMessageDialog(null, "�����ǣ�"
                                    + totalValues + "����\n���Ӯ������\n��Ǯ���������"
                                    + totalMoney + "Ԫ");
                        } else {
                            JOptionPane.showMessageDialog(null, "�����ǣ�"
                                    + totalValues + "��С��\nׯ��Ӯ������\n��Ǯ���������"
                                    + totalMoney + "Ԫ");
                        }
                    } else {
                        if (totalValues <= 9) { // ���ӵĵ���ΪСʱ��ʾ����ʾ��Ϣ
                            JOptionPane.showMessageDialog(null, "�����ǣ�"
                                    + totalValues + "��С��\n���Ӯ������\n��Ǯ���������"
                                    + totalMoney + "Ԫ");
                        } else {
                            JOptionPane.showMessageDialog(null, "�����ǣ�"
                                    + totalValues + "����\nׯ��Ӯ������\n��Ǯ���������"
                                    + totalMoney + "Ԫ");
                        }
                    }
                    thread = null;
                    stopIndex = 0;
                    // �������ֵ���
                    tenFlag = false;
                    twentyFlag = false;
                    fiftyFlag = false;
                    hundredFlag = false;
                    // �ӱ���ע�ı��
                    doubleFlag = false;
                    // ����ұ��
                    moneyFlag = false;
                    // ����ҳ��ۼƽ��
                    totalMoney = 0;
                    // ȷ����ע���
                    ok = false;
                    break;
                }
                try {
                    Thread.sleep(20);
                } catch (Exception ex) {
                    System.out.println(flag);
                }
            }
        }
    }
    
    /**
     * @author ������
     * ȷ�϶�ע���߳�
     */
    private class OkAnteThread implements Runnable {
        public void run() {
            boolean followFlag = true; // ʹ������ҫ�ı�Ǳ���
            while (true) {
                if (moneyFlag == true && ok == true) { // ������ȷ����ע�˾�ִ���������䣬��ʾ���˵���Ϣ
                    int count = 0;
                    while (true && followFlag == true) {
                        count++;
                        lb_follow.setVisible(true);
                        // ʵ�����ֵ���ҫЧ��
                        if (count % 2 == 1) {
                            lb_follow.setForeground(new Color(255, 0, 0));
                        } else {
                            lb_follow.setForeground(new Color(0, 0, 255));
                        }
                        if (count > 20) {
                            lb_follow.setVisible(false); // ����������ҫ�ı�ǩ
                            followFlag = false; // ��ʹ������ҫ�ı�Ǳ�������Ϊfalse
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (Exception ex) {
                            
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        System.out.println(flag);
                    }
                    lb_follow.setVisible(false); // ����������ҫ�ı�ǩ
                    okThread = null; // �ͷ��߳���Դ
                    break;
                }
            }
        }
    }
}
