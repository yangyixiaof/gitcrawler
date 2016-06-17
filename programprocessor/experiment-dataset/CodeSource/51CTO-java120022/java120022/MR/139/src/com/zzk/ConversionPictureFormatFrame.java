package com.zzk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class ConversionPictureFormatFrame extends JFrame {
    
    private JComboBox comboBox;
    private JTextField tf_pathAndFileName;
    private File imgFile = null;
    private BufferedImage buffImage;
    private DrawImagePanel imagePanel = null;
    private String path = null;
    private String fileName = null;
    private String pathAndFileName = null;
    /**
     * Launch the application
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConversionPictureFormatFrame frame = new ConversionPictureFormatFrame();
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
    public ConversionPictureFormatFrame() {
        super();
        setTitle("ת��ͼƬ��ʽ");
        setBounds(100, 100, 432, 315);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        
        imagePanel = new DrawImagePanel();
        getContentPane().add(imagePanel, BorderLayout.CENTER);

        final JLabel label = new JLabel();
        label.setText("��ѡ��ԭͼƬ��");
        panel.add(label);

        tf_pathAndFileName = new JTextField();
        tf_pathAndFileName.setPreferredSize(new Dimension(200,25));
        panel.add(tf_pathAndFileName);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡ����
                FileFilter filter = new FileNameExtensionFilter(
                        "ͼ���ļ���JPG/GIF/png/bmp)", "JPG", "JPEG", "GIF", "png", "bmp");// ����������
                fileChooser.setFileFilter(filter);// ���ù�����
                int i = fileChooser.showOpenDialog(null);// ��ʾ�򿪶Ի���
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��ȡѡ��ͼƬ��File����
                }
                if (imgFile != null) {
                    try {
                        buffImage = ImageIO.read(imgFile);// ����BufferedImage����
                        path = imgFile.getParent();
                        fileName = imgFile.getName();
                        pathAndFileName = path + "\\" + fileName;
                        tf_pathAndFileName.setText(pathAndFileName);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imagePanel.repaint();// ����paint()����
            }
        });
        button.setText("ѡ��ͼƬ");
        panel.add(button);

        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        final JLabel label_1 = new JLabel();
        label_1.setText("��ѡ��ת�����ͼƬ��ʽ��");
        panel_1.add(label_1);

        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(130,25));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"jpg", "gif", "png", "bmp"}));
        panel_1.add(comboBox);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    String extName = (String)comboBox.getSelectedItem();// �¸�ʽ����չ����������
                    String pathAndName = pathAndFileName.substring(0, pathAndFileName.lastIndexOf(".") + 1)+extName;// ת�����ͼƬ����·�����ļ���
                    File file = new File(pathAndName);// ����ת����ͼƬ��File����
                    ImageIO.write(buffImage, extName, file);// ������ͼ�񱣴浽����
                    File oldFile = new File(pathAndFileName);// ԭͼƬ��File����
                    oldFile.delete();// ɾ��ԭͼƬ�ļ�
                    JOptionPane.showMessageDialog(null, "�ļ���ʽ���ĳɹ���");// ��ʾ��ʾ��Ϣ
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "����ʧ��\n" + e1.getMessage());// ��ʾ��ʾ��Ϣ
                }
            }
        });
        button_1.setText("��    ��");
        panel_1.add(button_1);
        
    }
    // ���������
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());
            g.drawImage(buffImage, 0, 0, this); // ����ָ����ͼƬ
        }
    }
}
