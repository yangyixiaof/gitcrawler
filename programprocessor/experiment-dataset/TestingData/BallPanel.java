package com.zzk;
import java.awt.*;
import javax.swing.JLabel;
/**
 * @author ������
 */
public class BallPanel extends JLabel implements Runnable {
    private int r = 10;// С��뾶
    private int width = r * 2;// ����
    private int height = r * 2;// ��߶�
    private Color ballColor = Color.BLUE;// Ĭ����ɫ
    public BallPanel() {
        setPreferredSize(new Dimension(width, height));// ��ʼ����С
        new Thread(this).start();// ����С����Ծ�߳�
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ballColor);// ����Ĭ����ɫ
        g.fillOval(0, 0, width, height);// �ڱ�ǩ�ϻ�������
    }
    @Override
    public void run() {
        Container parent = getParent();/// ��õ�ǰ��ǩ�ĸ�����������
        Point myPoint = getLocation();// ��ȡ��ʼλ��
        while (true) {// ѭ����ȡ����������
            if (parent == null) {
                try {
                    Thread.sleep(50);// �߳�����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myPoint = getLocation();// ��ȡ��ʼλ��
                parent = getParent();// ��õ�ǰ��ǩ�ĸ�����������
            } else {// ����Ѿ���ȡ��������
                break;// ����ѭ��
            }
        }
        int sx = myPoint.x;// X����
        int sy = myPoint.y;// y����
        int step = (int) (Math.random() * 10) % 3 + 1;// �ƶ�����
        int dx = (Math.random() * 100) >= 50 ? step : -step;// ˮƽ����ֵ
        step = (int) (Math.random() * 10) % 3 + 1;// �ƶ�����
        int dy = (Math.random() * 100) >= 50 ? step : -step;// ��ֱ����ֵ
        int stime = (int) (Math.random() * 80 + 10);// ����ƶ��ٶ�
        while (parent.isVisible()) {
            int parentWidth = parent.getWidth();// �������
            int parentHeight = parent.getHeight();// �����߶�
            setLocation(sx, sy);// ָ��С���λ��
            try {
                Thread.sleep(stime);// ͨ�����߸ı��ƶ��ٶ�
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sx = sx + dx * 5;// ˮƽ����ƫ��5������
            sy = sy + dy * 5;// ��ֱ����ƫ��5������
            // ��ⴹֱ�߽�
            if (sy > parentHeight - height || sy < 0)
                dy = -dy;// ��ֹ���곬����ֱ�߽�
            // ���ˮƽ�߽�
            if (sx > parentWidth - width || sx < 0)
                dx = -dx;// ��ֹ���곬��ˮƽ�߽�
        }
    }
}
