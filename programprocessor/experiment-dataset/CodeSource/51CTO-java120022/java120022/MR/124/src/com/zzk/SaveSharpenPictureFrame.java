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
public class SaveSharpenPictureFrame extends JFrame {
    private BufferedImage image;// ��������ͼ�����
    private SharpenPicturePanel sharpenPicturePanel = null; // ����ͼ��������
    private boolean sharpenFlag = false;// �񻯱��
    public static void main(String args[]) {
        SaveSharpenPictureFrame frame = new SaveSharpenPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveSharpenPictureFrame() {
        super();
        setTitle("��ͼƬ������"); // ���ô������
        setBounds(200, 160, 446, 305); // ���ô����С��λ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        sharpenPicturePanel = new SharpenPicturePanel(); // ����ͼ��������
        add(sharpenPicturePanel); // �ڴ��������ͼ��������
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = {0.0f,-1.0f,0.0f,-1.0f,5.0f,-1.0f,0.0f,-1.0f,0.0f};// ������ʾ���ط���������
                convolve(elements);// ���÷���ʵ��ͼƬ�񻯹���
                sharpenFlag = true;// ��ͼƬ���
            }
        });
        button.setText("��ͼƬ");
        panel.add(button);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!sharpenFlag) {
                    JOptionPane.showMessageDialog(null,"��û����ͼƬ��");// ��ʾ��ʾ��Ϣ
                    return;
                }
                FileDialog dialog = new FileDialog(SaveSharpenPictureFrame.this,"����");// �����Ի���
                dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                dialog.setVisible(true);// ��ʾ����Ի���
                String path = dialog.getDirectory();// ����ļ��ı���·��
                String fileName = dialog.getFile();// ��ñ�����ļ���
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// �ļ���չ��,������
                String pathAndName = path + "\\" + fileName;// �ļ�������·��
                try {
                    ImageIO.write(image, fileExtName, new File(pathAndName));// ������ͼ�񱣴浽����
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"����ʧ��\n"+e1.getMessage());// ��ʾ��ʾ��Ϣ
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
    class SharpenPicturePanel extends JPanel {
        public SharpenPicturePanel(){
            Image img = null;// ��������ͼ�����
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // ����ͼ�����
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0,  null);// �ڻ���ͼ������ϻ���ͼ��
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// ���ƻ���ͼ�����
            }
        }
    }
}
