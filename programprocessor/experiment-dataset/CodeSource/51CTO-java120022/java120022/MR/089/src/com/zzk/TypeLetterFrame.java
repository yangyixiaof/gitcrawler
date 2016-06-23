package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author ������
 */
public class TypeLetterFrame extends JFrame {
    private JLabel lb_ok; // ��ʾ��ȷ�ʵı�ǩ
    private JLabel lb_speed; // ��ʾ�����ٶȵı�ǩ
    // ��������������
    private final BackgroundPanel backgroundPanel = new BackgroundPanel();
    
    private RandomBuildLetter buildLetter = new RandomBuildLetter();// �������������ĸ����Ķ���
    private JLabel[] labels = null; // ������ǩ
    private Vector<String> vector = new Vector<String>(); // �����洢׼��������ĸ������
    final JLabel label = new JLabel();
    private Date startTime = new Date();
    private int totalSeconds = 0; // ���ֵ�������
    private int totalOkLetters = 0; // ����ȷ����ĸ����
    private int totalLetters = 0; // ������ĸ����
    private int speed = 0; // �����ٶ�
    
    public TypeLetterFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("����ĸ��Ϸ");
        setBounds(100, 80, 520, 400);
        
        addKeyListener(new GameListener()); // Ϊ������Ӱ���������
        
        backgroundPanel.setImage(SwingResourceManager.getImage(
                TypeLetterFrame.class, "/image/background.jpg"));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        label.setBounds(10, 10, 157, 18);
        backgroundPanel.add(label);
        
        lb_speed = new JLabel();
        lb_speed.setBounds(173, 10, 157, 18);
        backgroundPanel.add(lb_speed);
        
