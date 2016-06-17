package com.zzk;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author ������
 */
public class PigLabel extends JLabel implements Runnable {
    // �����������ʱ�䣬��Ұ���ƶ��ٶ�
    private int sleepTime = (int) (Math.random() * 300) + 30;
    private int y = 260;// �ؼ��Ĵ�ֱ����
    private int score = 10;// �ý�ɫ��Ӧ�ķ���
    private Thread thread;// �����̶߳���
    private Container parent;// �ؼ��ĸ���������
    
    /**
     * ���췽��
     */
    public PigLabel() {
        super();
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "pig.gif"));// ����Ұ��ͼƬ
        setIcon(icon);// ���ñ������ͼ��
        // �������¼�������
        addMouseListener(new MouseAdapter() {
            // ������갴���Ĵ�����
            public void mousePressed(final MouseEvent e) {
                if (!MainFrame.readyAmmo())
                    return;
                MainFrame.useAmmo();// �����ӵ�
                appScore();// ����Ϸ�ӷ�
                destory();// ���ٱ����
            }
        });
        // �������¼�������
        addComponentListener(new ComponentAdapter() {
            // ���������Сʱ
            public void componentResized(final ComponentEvent e) {
                thread.start();// �����߳�
            }
        });
        // ��ʼ���̶߳���
        thread = new Thread(this);
    }
    
    @Override
    public void run() {
        parent = null;
        int width = 0;
        while (width <= 0 || parent == null) {// ��ȡ���������
            if (parent == null)
                parent = getParent();
            else
                width = parent.getWidth();
        }
        // ���������ƶ������
        for (int i = 0; i < width && parent != null; i += 8) {
            setLocation(i, y);
            try {
                Thread.sleep(sleepTime);// ����Ƭ��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (parent != null) {
            MainFrame.appScore(-score * 10); // ��Ȼ���ٽ��۷�
        }
        destory();
    }
    
    /**
     * �������Ƴ�������ķ���
     */
    public void destory() {
        if (parent == null)
            return;
        parent.remove(this);
        parent.repaint();
        parent = null; // ͨ���������ֹ�߳�ѭ��
    }
    
    /**
     * �ӷֵķ���
     */
    private void appScore() {
        MainFrame.appScore(10);
    }
}
