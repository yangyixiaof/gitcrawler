package com.zzk;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author ������
 */
public class BirdLabel extends JLabel implements Runnable {
    /**
     * �ؼ��Ŀؼ��¼�������
     * @author ������
     */
    private final class ComponentAction extends ComponentAdapter {
        public void componentResized(final ComponentEvent e) {
            thread.start();// �߳�����
        }
    }
    
    /**
     * �ؼ�������¼�������
     * 
     * @author ������
     */
    private final class MouseAction extends MouseAdapter {
        public void mousePressed(final MouseEvent e) {
            if (!MainFrame.readyAmmo())// ����ӵ�û��׼����
                return;// ʲôҲ����
            MainFrame.useAmmo();// �����ӵ�
            appScore();// �ӷ�
            destory();// ���ٱ����
        }
    }
    
    // ��������̵߳�����ʱ�䣬������С���ƶ��ٶ�
    private int sleepTime = (int) (Math.random() * 300) + 5;
    private int y = 100;
    private Thread thread;// ���߳���Ϊ��Ա����
    private Container parent;
    private int score = 15;// �����ɫ��Ӧ�ķ���
    
    /**
     * ���췽��
     */
    public BirdLabel() {
        super();
        // ����С��ͼ�����
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "bird.gif"));
        setIcon(icon);// ���ÿؼ�ͼ��
        addMouseListener(new MouseAction());// �������¼�������
        // ��ӿؼ��¼�������
        addComponentListener(new ComponentAction());
        thread = new Thread(this);// �����̶߳���
    }
    
    @Override
    public void run() {
        parent = null;
        int width = 0;
        try {
            while (width <= 0 || parent == null) {
                if (parent == null){
                    parent = getParent();// ��ȡ������
                } else {
                    width = parent.getWidth();// ��ȡ�������Ŀ��
                }
                Thread.sleep(10);
            }
            for (int i = width; i > 0 && parent != null; i -= 8) {
                setLocation(i, y);// ���������ƶ������λ��
                Thread.sleep(sleepTime);// ����Ƭ��
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (parent != null) {
            MainFrame.appScore(-score * 10); // ��Ȼ���ٽ��۷�
        }
        destory();// �ƶ���ϣ����ٱ����
    }
    
    /**
     * �������Ƴ�������ķ���
     */
    public void destory() {
        if (parent == null)
            return;
        parent.remove(this);// �Ӹ��������Ƴ�����
        parent.repaint();
        parent = null; // ͨ���������ֹ�߳�ѭ��
    }
    
    /**
     * �ӷֵķ���
     */
    private void appScore() {
        MainFrame.appScore(15);
    }
}
