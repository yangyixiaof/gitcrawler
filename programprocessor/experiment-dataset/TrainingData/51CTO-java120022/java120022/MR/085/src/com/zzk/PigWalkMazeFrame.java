package com.zzk;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class PigWalkMazeFrame extends JFrame implements KeyListener, Runnable {
    Rectangle rect1, rect2, rect3, rect4;
    int gobuttonX = 0, gobuttonY = 0;
    final JButton goButton = new JButton();
    URL url = getClass().getResource("/images/pig.png");
    ImageIcon imageIcon = new ImageIcon(url);
    final JLabel label = new JLabel();
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PigWalkMazeFrame frame = new PigWalkMazeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public PigWalkMazeFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent e) {
                goButton.requestFocus(); // ʹС���ȡ����
            }
        });
        getContentPane().setLayout(null);
        setBounds(100, 100, 488, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("С�����Թ�");
        BakcgroundPanel panel = new BakcgroundPanel();
        rect1 = new Rectangle(0, 55, 190, 40);
        rect2 = new Rectangle(190, 40, 40, 240);
        rect3 = new Rectangle(190, 180, 230, 40);
        rect4 = new Rectangle(300, 180, 40, 140);
        setResizable(false);
        panel.setLayout(null);
        panel.setBounds(0, 0, 482, 341);
        getContentPane().add(panel);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                buttonAction(e);
            }
        });
        URL url = getClass().getResource("/images/button.png");
        ImageIcon imageIcons = new ImageIcon(url);
        button.setIcon(imageIcons);
        button.setBounds(27, 100, 106, 28);
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight());
        panel.add(goButton);
        panel.add(button);
        gobuttonX = goButton.getBounds().x; // ����С��ť�ĺ�����
        gobuttonY = goButton.getBounds().y; // ����С��ť��������
        goButton.addKeyListener(this);
        goButton.setIcon(imageIcon);
        goButton.setContentAreaFilled(false); // ȡ���������
        goButton.setBorder(null); // ȡ���߿�
        url = getClass().getResource("/images/exit.png");
        imageIcons = new ImageIcon(url);
        label.setIcon(imageIcons);
        label.setBounds(300, 315, imageIcons.getIconWidth(), imageIcons
                .getIconHeight());
        panel.add(label);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if ((gobuttonY == 286)) { // ���С������������286
            Thread thread = new Thread(this);
            thread.start(); // �����߳�
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) { // ����û����������ϼ�
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20); // ����Rectangle����
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) { // �ж�С���Ƿ��߳����Թ�
                gobuttonY = gobuttonY - 2; // ���ñ�������
                goButton.setLocation(gobuttonX, gobuttonY); // ���ð�ť����
            } else { // ���С���߳����Թ�
                JOptionPane.showMessageDialog(this, "ײǽ�˰ɣ����¿�ʼ�ɣ�", "ײǽ����",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) { // �ж��û��Ƿ����¼�
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonY = gobuttonY + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "ײǽ�˰ɣ����¿�ʼ�ɣ�", "ײǽ����",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // ����û��������
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX - 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "ײǽ�˰ɣ����¿�ʼ�ɣ�", "ײǽ����",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // ����û������Ҽ�
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "ײǽ�˰ɣ����¿�ʼ�ɣ�", "ײǽ����",
                        JOptionPane.DEFAULT_OPTION);
            }
        }
    }
    
    public void buttonAction(ActionEvent e) {
        goButton.setIcon(imageIcon); // �������ð�ť����ʾͼƬ
        goButton.addKeyListener(this); // Ϊ��ť��Ӽ����¼�
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight()); // ����С��λ��
        gobuttonX = goButton.getBounds().x; // ���С��ǰλ�õ�X����
        gobuttonY = goButton.getBounds().y;// ���С��ǰλ�õ�Y����
        goButton.requestFocus(); // ���ð�ť��ȡ����
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void run() {
        URL out = getClass().getResource("/images/pigOut.png"); // ��ȡͼƬURL
        ImageIcon imageout = new ImageIcon(out); // ����ͼƬ����
        goButton.setIcon(imageout); // ����С��ť��ʾͼƬ
        goButton.setBounds(gobuttonX,
                gobuttonY - imageout.getIconHeight() + 50, imageout
                        .getIconWidth(), imageout.getIconHeight()); // �������ð�ťλ��
        goButton.removeKeyListener(this); // ��ť�Ƴ������¼�
    }
    
}
