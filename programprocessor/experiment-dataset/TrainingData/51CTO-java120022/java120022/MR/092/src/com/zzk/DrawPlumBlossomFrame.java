package com.zzk;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import com.swtdesigner.SwingResourceManager;

/**
 * @author ������
 */
public class DrawPlumBlossomFrame extends JFrame {
    public String flowerType = "flower2"; // ������״
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DrawPlumBlossomFrame frame = new DrawPlumBlossomFrame();
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
    public DrawPlumBlossomFrame() {
        super();
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("��÷��");
        setBounds(100, 100, 640, 445);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, 632, 417);
        backgroundPanel.setImage(SwingResourceManager.getImage(
                DrawPlumBlossomFrame.class, "/images/background.jpg"));
        getContentPane().add(backgroundPanel);
        
        final JPanel canvasPane = new JPanel();
        canvasPane.setLayout(null);
        canvasPane.setOpaque(false);
        canvasPane.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ����������
                    final JLabel flower = new JLabel(); // ��ʾ÷���ı�ǩ
                    flower.setIcon(SwingResourceManager.getIcon(
                            DrawPlumBlossomFrame.class, "/images/" + flowerType
                                    + ".png"));
                    if ("flower1".equals(flowerType)) { // ���õ�һ������÷���Ĵ�С��λ��
                        flower.setBounds(e.getX() - 6, e.getY() - 12, 30, 36);
                    } else if ("flower2".equals(flowerType)) { // ���õڶ�������÷���Ĵ�С��λ��
                        flower.setBounds(e.getX() - 28, e.getY() - 30, 51, 43);
                    } else if ("flower3".equals(flowerType)) { // ���õ���������÷���Ĵ�С��λ��
                        flower.setBounds(e.getX() - 5, e.getY() - 15, 30, 23);
                    } else { // ���õ���������÷���Ĵ�С��λ��
                        flower.setBounds(e.getX() - 29, e.getY() - 25, 58, 51);
                    }
                    canvasPane.add(flower); // ���÷��
                    canvasPane.repaint(); // �ػ����
                } else if (e.getModifiers() == InputEvent.BUTTON3_MASK) { // �����Ҽ�
                    Component at = canvasPane.getComponentAt(e.getPoint()); // ��ȡ���λ�õ����
                    if (at instanceof JLabel) { // �ж��Ƿ�ΪJLabel
                        canvasPane.remove(at); // �Ƴ����
                        canvasPane.repaint(); // �ػ����
                    }
                }
            }
        });
        canvasPane.setBounds(160, 0, 462, 345);
        backgroundPanel.add(canvasPane);
        
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(null);
        panel.setBounds(10, 112, 144, 159);
        backgroundPanel.add(panel);
        
        final JLabel flower1 = new JLabel();
        flower1.setBounds(30, 85, 30, 36);
        panel.add(flower1);
        flower1.setIcon(SwingResourceManager.getIcon(
                DrawPlumBlossomFrame.class, "/images/flower1.png"));
        
        final JLabel flower2 = new JLabel();
        flower2.setBounds(30, 35, 51, 43);
        panel.add(flower2);
        flower2.setIcon(SwingResourceManager.getIcon(
                DrawPlumBlossomFrame.class, "/images/flower2.png"));
        
        final JLabel flower3 = new JLabel();
        flower3.setBounds(85, 45, 30, 23);
        panel.add(flower3);
        flower3.setIcon(SwingResourceManager.getIcon(
                DrawPlumBlossomFrame.class, "/images/flower3.png"));
        
        final JLabel flower4 = new JLabel();
        flower4.setIcon(SwingResourceManager.getIcon(
                DrawPlumBlossomFrame.class, "/images/flower4.png"));
        flower4.setBounds(65, 75, 58, 51);
        panel.add(flower4);
        //
        flower1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                flowerType = "flower1"; // ���û�������Ϊflower1
                flower1.setBorder(new EtchedBorder(EtchedBorder.LOWERED)); // ΪJLabel��ӱ߿�
                flower2.setBorder(null); // ȥ��flower2�ı߿�
                flower3.setBorder(null); // ȥ��flower3�ı߿�
                flower4.setBorder(null); // ȥ��flower4�ı߿�
            }
        });
        
        flower2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                flowerType = "flower2"; // ���û�������Ϊflower2
                flower2.setBorder(new EtchedBorder(EtchedBorder.LOWERED)); // ΪJLabel��ӱ߿�
                flower3.setBorder(null); // ȥ��flower3�ı߿�
                flower1.setBorder(null); // ȥ��flower1�ı߿�
                flower4.setBorder(null); // ȥ��flower4�ı߿�
            }
        });
        
        flower3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                flowerType = "flower3"; // ���û�������Ϊflower3
                flower3.setBorder(new EtchedBorder(EtchedBorder.LOWERED)); // ΪJLabel��ӱ߿�
                flower2.setBorder(null);
                flower1.setBorder(null);
                flower4.setBorder(null);
            }
        });
        
        flower4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                flowerType = "flower4"; // ���û�������Ϊflower3
                flower4.setBorder(new EtchedBorder(EtchedBorder.LOWERED)); // ΪJLabel��ӱ߿�
                flower3.setBorder(null); // ȥ��flower3�ı߿�
                flower2.setBorder(null); // ȥ��flower2�ı߿�
                flower1.setBorder(null); // ȥ��flower1�ı߿�
            }
        });
    }
}
