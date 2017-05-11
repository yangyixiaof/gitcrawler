package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
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
public class SaveZoomImageFrame extends JFrame {
    private JButton button_3;
    private JButton button_2;
    private JButton button_1;
    private JButton button;
    private JPanel panel;
    private Image img = null; // ����ͼ�����
    private ZoomImagePanel imagePanel = null; // ����ͼ��������
    private int imgWidth, imgHeight;// ���ڴ洢ͼƬ�Ŀ�Ⱥ͸߶�
    private int newW, newH;// ���ڴ洢ͼƬ���ź�Ŀ�Ⱥ͸߶�
    private float value = 50.0f;// ����ͼ���С�ı���
    
    public static void main(String args[]) {
        SaveZoomImageFrame frame = new SaveZoomImageFrame();
        frame.setVisible(true);
    }
    
    public SaveZoomImageFrame() {
        super();
        this.setTitle("����ͼƬ������"); // ���ô������
        URL imgUrl = SaveZoomImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imagePanel = new ZoomImagePanel(); // ����ͼ��������
        this.setBounds(200, 160, 355, 253); // ���ô����С��λ��
        this.add(imagePanel); // �ڴ����в�λ�����ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        getContentPane().add(getPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * @return
     */
    protected JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.add(getButton());
            panel.add(getButton_1());
            panel.add(getButton_2());
            panel.add(getButton_3());
        }
        return panel;
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value += 5;// ����value��ֵ�����ڷŴ�ͼƬ
                    if (value >= 200.0f) {// ���value��ֵ���ڵ���200
                        value = 200.0f;// ʹvalue��ֵ����200
                    }
                    imagePanel.repaint();// ���µ���������paint()����
                }
            });
            button.setText("��  ��");
        }
        return button;
    }
    
    /**
     * @return
     */
    protected JButton getButton_1() {
        if (button_1 == null) {
            button_1 = new JButton();
            button_1.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value -= 5;// ����value��ֵ��������СͼƬ
                    if (value <= 0.0f) {// ���value��ֵС�ڵ���0
                        value = 0.0f;// ʹvalue��ֵ����0
                    }
                    imagePanel.repaint();// ���µ���������paint()����
                }
            });
            button_1.setText("��    С");
        }
        return button_1;
    }
    
    /**
     * @return
     */
    protected JButton getButton_2() {
        if (button_2 == null) {
            button_2 = new JButton();
            button_2.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (newW <= 0 || newH <= 0) {
                        JOptionPane.showMessageDialog(null, "ͼ��Ŀ�Ⱥ͸߶ȱ������0");// ��ʾ��ʾ��Ϣ
                        return;
                    }
                    FileDialog dialog = new FileDialog(SaveZoomImageFrame.this,
                            "����");// �����Ի���
                    dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                    dialog.setVisible(true);// ��ʾ����Ի���
                    String path = dialog.getDirectory();// ����ļ��ı���·��
                    String fileName = dialog.getFile();// ��ñ�����ļ���
                    if (path == null || fileName == null) {
                        return;
                    }
                    String fileExtName = fileName.substring(fileName
                            .indexOf(".") + 1);// �ļ���չ��,������
                    String pathAndName = path + "\\" + fileName;// �ļ�������·��
                    BufferedImage bufferImage = new BufferedImage(newW, newH,
                            BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
                    Graphics g = bufferImage.getGraphics();// ��û�ͼ�����Ķ���
                    g.drawImage(img, 0, 0, newW, newH, null);// �ڻ���ͼ���ϻ���ͼ��
                    try {
                        ImageIO.write(bufferImage, fileExtName, new File(
                                pathAndName));// ������ͼ�񱣴浽����
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "����ʧ��\n"
                                + e1.getMessage());// ��ʾ��ʾ��Ϣ
                    }
                }
            });
            button_2.setText("��    ��");
        }
        return button_2;
    }
    
    /**
     * @return
     */
    protected JButton getButton_3() {
        if (button_3 == null) {
            button_3 = new JButton();
            button_3.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    System.exit(0);
                }
            });
            button_3.setText("��    ��");
        }
        return button_3;
    }
    
    // ���������
    class ZoomImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �����ͼ�����ĵ�����
            imgWidth = img.getWidth(this); // ��ȡͼƬ���
            imgHeight = img.getHeight(this); // ��ȡͼƬ�߶�
            newW = (int) (imgWidth * value / 100);// ����ͼƬ���ź�Ŀ��
            newH = (int) (imgHeight * value / 100);// ����ͼƬ���ź�ĸ߶�
            g.drawImage(img, 0, 0, newW, newH, this);// ����ָ����С��ͼƬ
        }
    }
    
}
