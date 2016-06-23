package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
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
public class SaveWatermarkPictureFrame extends JFrame {
    private boolean watermark = false;// ˮӡ��ǣ�Ϊtrueʱ����ˮӡ
    private Image img = null; // ����ͼ�����
    private DrawWatermarkPanel watermarkPanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        SaveWatermarkPictureFrame frame = new SaveWatermarkPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveWatermarkPictureFrame() {
        super();
        setTitle("ΪͼƬ���ˮӡ������"); // ���ô������
        URL imgUrl = SaveWatermarkPictureFrame.class
                .getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        watermarkPanel = new DrawWatermarkPanel(); // ����ͼ��������
        this.setBounds(200, 160, 420, 320); // ���ô����С��λ��
        this.add(watermarkPanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                watermark = true;
                watermarkPanel.repaint();// ����paint()��������ˮӡ
            }
        });
        button.setText("���ˮӡ");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!watermark) {
                    JOptionPane.showMessageDialog(null,"��û��ΪͼƬ���ˮӡ��");// ��ʾ��ʾ��Ϣ
                    return;
                }
                FileDialog dialog = new FileDialog(SaveWatermarkPictureFrame.this,"����");// �����Ի���
                dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                dialog.setVisible(true);// ��ʾ����Ի���
                String path = dialog.getDirectory();// ����ļ��ı���·��
                String fileName = dialog.getFile();// ��ñ�����ļ���
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// �ļ���չ��,������
                String pathAndName = path + "\\" + fileName;// �ļ�������·��
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveWatermarkPictureFrame.this), img
                        .getHeight(SaveWatermarkPictureFrame.this),
                        BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// ��û�ͼ�����Ķ���
                g2.drawImage(img, 0, 0, img.getWidth(SaveWatermarkPictureFrame.this), 
                        img.getHeight(SaveWatermarkPictureFrame.this), null);// �ڻ���ͼ���ϻ���ͼ��
                g2.rotate(Math.toRadians(-30));// ��ת��ͼ�����Ķ���
                Font font = new Font("����", Font.BOLD, 72);// �����������
                g2.setFont(font);// ָ������
                g2.setColor(Color.RED);// ָ����ɫ
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��ñ�ʾ͸���ȵ�AlphaComposite����
                g2.setComposite(alpha);// ָ��AlphaComposite����
                g2.drawString("��̴ʵ�", -30, 240);// �����ı�,ʵ��ˮӡ
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
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_2.setText("��    ��");
        panel.add(button_2);
    }
    
    // ���������
    class DrawWatermarkPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
            if (watermark) {
                g2.rotate(Math.toRadians(-30));// ��ת��ͼ�����Ķ���
                Font font = new Font("����", Font.BOLD, 72);// �����������
                g2.setFont(font);// ָ������
                g2.setColor(Color.RED);// ָ����ɫ
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��ñ�ʾ͸���ȵ�AlphaComposite����
                g2.setComposite(alpha);// ָ��AlphaComposite����
                g2.drawString("��̴ʵ�", -30, 240);// �����ı�,ʵ��ˮӡ
            }
        }
    }
}
