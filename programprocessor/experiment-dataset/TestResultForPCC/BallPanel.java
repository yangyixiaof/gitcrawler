package test;
import java.awt.*;
import javax.swing.JLabel;
/**
 * @author 
 */
public class BallPanel extends JLabel implements Runnable {
	private static final long serialVersionUID = -6005970672070639568L;
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
        // right 4.5
        g.fillOval(0, 0, width, height);// �ڱ�ǩ�ϻ�������
    }
    @Override
    public void run() {
        Container parent = getParent();/// ��õ�ǰ��ǩ�ĸ�����������
        // right 2
        Point myPoint = getLocation();// ��ȡ��ʼλ��
        // right 1
        while (true) {// ѭ����ȡ����������
        	// right 1
            if (parent == null) {
                try {
                	// right 1
                    Thread.sleep(50);// �߳�����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // right 3
                myPoint = getLocation();// ��ȡ��ʼλ��
                // right 1
                parent = getParent();// ��õ�ǰ��ǩ�ĸ�����������
            } else {// ����Ѿ���ȡ��������
            	// right 6
                break;// ����ѭ��
            }
        }
        int sx = myPoint.x;
        int sy = myPoint.y;
        int step = (int) (Math.random() * 10) % 3 + 1;
        int dx = (Math.random() * 100) >= 50 ? step : -step;ֵ
        step = (int) (Math.random() * 10) % 3 + 1;
        int dy = (Math.random() * 100) >= 50 ? step : -step;ֵ
        int stime = (int) (Math.random() * 80 + 10);
        while (parent.isVisible()) {
            int parentWidth = parent.getWidth();
            int parentHeight = parent.getHeight();
            setLocation(sx, sy);
            try {
                Thread.sleep(stime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sx = sx + dx * 5;
            sy = sy + dy * 5;
            if (sy > parentHeight - height || sy < 0)
                dy = -dy;
            if (sx > parentWidth - width || sx < 0)
                dx = -dx;
        }
    }
}
