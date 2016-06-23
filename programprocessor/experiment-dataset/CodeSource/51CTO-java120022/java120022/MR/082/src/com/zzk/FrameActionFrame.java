package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class FrameActionFrame extends JFrame {
    Thread thread = new Thread(new ActionThread());
    boolean flag = true; // ���ڱ�ʶ�������š���ͣ�ͼ����ĳ�Ա����
    boolean stop = false; // ���ڱ�ʶ�������ź�ֹͣ�ĳ�Ա����
    final JLabel label = new JLabel(); // ��ʾͼƬ�ı�ǩ
    
    public FrameActionFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // �����̣߳��ڴ����ʱ���Ŷ���
            }
        });
        setTitle("֡����Ч��");
        setBounds(260, 240, 324, 227); // ͼƬ�Ŀ�Ⱥ͸߶�392,208
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);// ��ǩ����ˮƽ����
        label.setIcon(SwingResourceManager.getIcon(FrameActionFrame.class,
                "/image/1.gif"));
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (thread == null) {
                    thread = new Thread(new ActionThread()); // ����߳�Ϊ�գ��򴴽��̶߳���
                }
                if (!thread.isAlive()) {
                    // ����̲߳��ǻ�߳���ִ������Ĵ��룬�����̵߳�ִ��
                    stop = false;
                    flag = true;
                    thread.start();
                }
            }
        });
        button.setText("��  ��");
        panel.add(button);
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = false; // ��ͣ��������
            }
        });
        button_1.setText("��  ͣ");
        panel.add(button_1);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = true; // �������Ŷ���
            }
        });
        button_3.setText("��  ��");
        panel.add(button_3);
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                stop = true; // ֹͣ���Ŷ���
            }
        });
        button_2.setText("ͣ  ֹ");
        panel.add(button_2);
    }
    
    public static void main(String[] args) {
        FrameActionFrame frame = new FrameActionFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author ������
     *         ����ʵ�ֶ������߳�
     */
    private class ActionThread implements Runnable {
        private int index = 1; // ���ڿ���ͼ���ļ������ļ���
        public void run() {
            while (true) {
                if (stop) {
                    thread = null; // �����߳�
                    break; // ����ѭ���������̵߳�ִ��
                }
                if (flag) {
                    String picture = "/image/"; // ����ͼƬ���λ�ú��ļ����ı���
                    index++;
                    if (index <= 8) {
                        picture = picture + index + ".jpg"; // ͨ���������ͼƬ��λ�ú��ļ���
                    } else {
                        index = 1;
                        picture = picture + index + ".jpg"; // ͨ���������ͼƬ��λ�ú��ļ���
                    }
                    // �ı��ǩ����ʾ��ͼƬʵ�ֶ���Ч��
                    label.setIcon(SwingResourceManager.getIcon(
                            FrameActionFrame.class, picture));
                    try {
                        Thread.sleep(200); // �߳�˯��200����
                    } catch (Exception ex) {
                        
                    }
                }
            }
        }
    }
}
