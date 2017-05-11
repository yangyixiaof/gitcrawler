package com.mingrisoft;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * ��������������
 * 
 * @author ZhongWei Lee
 */
public class BackgroundPanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1642802227586560186L;
    /**
     * ����ͼƬ
     */
    private Image image;
    
    /**
     * ���췽��
     */
    public BackgroundPanel() {
        super();
        setOpaque(false);
        setLayout(null);
    }
    
    /**
     * ����ͼƬ�ķ���
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
    @Override
    protected void paintComponent(Graphics g) {// ��д����������
        if (image != null) {
            g.drawImage(image, 0, 0, 400, 406, this);// ����ͼƬ�������С��ͬ
        }
        super.paintComponent(g);// ִ�г��෽��
    }
}
