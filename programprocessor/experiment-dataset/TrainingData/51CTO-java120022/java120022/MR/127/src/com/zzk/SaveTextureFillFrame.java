package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveTextureFillFrame extends JFrame {
    private TextureFillPanel textureFillPanel = null; // ����������
    private int newW, newH;// ���ڴ洢ͼƬ���ź�Ŀ�Ⱥ͸߶�
    
    public static void main(String args[]) {
        SaveTextureFillFrame frame = new SaveTextureFillFrame();
        frame.setVisible(true);
    }
    
    public SaveTextureFillFrame() {
        super();
        setTitle("�����������ΪͼƬ"); // ���ô������
        textureFillPanel = new TextureFillPanel(); // ����ͼ��������
        setBounds(200, 160, 346, 285); // ���ô����С��λ��
        add(textureFillPanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                FileDialog dialog = new FileDialog(SaveTextureFillFrame.this,
                        "����");// �����Ի���
                dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                dialog.setVisible(true);// ��ʾ����Ի���
                String path = dialog.getDirectory();// ����ļ��ı���·��
                String fileName = dialog.getFile();// ��ñ�����ļ���
                if (path == null || fileName == null) {
                    JOptionPane.showMessageDialog(null, "��ָ���ļ��ı���·�����ļ�����");// ��ʾ��ʾ��Ϣ
                    return;
                }
                String fileExtName = fileName
                        .substring(fileName.indexOf(".") + 1);// �ļ���չ��,������
                String pathAndName = path + "\\" + fileName;// �ļ�������·��
                BufferedImage image = new BufferedImage(200, 200,
                        BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
                Graphics2D g2 = image.createGraphics();// ��û���ͼ�����Ļ�ͼ�����Ķ���
                g2.setColor(Color.BLUE);// ������ɫ
                g2.fillRect(0, 0, 90, 90);// ����������
                g2.setColor(Color.RED);// ������ɫ
                g2.fillOval(95, 0, 90, 90);// ���ƴ����ɫ��Բ��
                g2.setColor(Color.GREEN);// ������ɫ
                g2.fillRect(95, 95, 90, 90);// ����������
                g2.setColor(Color.ORANGE);// ������ɫ
                g2.fillOval(0, 95, 90, 90);// ���ƴ����ɫ��Բ��
                Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// ����Rectangle2D����
                TexturePaint textPaint = new TexturePaint(image, rect);// ��������������
                BufferedImage bufferImage = new BufferedImage(newW, newH,
                        BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
                Graphics g = bufferImage.getGraphics();// ��û���ͼ��Ļ�ͼ�����Ķ���
                Graphics2D graphics2 = (Graphics2D) g;// ת����ͼ�����Ķ���ΪGraphics2D����
                graphics2.setPaint(textPaint);// Ϊ��ͼ�����Ķ���ָ������������
                Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW,
                        newH);// �������ζ���
                graphics2.fill(ellipse2);// �����������ľ���
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// ������ͼ�񱣴浽����
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "����ʧ��\n"
                            + e1.getMessage());// ��ʾ��ʾ��Ϣ
                }
            }
        });
        button.setText("����ΪͼƬ");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        panel.add(button_1);
    }
    
    // ���������
    class TextureFillPanel extends JPanel {
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            Graphics2D g2 = image.createGraphics();// ��û���ͼ�����Ļ�ͼ�����Ķ���
            g2.setColor(Color.BLUE);// ������ɫ
            g2.fillRect(0, 0, 90, 90);// ����������
            g2.setColor(Color.RED);// ������ɫ
            g2.fillOval(95, 0, 90, 90);// ���ƴ����ɫ��Բ��
            g2.setColor(Color.GREEN);// ������ɫ
            g2.fillRect(95, 95, 90, 90);// ����������
            g2.setColor(Color.ORANGE);// ������ɫ
            g2.fillOval(0, 95, 90, 90);// ���ƴ����ɫ��Բ��
            Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// ����Rectangle2D����
            TexturePaint textPaint = new TexturePaint(image, rect);// ��������������
            Graphics2D graphics2 = (Graphics2D) g;// ת��paint()�����Ļ�ͼ�����Ķ���
            graphics2.setPaint(textPaint);// Ϊ��ͼ�����Ķ�����������������
            newW = getWidth();
            newH = getHeight();
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW, newH);// �������ζ���
            graphics2.fill(ellipse2);// �����������ľ���
        }
    }
}
