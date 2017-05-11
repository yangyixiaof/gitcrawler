package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class CinefilmEffectFrame extends JFrame {
    Thread thread = new Thread(new CinefileThread());
    private final JLabel ggLabel = new JLabel();
    private final JLabel label_1 = new JLabel(); // ��ʾͼƬ�ĵ�1����ǩ
    private final JLabel label_2 = new JLabel(); // ��ʾͼƬ�ĵ�2����ǩ
    private final JLabel label_3 = new JLabel(); // ��ʾͼƬ�ĵ�3����ǩ
    private final JLabel label_4 = new JLabel(); // ��ʾͼƬ�ĵ�4����ǩ
    private final JLabel label_5 = new JLabel(); // ��ʾͼƬ�ĵ�5����ǩ
    private final JLabel ffLabel = new JLabel();
    int x1 = 0; // ��1����ǩ��ʾλ�õı���
    int x2 = 98; // ��2����ǩ��ʾλ�õı���
    int x3 = 196; // ��3����ǩ��ʾλ�õı���
    int x4 = 294; // ��4����ǩ��ʾλ�õı���
    int x5 = 392; // ��5����ǩ��ʾλ�õı���
    boolean indexFlag = false; // ��ʶ��ǩ�Ƿ�ͼ�ı���
    
    public CinefilmEffectFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // �����̶߳���
            }
        });
        setTitle("��Ӱ��Ƭ��Ч"); // ���ô���ı���
        setBounds(260, 240, 400, 280); // ͼƬ�Ŀ�Ⱥ͸߶�
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // ���ô���Ĺرշ�ʽ
        
        ggLabel.setOpaque(true);
        ggLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/��Ƭ.JPG"));
        ggLabel.setText("  ");
        getContentPane().add(ggLabel, BorderLayout.NORTH);
        
        ffLabel.setText("  ");
        ffLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/��Ƭ.JPG"));
        ffLabel.setOpaque(true);
        getContentPane().add(ffLabel, BorderLayout.SOUTH);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        label_1.setBounds(0, 0, 98, 210);
        label_1.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/1.jpg"));
        label_1.setText("New JLabelfdbb");
        panel.add(label_1);
        label_2.setBounds(98, 0, 98, 210);
        label_2.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/2.jpg"));
        label_2.setText("22222222222222");
        panel.add(label_2);
        label_3.setBounds(196, 0, 98, 210);
        label_3.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/3.jpg"));
        label_3.setText("11111111111111");
        panel.add(label_3);
        label_4.setBounds(294, 0, 98, 210);
        label_4.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/4.jpg"));
        label_4.setText("New JLabelfdww");
        panel.add(label_4);
        label_5.setBounds(392, 0, 98, 210);
        label_5.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/5.jpg"));
        label_5.setText("33333333333333");
        panel.add(label_5);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        CinefilmEffectFrame frame = new CinefilmEffectFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author ������
     *         ����ʵ�ֶ������߳�
     */
    private class CinefileThread implements Runnable {
        public void run() {
            while (true) {
                x1 = x1 - 7; // ��1����ǩ����߽��7��ʹ������
                x2 = x2 - 7; // ��2����ǩ����߽��7��ʹ������
                x3 = x3 - 7; // ��3����ǩ����߽��7��ʹ������
                x4 = x4 - 7; // ��4����ǩ����߽��7��ʹ������
                x5 = x5 - 7; // ��5����ǩ����߽��7��ʹ������
                label_1.setBounds(x1, 0, 98, 210); // ���õ�1����ǩ����ʾλ��
                label_2.setBounds(x2, 0, 98, 210); // ���õ�1����ǩ����ʾλ��
                label_3.setBounds(x3, 0, 98, 210); // ���õ�1����ǩ����ʾλ��
                label_4.setBounds(x4, 0, 98, 210); // ���õ�1����ǩ����ʾλ��
                label_5.setBounds(x5, 0, 98, 210); // ���õ�1����ǩ����ʾλ��
                
                if (x1 == -98) { // ����1����ǩ����ʾλ����-98ʱִ��
                    indexFlag = !indexFlag; // �ı�indexFlag��ֵ
                    x1 = 392; // ���õ�1����ǩ����ʾλ��
                    if (indexFlag) {
                        // indexFlagΪtrueʱ�ı��ͼƬ
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/6.jpg"));
                    } else {
                        // indexFlagΪfalseʱ�ı��ͼƬ
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/1.jpg"));
                    }
                }
                if (x2 == -98) { // ����2����ǩ����ʾλ����-98ʱִ��
                    indexFlag = !indexFlag; // �ı�indexFlag��ֵ
                    x2 = 392; // ���õ�2����ǩ����ʾλ��
                    if (indexFlag) {
                        // indexFlagΪtrueʱ�ı��ͼƬ
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/7.jpg"));
                    } else {
                        // indexFlagΪfalseʱ�ı��ͼƬ
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/2.jpg"));
                    }
                }
                if (x3 == -98) { // ����3����ǩ����ʾλ����-98ʱִ��
                    indexFlag = !indexFlag; // �ı�indexFlag��ֵ
                    x3 = 392; // ���õ�3����ǩ����ʾλ��
                    if (indexFlag) {
                        // indexFlagΪtrueʱ�ı��ͼƬ
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/8.jpg"));
                    } else {
                        // indexFlagΪfalseʱ�ı��ͼƬ
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/3.jpg"));
                    }
                }
                if (x4 == -98) { // ����4����ǩ����ʾλ����-98ʱִ��
                    indexFlag = !indexFlag; // �ı�indexFlag��ֵ
                    x4 = 392; // ���õ�4����ǩ����ʾλ��
                    if (indexFlag) {
                        // indexFlagΪtrueʱ�ı��ͼƬ
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/9.jpg"));
                    } else {
                        // indexFlagΪfalseʱ�ı��ͼƬ
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/4.jpg"));
                    }
                }
                if (x5 == -98) { // ����5����ǩ����ʾλ����-98ʱִ��
                    indexFlag = !indexFlag; // �ı�indexFlag��ֵ
                    x5 = 392; // ���õ�5����ǩ����ʾλ��
                    if (indexFlag) {
                        // indexFlagΪtrueʱ�ı��ͼƬ
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/10.jpg"));
                    } else {
                        // indexFlagΪfalseʱ�ı��ͼƬ
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/5.jpg"));
                    }
                }
                try {
                    Thread.sleep(150); // �߳�˯��150����
                } catch (Exception ex) {
                    
                }
            }
        }
    }
}