        lb_ok = new JLabel();
        lb_ok.setBounds(345, 10, 157, 18);
        backgroundPanel.add(lb_ok);
        addLetter();
        Thread th = new Thread(new MoveLetterThread());
        th.start();
    }
    
    private void addLetter() {
        int seed = 10; // ���ñ�ǩ֮��ƫ�����ı���
        // ����RandomBuildLetter��ķ����������8����������ֵ�����飬��8��A-Z֮����ĸ��ASCIIֵ
        int[] letters = buildLetter.getLetter(8);
        labels = new JLabel[letters.length]; // ������ʾ����ĸƻ���ı�ǩ����
        // ʵ������ǩ�����ÿ������
        for (int i = 0; i < letters.length; i++) {
            int value = letters[i]; // �������letters�е�ֵ
            char c = (char) value; // ������letters�е�ֵת��Ϊ�ַ�
            String s = String.valueOf(c); // ���ַ�ת��Ϊ�ַ���
            labels[i] = new JLabel(); // ʵ������ǩ����
            labels[i].setToolTipText(s); // ���ñ�ǩ����ʾ�ı�
            // ���ñ�ǩ��ʾ��ͼƬ��������ĸ��ƻ��
            labels[i].setIcon(SwingResourceManager.getIcon(
                    TypeLetterFrame.class, "/icon/" + s + ".png"));
            int x = (int) (Math.random() * 60) + seed; // ���������ǩ��ʾλ�õĺ�����
            int y = (int) (Math.random() * 80); // ���������ǩ��ʾλ�õ�������
            labels[i].setBounds(x, y, 100, 30); // ���ñ�ǩ����ʾλ�úʹ�С
            backgroundPanel.add(labels[i]); // ����ǩ��ӵ����������
            seed += 60; // ������ǩ֮���ƫ����
            vector.add(s); // ���ַ�����ĸ��������������
            totalLetters++; // ���������ĸ���ܸ���
        }
    }
    
    /**
     * @author ������
     *         ʹ��ĸ�ƶ����߳�
     */
    private class MoveLetterThread implements Runnable {
        int seed = 10; // �����ǩ֮��ƫ�����ı���
        
        public void run() {
            while (true) {
                // ����ʾƻ����ĸ�ı�ǩ������в���
                for (int i = 0; i < labels.length; i++) {
                    // ���ñ�ǩ����ʾλ��
                    labels[i].setLocation(labels[i].getX(), labels[i].getY()
                            + (int) (Math.random() * labels[i].getHeight()));
                    if (labels[i].getY() > 400) { // ��ǩ���������400
                        String oldValue = labels[i].getToolTipText(); // ��ñ�ǩ��ԭ������ʾ�ı�
                        int value = buildLetter.getRandomLetter(); // �������A-Z��ASCIIֵ
                        char c = (char) value; // ת������ĸ�ַ�
                        String s = String.valueOf(c); // ����ĸ�ַ�ת��Ϊ�ַ���
                        labels[i].setToolTipText(s); // �����µ���ʾ�ı�
                        // ����ͼ�꣬������ĸ��ƻ��
                        labels[i].setIcon(SwingResourceManager.getIcon(
                                TypeLetterFrame.class, "/icon/" + s + ".png"));
                        totalLetters++; // ���������ĸ���ܸ���
                        labels[i].setVisible(true); // ��ʾ��ǩ
                        int x = (int) (Math.random() * 60) + seed; // ���������ǩ��ʾλ�õĺ�����
                        int y = (int) (Math.random() * 80); // ���������ǩ��ʾλ�õ�������
                        labels[i].setLocation(x, y); // ���ñ�ǩ����ʾλ��
                        seed += 60; // ������ǩ֮���ƫ����
                        if (seed >= 490) { // ���seed����490
                            int m = 0; // ����Ҫ���������Ƴ��������
                            for (int j = 0; j < vector.size(); j++) { // ������������
                                String str = vector.get(j).toString(); // ��������е�Ԫ��
                                if (str.equals(oldValue)) { // ������ǩ��ԭ������ʾ�ı���ͬ
                                    m = j; // Ϊ����m��ֵ����Ҫ��������ɾ���������
                                }
                            }
                            vector.remove(m); // ���������Ƴ�Ԫ��
                            seed = 10; // �������ñ�ǩ֮���ƫ����
                        }
                        vector.add(s); // ������������µ�Ԫ��
                    }
                    // �ܵ�����
                    int second0 = (int) ((new Date().getTime() - startTime
                            .getTime()) / 1000);
                    int second = second0 % 60; // ʱ���е���
                    int minute0 = new Double(second0 / 60).intValue(); // �ܵķ���
                    totalSeconds = second0; // ����ܵ�����
                    int minute = minute0 % 60; // ʱ���еķ���
                    int hour = new Double(minute0 / 60).intValue(); // �ܵ�Сʱ��
                    // �ڱ�ǩ����ʾʱ��
                    label.setText("��ʱ��" + String.valueOf(hour) + ":"
                            + String.valueOf(minute) + ":"
                            + String.valueOf(second));
                    speed = (int) (totalOkLetters / (totalSeconds / 60.0f)); // ��������ٶ�
                    // �ڱ�ǩ����ʾ�����¼�
                    lb_speed.setText("�ٶȣ���/���ӣ�" + String.valueOf(speed));
                    int ok = 0; // �����ȷ�ʵı���
                    if (totalLetters != 0) {
                        ok = (totalOkLetters * 100 / totalLetters); // ������ȷ��
                    }
                    lb_ok.setText("��ȷ�ʣ��ٷֱȣ�" + String.valueOf(ok)); // �ڱ�ǩ����ʾ��ȷ��
                }
                try {
                    Thread.sleep(200); // ����200����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @author ������
     *         ���̰�����������
     */
    private class GameListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode(); // ��ð�����ֵ
            if (code >= 65 && code <= 90) { // �Ǵ�д��ĸ
                char c = (char) code; // ת��Ϊ��ĸ�ַ�
                String s = String.valueOf(c); // ת��Ϊ�ַ���
                for (int i = 0; i < vector.size(); i++) { // ������������
                    String str = vector.get(i).toString(); // ��������е�Ԫ��
                    if (s.equals(str)) { // �����ַ��������е���ͬ
                        for (int j = 0; j < labels.length; j++) { // ������ǩ����
                            if (str.equals(labels[j].getToolTipText())) { // �����ַ����ǩ��ʾ�ı���ͬ
                                labels[j].setVisible(false); // ���ظñ�ǩ
                                int value = buildLetter.getRandomLetter(); // ������ַ���ASCIIֵ
                                char ch = (char) value; // ת��Ϊ�ַ�
                                String ss = String.valueOf(ch); // ת��Ϊ�ַ���
                                labels[j].setToolTipText(ss); // �������ñ�ǩ����ʾ�ı�
                                // ���ñ�ǩ����ʾ����ĸƻ��ͼƬ
                                labels[j].setIcon(SwingResourceManager.getIcon(
                                        TypeLetterFrame.class, "/icon/" + ss
                                                + ".png"));
                                totalLetters++; // ���������ĸ������
                                labels[j].setVisible(true); // ��ʾ��ǩ���
                                int x = (int) (Math.random() * 20 + labels[j]
                                        .getX()); // ������ɱ�ǩ����ĺ�����
                                int y = (int) (Math.random() * -80); // ������ɱ�ǩ�����������
                                labels[j].setLocation(x, y); // ���ñ�ǩ����ʾλ��
                                vector.add(ss); // �����ַ�����ӵ�������
                                break; // ����ѭ��
                            }
                        }
                        int x = 0; // ����Ҫ���������Ƴ��������
                        for (int k = 0; k < vector.size(); k++) { // ������������
                            String oldValue = vector.get(k).toString(); // ��������е�Ԫ��
                            if (str.equals(oldValue)) { // ������ǩ��ԭ������ʾ�ı���ͬ
                                x = k; // Ϊ����x��ֵ����Ҫ��������ɾ���������
                            }
                        }
                        vector.remove(x); // ���������Ƴ�Ԫ��
                        totalOkLetters++; // ����ȷ����ĸ����
                        break;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        TypeLetterFrame frame = new TypeLetterFrame();
        frame.setVisible(true);
    }
    
}
