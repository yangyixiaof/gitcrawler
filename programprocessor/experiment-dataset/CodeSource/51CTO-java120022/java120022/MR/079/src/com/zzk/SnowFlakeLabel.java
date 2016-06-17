package com.zzk;
import java.awt.*;
import javax.swing.*;
/**
 * @author ������
 */
public class SnowFlakeLabel extends JLabel implements Runnable {
    private final static ImageIcon snow = new ImageIcon(SnowFlakeLabel.class
            .getResource("/image/snowflake.png"));
    private int width = snow.getIconWidth();// ���
    private int height = snow.getIconHeight();// �߶�
    /**
     * ���췽��
     */
    public SnowFlakeLabel() {
        setSize(new Dimension(width, height));// ��ʼ����С
        setIcon(snow);// ָ��ͼ��
        new Thread(this).start();// �����������߳�
    }
    public void run() {
        Container parent = getParent();// ��ȡ����������
        Point myPoint = getLocation();// ��ȡ��ʼλ��
        while (true) {// ѭ����ȡ����������
            if (parent == null) {
                try {
                    Thread.sleep(50);// �߳�����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myPoint = getLocation();// ��ȡ��ʼλ��
                parent = getParent();// ��ȡ����������
            } else {// ����Ѿ���ȡ��������
                break;// ����ѭ��
            }
        }
        int sx = myPoint.x;// X����
        int sy = myPoint.y;// Y����
        int stime = (int) (Math.random() * 30 + 10);// ����ƶ��ٶ�
        int parentHeight = parent.getHeight();// �����߶�
        while (parent.isVisible() && sy < parentHeight - height) {
            setLocation(sx, sy);// ָ��λ��
            try {
                Thread.sleep(stime);// �߳�����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sy += 2;// ��ֱƫ��2������
        }
    }
}
