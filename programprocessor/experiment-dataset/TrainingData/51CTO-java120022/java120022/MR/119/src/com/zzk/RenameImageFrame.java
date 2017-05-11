package com.zzk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class RenameImageFrame extends JFrame {
    private JTextField tf_newFileName;
    private JTextField tf_fileName;
    private File imgFile = null;
    private BufferedImage src;
    private String path = null;// ͼƬ��·��
    private String fileName = null;// ԭͼƬ���ļ���
    private DrawImagePanel imagePanel = null; // ����ͼ��������
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RenameImageFrame frame = new RenameImageFrame();
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
    public RenameImageFrame() {
        super();
        setTitle("�޸�ͼƬ�ļ���"); // ���ô������
        setBounds(200, 160, 391, 288); // ���ô����С��λ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imagePanel = new DrawImagePanel(); // ����ͼ��������
        add(imagePanel); // �ڴ��������ͼ��������
        
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("ѡ��ͼƬ��");
        panel.add(label_1);
        
        tf_fileName = new JTextField();
        tf_fileName.setPreferredSize(new Dimension(200, 25));
        panel.add(tf_fileName);
        
        final JButton button_2 = new JButton();
        panel.add(button_2);
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡ����
                FileFilter filter = new FileNameExtensionFilter(
                        "ͼ���ļ���JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// ����������
                fileChooser.setFileFilter(filter);// ���ù�����
                int i = fileChooser.showOpenDialog(null);// ��ʾ�򿪶Ի���
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��ȡѡ��ͼƬ��File����
                    path = imgFile.getParent();// ���ͼƬ��·��
                    fileName = imgFile.getName();// ���ԭͼƬ�ļ���
                    tf_fileName.setText(path + "\\" + fileName);// ���ı�������ʾͼƬ������·��
                }
                if (imgFile != null) {
                    try {
                        src = ImageIO.read(imgFile);// ����BufferedImage����
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imagePanel.repaint();// ����paint����
            }
        });
        button_2.setText("ѡ��ͼƬ");
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("�������ļ�����");
        panel_1.add(label);
        
        tf_newFileName = new JTextField();
        tf_newFileName.setPreferredSize(new Dimension(160, 25));
        panel_1.add(tf_newFileName);
        
        final JButton button = new JButton();
        panel_1.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (path != null && fileName != null) {
                    String newName = tf_newFileName.getText().trim();// �����������ļ���
                    if (newName.indexOf(":") >= 0 || newName.indexOf("\\") >= 0) {// �ж��ļ����Ƿ����·��
                        JOptionPane.showMessageDialog(null, "��ֱ���������ļ�����\n����Ҫָ��·����");
                        return;
                    } else if (newName.equals("")) {// ���û�������ļ����������ʾ
                        JOptionPane.showMessageDialog(null, "���������ļ�����");
                        return;
                    } else {
                        if (newName.indexOf(".") > 0) {
                            imgFile.renameTo(new File(path + "\\" + newName));// ���ļ�������չ��
                        } else {
                            imgFile.renameTo(new File(path + "\\" + newName + fileName.substring(fileName.indexOf("."))));// ���ļ���������չ��
                        }
                    }
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
                }
            }
        });
        button.setText("������ͼƬ");
    }
    
    // ���������
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(src, 0, 0, getWidth(), getHeight(), this); // ����ָ����ͼƬ
        }
    }
}
