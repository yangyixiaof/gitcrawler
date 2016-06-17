package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveBlurPictureFrame extends JFrame {
    private BufferedImage image;// ��������ͼ�����
    private BlurPicturePanel blurPicturePanel = null; // ����ͼ��������
    private boolean blurFlag = false;// ģ�����
    
    public static void main(String args[]) {
        SaveBlurPictureFrame frame = new SaveBlurPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveBlurPictureFrame() {
        super();
        setTitle("ģ��ͼƬ������"); // ���ô������
        setBounds(200, 160, 439, 319); // ���ô����С��λ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        blurPicturePanel = new BlurPicturePanel(); // ����ͼ��������
        add(blurPicturePanel); // �ڴ��������ͼ��������
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = new float[9];// �����ʾ���ط���������
                for (int i = 0; i < 9; i++) {
                    elements[i] = 0.11f;// Ϊ���鸳ֵ
                }
                convolve(elements);// ���÷�����ʵ��ģ������
                blurFlag = true;// ģ��ͼƬ���
            }
        });
        button.setText("ģ��ͼƬ");
        panel.add(button);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!blurFlag) {
                    JOptionPane.showMessageDialog(null, "��û��ģ��ͼƬ��");// ��ʾ��ʾ��Ϣ
                    return;
                }
                FileDialog dialog = new FileDialog(SaveBlurPictureFrame.this,
                        "����");// �����Ի���
                dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                dialog.setVisible(true);// ��ʾ����Ի���
                String path = dialog.getDirectory();// ����ļ��ı���·��
                String fileName = dialog.getFile();// ��ñ�����ļ���
                if (path == null || fileName == null) {
                    return;
                }
                String fileExtName = fileName
                        .substring(fileName.indexOf(".") + 1);// �ļ���չ��,������
                String pathAndName = path + "\\" + fileName;// �ļ�������·��
                try {
                    ImageIO.write(image, fileExtName, new File(pathAndName));// ������ͼ�񱣴浽����
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "����ʧ��\n"
                            + e1.getMessage());// ��ʾ��ʾ��Ϣ
                }
            }
        });
        button_2.setText("����ͼƬ");
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
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// ���� Kernel����
        ConvolveOp op = new ConvolveOp(kernel);// ����ConvolveOp����
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // ���˻���ͼ�����
        repaint();// ����paint()����
    }
    
    // ���������
    class BlurPicturePanel extends JPanel {
        public BlurPicturePanel() {
            Image img = null;// ��������ͼ�����
            try {
                img = ImageIO.read(new File("src/img/imag.jpg")); // ����ͼ�����
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null), img.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0, null);// �ڻ���ͼ������ϻ���ͼ��
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// ���ƻ���ͼ�����
            }
        }
    }
}
