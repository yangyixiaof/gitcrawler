package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TranslucenceImageFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private TranslucenceImagePanel translucencePanel = null; // ����ͼ��������
    private AlphaComposite alpha = AlphaComposite.SrcOver.derive(1.0f);// ������ʾ��͸����AlphaComposite����
    public static void main(String args[]) {
        TranslucenceImageFrame frame = new TranslucenceImageFrame();
        frame.setVisible(true);
    }
    
    public TranslucenceImageFrame() {
        super();
        URL imgUrl = TranslucenceImageFrame.class.getResource("/img/imag.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        translucencePanel = new TranslucenceImagePanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(translucencePanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ��͸����Ч"); // ���ô������

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(0.5f);// ��ñ�ʾ��͸����AlphaComposite����
                translucencePanel.repaint();// ����paint()����
            }
        });
        button.setText("��͸��");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(1.0f);// ��ñ�ʾ��͸����AlphaComposite����
                translucencePanel.repaint();// ����paint()����
            }
        });
        button_1.setText("��͸��");
        panel.add(button_1);
    }
    
    // ���������
    class TranslucenceImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            g2.clearRect(0, 0,  getWidth(), getHeight());// �����ͼ�����ĵ�����
            g2.setComposite(alpha);// ָ��AlphaComposite����
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// ����ͼ��
        }
        
    }
}
