package com.lzw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class MouseZoomButton extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MouseZoomButton frame = new MouseZoomButton();
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
    public MouseZoomButton() {
        setTitle("��꾭��ʱ��ť�Ŵ�Ч��");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 449, 241);// ���ô����С��λ��
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        // ���������ǩ�ؼ�
        JLabel label = new JLabel("<html><body align=center>���Ƿ�ϲ��ʹ��Java"
                + "������<br>��дӦ�ó���</body></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);// ��ǩ�ı�����
        label.setFont(new Font("SansSerif", Font.PLAIN, 32));
        label.setBounds(6, 6, 421, 106);
        contentPane.add(label);
        JButton button = new JButton("ϲ��");// ������ť�ؼ�
        MouseAdapter mouseAdapter = new MouseAdapter() {// ��������¼�������
            private Rectangle sourceRec;// �������ζ���
            
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();// ��ȡ�¼�Դ��ť
                sourceRec = button.getBounds();// ���水ť��С
                button.setBounds(sourceRec.x - 10, sourceRec.y - 10,
                        sourceRec.width + 20, sourceRec.height + 20);// �Ѱ�ť�Ŵ�
                super.mouseEntered(e);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();// ��ȡ�¼�Դ��ť
                if (sourceRec != null) {// ����б��ݾ����������ָ���ť��С
                    button.setBounds(sourceRec);// ���ð�ť��С
                }
                super.mouseExited(e);
            }
            
        };
        button.addMouseListener(mouseAdapter);// Ϊ��ť����¼�������
        button.setBounds(59, 145, 90, 30);// ���ð�ť��С
        contentPane.add(button);
        JButton button_1 = new JButton("��ϲ��");// ������ť�ؼ�
        button_1.setBounds(259, 145, 90, 30);// ɫ���ư�ť��ʼ��С
        button_1.addMouseListener(mouseAdapter);// Ϊ��ť����¼�������
        contentPane.add(button_1);
    }
}
