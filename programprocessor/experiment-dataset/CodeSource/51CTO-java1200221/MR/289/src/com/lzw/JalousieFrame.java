package com.lzw;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JalousieFrame extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JalousieFrame frame = new JalousieFrame();
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
    public JalousieFrame() {
        setTitle("�����Ҷ���ǳ���Ч");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 452, 221);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u767B\u5F55\u6A21\u5757");
        label.setOpaque(true);
        label.setBackground(new Color(245, 222, 179));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 30));
        label.setBounds(6, 6, 422, 72);
        contentPane.add(label);
        
        JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        label_1.setBounds(31, 90, 55, 18);
        contentPane.add(label_1);
        
        JLabel label_2 = new JLabel("\u5BC6\u3000\u7801\uFF1A");
        label_2.setBounds(31, 134, 55, 18);
        contentPane.add(label_2);
        
        textField = new JTextField();
        textField.setBounds(83, 84, 184, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(83, 128, 184, 30);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JButton button = new JButton("\u767B\u5F55");
        button.setBounds(304, 84, 90, 30);
        contentPane.add(button);
        
        JButton button_1 = new JButton("\u5173\u95ED");
        button_1.setBounds(304, 128, 90, 30);
        contentPane.add(button_1);
        setLocationRelativeTo(null);
        JalousiePanel panel = new JalousiePanel();
    }
    
    class JalousiePanel extends JPanel {
        final int step = 30;// ��ҵ������߶�
        int hei = step;// ��Ҷ������߶�
        int recNum = 0;// ��Ҷ��������
        private Timer timer;
        
        public JalousiePanel() {
            setOpaque(false);// ���͸��
            final Component oldPanel = getGlassPane();// ����ԭ�в������
            final boolean visible = oldPanel.isVisible();
            setGlassPane(JalousiePanel.this);// �ѵ�ǰ�������Ϊ���岣�����
            getGlassPane().setVisible(true);// ��ʾ�������
            // ��ʼ��Timer�ؼ�
            timer = new Timer(300, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setGlassPane(JalousiePanel.this);// ���õ�ǰ���Ϊ���岣�����
                    getGlassPane().setVisible(true);// ��ʾ�������
                    if (hei-- > 0) {// �ݼ���Ҷ������߶�
                        repaint();// �ػ����
                    } else {// �����Ҷ���߶Ƚ���С��0
                        timer.stop();// ֹͣTimer�ؼ�
                        setGlassPane(oldPanel);// �ָ�ԭ�в������
                        hei = step;// ��ʼ����Ҷ���߶�
                        getGlassPane().setVisible(visible);// �ָ����������ʾ״̬
                    }
                }
            });
            // ��ӿؼ����¼�������
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    fillJalousie();// �ؼ���ʾʱ���õķ���
                }
                
                private void fillJalousie() {
                    Dimension size = getSize();// ��ȡ����ؼ���С
                    recNum = (size.height - 1) / step + 1;// �����Ҷ������
                    timer.start();// ����Timer�ؼ�
                }
                
                @Override
                public void componentResized(ComponentEvent e) {
                    fillJalousie();// �ؼ�������Сʱ���õķ���
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g1) {
            Graphics2D g = (Graphics2D) g1;// ��ȡ2D��ͼ����
            g.setColor(Color.BLUE);// ���û�ͼǰ��ɫ
            // ���û�ͼ͸����
            g.setComposite(AlphaComposite.SrcOver.derive(0.5f));
            for (int i = 0; i < recNum; i++) {
                // �������а�ҵ��
                g.fillRect(0, i * step, getWidth(), hei);
            }
            super.paintComponent(g);
        }
    }
}
