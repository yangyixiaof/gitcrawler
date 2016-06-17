package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.awt.AWTUtilities;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class ClockFrame extends JDialog {
    private float opqua = 0.7f;
    private ClockPanel clockPanel;
    private Point fp; // ��ҷ����֮ǰ�����λ��
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClockFrame frame = new ClockFrame();// �����������
                    frame.setVisible(true);// ��ʾ����
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * ���ִ���Ĺ��췽��
     */
    public ClockFrame() {
        super();
        setUndecorated(true);// ȡ����������
        setAlwaysOnTop(true);// �����ö�
        setTitle("���տƼ�ʯӢ��");// ���ô������
        getContentPane().setLayout(new BorderLayout());
        setBounds(100, 30, 217, 257);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        clockPanel = new ClockPanel();// ����ʱ�����
        getContentPane().add(clockPanel);
        // Ϊʱ����������갴���¼�������
        clockPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                fp = e.getPoint();
                if (e.getButton()==MouseEvent.BUTTON3){
                    System.exit(0);// �Ҽ��˳�
                }
            }
        });
        // ��ʱ�����������ҷ�¼����ƶ�����
        clockPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                JDialog frame = (JDialog) getRootPane().getParent();
                Point point = e.getLocationOnScreen();
                frame.setLocation(point.x - fp.x, point.y - fp.y);
            }
        });
        pack();
        
        addKeyListener(new KeyAdapter() {// Ϊ������Ӽ����¼�������
            public void keyPressed(final KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {// �жϰ�������
                    case KeyEvent.VK_ADD:// +���Ű����ή��͸��ͼ
                        opqua += 0.05;
                        opqua = opqua > 0.95f ? 1f : opqua;
                        break;
                    case KeyEvent.VK_SUBTRACT:// -���Ű���������͸����
                        opqua -= 0.05;
                        opqua = opqua < 0.1f ? 0.1f : opqua;
                        break;
                }
                // �����Ctrl+Shift+X�M���I�����˳�����
                if (code == KeyEvent.VK_X
                        && e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK))
                    System.exit(0);
                AWTUtilities.setWindowOpacity(ClockFrame.this, opqua);// ���´���͸����
            }
        });
        
        AWTUtilities.setWindowOpacity(this, opqua);
        Dimension screenSize = getToolkit().getScreenSize();
        double width = screenSize.getWidth();
        int x = (int) (width - getWidth() - 30);
        setLocation(x, 30);
        
        new Thread() {// �����̶߳��󣬸���ʱ��������
            @Override
            public void run() {
                try {
                    while (true) {
                        sleep(1000);// ����1��
                        clockPanel.repaint();// ���»���ʱ��������
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
