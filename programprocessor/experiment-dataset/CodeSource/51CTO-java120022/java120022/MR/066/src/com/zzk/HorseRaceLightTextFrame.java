package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HorseRaceLightTextFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private HorseRaceLightTextPanel horseRaceLightTextPanel = new HorseRaceLightTextPanel();
    public static void main(String args[]) {
                    HorseRaceLightTextFrame frame = new HorseRaceLightTextFrame();
                    frame.setVisible(true);
    }
    
    public HorseRaceLightTextFrame() {
        super();
        setTitle("�����������Ч");
        setBounds(200, 160, 360, 237);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = HorseRaceLightTextFrame.class
                .getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        getContentPane().add(horseRaceLightTextPanel);
        Thread thread = new Thread(horseRaceLightTextPanel);// �����̶߳���
        thread.start();// �����̶߳���
    }
    
    class HorseRaceLightTextPanel extends JPanel implements Runnable { // �����ڲ������
        String value = "ȫ�ܱ�̴ʵ䣬�ҵ�ѧϰר�ҡ�";// �洢���Ƶ�����
        char[] drawChar = value.toCharArray();// ����������ת��Ϊ�ַ�����
        int[] x = new int[drawChar.length];// �洢ÿ���ַ����Ƶ�x���������
        int y = 100;// �洢���Ƶ��y����
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �����ͼ�����ĵ�����
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
            Font font = new Font("���Ŀ���", Font.BOLD, 20);// �����������
            g.setFont(font);// ָ������
            g.setColor(Color.RED);// ָ����ɫ
            for (int j = drawChar.length-1; j >=0; j--){
               g.drawString(drawChar[drawChar.length-1-j]+"",x[j] , y);// �����ı�
            }
        }
        public void run() {
            try {
                boolean flag = false;// Ϊfalseʱ��ʾ��һ��ִ�У�x������еȱȵ�����������еȲ����
                while (true) { // ��ȡ����
                    Thread.sleep(300); // ��ǰ�߳�����300����
                    for (int i = drawChar.length-1; i >=0 ; i--) {
                        if (!flag){
                            x[i]=x[i]+20*i;// x������еȱȵ���
                        } else {
                            x[i]=x[i]+20;// x������еȲ����
                        }
                        if (x[i]>=360 - 20){// ���ڴ����ȼ�20��ֵʱ
                            x[i] = 0;       // x����Ϊ0
                        }
                    }
                    repaint();// ����paint()����
                    if (!flag) {
                        flag = true;// ��ֵΪtrue
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
