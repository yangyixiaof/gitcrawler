package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveMixPictureFrame extends JFrame {
    private Image img1 = null; // ����ͼ�����
    private Image img2 = null; // ����ͼ�����
    private boolean mixFlag = false;// ���ھ����Ƿ��ܺ�ͼƬ��Ϊtrueʱ�ܺ�ͼƬ
    private boolean firstOrSecondFlag = false;// Ϊfalseʱ��ʾ��һ��ͼƬ��Ϊtrueʱ��ʾ�ڶ���ͼƬ
    private MixPicturePanel mixPicturePanel = null; // ����ͼ��������
    private int panelWidth = 0;// ͼ�����Ŀ��
    private int panelHeight = 0;// ͼ�����ĸ߶�
    public static void main(String args[]) {
        SaveMixPictureFrame frame = new SaveMixPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveMixPictureFrame() {
        super();
        URL imgUrl = SaveMixPictureFrame.class.getResource("/img/img.jpg");// ��ȡͼƬ��Դ��·��
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imgUrl = SaveMixPictureFrame.class.getResource("/img/imag.jpg");// ��ȡͼƬ��Դ��·��
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        mixPicturePanel = new MixPicturePanel(); // ����ͼ��������
        this.setBounds(200, 160, 476, 336); // ���ô����С��λ��
        this.add(mixPicturePanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("�ܺ�����ͼƬ������"); // ���ô������
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = false;// ��ǲ����Ƶڶ���ͼƬ
                mixFlag = false;// ���û�ܺ�ͼƬ
                mixPicturePanel.repaint();// ����paint()�������Ƶ�һ��ͼƬ
            }
        });
        button_2.setText("��һ��");
        panel.add(button_2);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// ��ǻ��Ƶڶ���ͼƬ
                mixFlag = false;// ���û�ܺ�ͼƬ
                mixPicturePanel.repaint();// ����paint()�������Ƶ�һ��ͼƬ
            }
        });
        button_3.setText("�ڶ���");
        panel.add(button_3);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// ��ǻ��Ƶڶ���ͼƬ
                mixFlag = true;// ����ܺ�ͼƬ
                mixPicturePanel.repaint();// ����paint()�������Ƶ�һ��ͼƬ
            }
        });
        button.setText("�ܺ�ͼƬ");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!mixFlag) {
                    JOptionPane.showMessageDialog(null,"��û���ܺ�ͼƬ��");// ��ʾ��ʾ��Ϣ
                    return;
                }
                FileDialog dialog = new FileDialog(SaveMixPictureFrame.this,"����");// �����Ի���
                dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                dialog.setVisible(true);// ��ʾ����Ի���
                String path = dialog.getDirectory();// ����ļ��ı���·��
                String fileName = dialog.getFile();// ��ñ�����ļ���
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// �ļ���չ��,������
                String pathAndName = path + "\\" + fileName;// �ļ�������·��
                BufferedImage bufferImage = new BufferedImage(panelWidth, panelHeight,
                        BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// ��û�ͼ�����Ķ���
                g2.drawImage(img1, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// ����ͼ��
                    AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��ñ�ʾ͸���ȵ�AlphaComposite����
                    g2.setComposite(alpha);// ָ��AlphaComposite����
                    g2.drawImage(img2, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// ���Ƶ���͸���Ⱥ��ͼƬ
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// ������ͼ�񱣴浽����
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"����ʧ��\n"+e1.getMessage());// ��ʾ��ʾ��Ϣ
                }
            }
        });
        button_1.setText("����ͼƬ");
        panel.add(button_1);
        
        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_4.setText("��    ��");
        panel.add(button_4);
        
    }
    
    // ���������
    class MixPicturePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            panelWidth = getWidth();// ���ͼ�����Ŀ��
            panelHeight = getHeight();// ���ͼ�����ĸ߶�
            g2.drawImage(img1, 0, 0, panelWidth, panelHeight, this);// ����ͼ��
            if (mixFlag) {// ����ܺ�ͼƬ
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��ñ�ʾ͸���ȵ�AlphaComposite����
                g2.setComposite(alpha);// ָ��AlphaComposite����
            }
            if (firstOrSecondFlag) {// ��ǻ��Ƶڶ���ͼƬ
                g2.drawImage(img2, 0, 0, panelWidth, panelHeight, this);// ���Ƶ���͸���Ⱥ��ͼƬ
            }
        }
    }
}
