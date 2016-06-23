package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ImageBrightenFrame extends JFrame {
    private BufferedImage image;// ���ڵ������ȵĻ���ͼ�����
    private BufferedImage oldImage;// ���ڴ�ŵ�������֮ǰ��ԭ����ͼ�����
    private ImageBrightenPanel imageBrightenPanel = new ImageBrightenPanel();
    
    public static void main(String args[]) {
        ImageBrightenFrame frame = new ImageBrightenFrame();
        frame.setVisible(true);
    }
    
    public ImageBrightenFrame() {
        super();
        setBounds(100, 100, 357, 276);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("����ͼƬ������");
        Image img = null;
        try {
            img = ImageIO.read(new File("src/img/image.jpg"));  // ����ͼ�����
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
        image.getGraphics().drawImage(img, 0, 0, null);// �ڻ���ͼ������ϻ���ͼ��
        oldImage = image;// �洢ԭ����ͼ����������Ժ�Ļָ�����
        getContentPane().add(imageBrightenPanel, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float a = 1.0f;// ������������
                float b = 5.0f;// ����ƫ����
                RescaleOp op = new RescaleOp(a,b,null);// ��������ָ���������Ӻ�ƫ������ RescaleOp����
                image = op.filter(image, null);// ��Դͼ���е����ݽ��������������ţ��ﵽ������Ч��
                repaint();// ���»���ͼ��
            }
        });
        button.setText("��    ��");
        panel.add(button);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float a = 1.0f;// ������������
                float b = -5.0f;// ����ƫ����
                RescaleOp op = new RescaleOp(a,b,null);// ��������ָ���������Ӻ�ƫ������ RescaleOp����
                image = op.filter(image, null);// ��Դͼ���е����ݽ��������������ţ��ﵽ�䰵��Ч��
                repaint();// ���»���ͼ��
            }
        });
        button_3.setText("��    ��");
        panel.add(button_3);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                image = oldImage;  // ��ñ���ǰ��ͼ��
                imageBrightenPanel.repaint();// ���»���ԭͼ�񣬼��ָ�Ϊ����ǰ��ͼ��
            }
        });
        button_2.setText("��    ��");
        panel.add(button_2);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        panel.add(button_1);
    }
    
    class ImageBrightenPanel extends JPanel {
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);  // ������ͼ�������Ƶ������
            }
        }
    }
}
