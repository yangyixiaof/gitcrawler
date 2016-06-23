package com.lzw.login;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ProgressPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JProgressBar jProgressBar = null;// ������
    private BufferedImage bgimage;// ����ͼƬ
    private JLabel jLabel = null;// ��ǩ�ؼ�
    
    /**
     * ���췽��
     */
    public ProgressPanel() {
        super();
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        jLabel = new JLabel();// ��ʼ����ǩ�ؼ�
        jLabel.setText("���ڵ�¼ϵͳ����");
        // ��������
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        // ����ǰ��ɫ
        jLabel.setForeground(new Color(0x28629e));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 50, 0, 50);
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridy = 1;
        // ���ò��ֹ�����
        this.setLayout(new GridBagLayout());
        this.setSize(300, 200);// ���ý����С
        // ����ǰ��ɫ
        this.setForeground(Color.white);
        this.setOpaque(false);
        // ��ӵ�¼������嵽���
        this.add(getJProgressBar(), gridBagConstraints);
        // ��ӵ�¼��Ϣ��ǩ�����
        this.add(jLabel, gridBagConstraints2);
    }
    
    /**
     * ��ʼ���������ؼ�
     * 
     * @return javax.swing.JProgressBar
     */
    private JProgressBar getJProgressBar() {
        if (jProgressBar == null) {
            jProgressBar = new JProgressBar();
            // ���ý�����Ϊ��ȷ��״̬
            jProgressBar.setIndeterminate(true);
        }
        return jProgressBar;
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();// ת��Ϊ2D��ͼ������
        g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));// ����͸���ϳɹ���
        g2.setPaint(Color.GREEN);// ʹ����ɫǰ��ɫ
        g2.fillRect(0, 0, getWidth(), getHeight());// ���ư�͸������
        g2.dispose();
        super.paint(g);// ִ�и����ͼ����
    }
}
