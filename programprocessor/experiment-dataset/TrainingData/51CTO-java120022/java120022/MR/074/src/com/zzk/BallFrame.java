package com.zzk;

import javax.swing.*;

/**
 * @author ������
 */
public class BallFrame extends JFrame {
    private JPanel panel = null;// �������
    private BallPanel ballPanel = null;// �����ṩһ��С��
    
    public static void main(String[] args) {
        BallFrame thisClass = new BallFrame();
        thisClass.setVisible(true);
    }
    
    /**
     * ���췽��
     */
    public BallFrame() {
        super();
        setSize(320, 223);// ���ô����С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);// ��ֹ���������С
        setTitle("���浯�򶯻�");// ���ô�������ı�
        ballPanel = new BallPanel();// ����С��
        ballPanel.setBounds(121, 67, 20, 20);// ����С��λ�����С
        panel = (JPanel) getContentPane();// ��ô�����������
        panel.setLayout(null);// �����������ʹ��null����
        panel.add(ballPanel, null);// ���С�򵽴�����������
    }
}
