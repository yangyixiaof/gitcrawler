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
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowerPictureFrame extends JFrame {
    private static final long serialVersionUID = 2568309165342527300L;
    private JTextField tf_path;
    private BufferedImage img=null;
    private File imgFile = null;
    private DrawImagePanel imgPanel = null;
    private String filePath = null;
    private String currentFileName = null;
    private int currentFileIndex = 0;
    private List<String> fileNameList = new ArrayList<String>();
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BrowerPictureFrame frame = new BrowerPictureFrame();
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
    public BrowerPictureFrame() {
        super();
        setTitle("ͼƬ�����");
        setBounds(100, 100, 457, 328);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgPanel = new DrawImagePanel();
        final JPanel panel = new JPanel();
        getContentPane().add(imgPanel, BorderLayout.CENTER);
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setText("ѡ���ļ���");
        panel.add(label);

        tf_path = new JTextField();
        tf_path.setPreferredSize(new Dimension(280,25));
        panel.add(tf_path);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡ����
                FileFilter filter = new FileNameExtensionFilter(
                        "ͼ���ļ���JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// ����������
                fileChooser.setFileFilter(filter);// ���ù�����
                int flag = fileChooser.showOpenDialog(null);// ��ʾ�򿪶Ի���
                if (flag == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��ȡѡ��ͼƬ��File����
                }
                if (imgFile != null) {
                    try {
                        img = ImageIO.read(imgFile);// ����BufferedImage����
                        filePath = imgFile.getParent();// ����ļ���·��
                        tf_path.setText(filePath);// ���ı�������ʾ·��
                        currentFileName = imgFile.getName();// ���ѡ����ļ�������ֵ���洢��ǰ�ļ��ı���
                        final String extName = currentFileName.substring(currentFileName.lastIndexOf("."));// ����ļ�����չ��
                        File pathFile = new File(filePath);// ����·����File����
                        String[] fileNames = pathFile.list(new FilenameFilter(){// �����������������ļ�������
                            @Override
                            public boolean accept(File dir, String name) {
                                return name.endsWith(extName);// ����������չ�����ļ���
                            }
                        });
                        for (int i=0;i<fileNames.length;i++){// ���������е��ļ���
                            if (fileNames[i].equals(currentFileName)){// �ж��Ƿ�Ϊ��ǰ�ļ�
                                currentFileIndex = i;// ���䵱ǰ�ļ�������ֵ
                            }
                            fileNameList.add(fileNames[i]);// ���ļ���ӵ������б���
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imgPanel.repaint();// ����paint()����
            }
        });
        button.setText("ѡ    ��");
        panel.add(button);

        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex = 0;// ��һ��ͼƬ������
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// ������ǰ����ֵ��ӦͼƬ��File����
                try {
                    img = ImageIO.read(imgFile);// ������ǰͼƬ��ͼ�����
                    imgPanel.repaint();// ����paint()����
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_1.setText("��һ��");
        panel_1.add(button_1);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex--;// ������ǰͼƬ������ֵ
                if (currentFileIndex < 0) {
                    currentFileIndex = fileNameList.size() - 1;// ��ǰͼƬ����Ϊ���һ��ͼƬ������
                }
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// ������ǰ����ֵ��ӦͼƬ��File����
                try {
                    img = ImageIO.read(imgFile);// ������ǰͼƬ��ͼ�����
                    imgPanel.repaint();// ����paint()��������ʾͼƬ
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_2.setText("��һ��");
        panel_1.add(button_2);

        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex++;// ������ǰͼƬ������ֵ
                if (currentFileIndex > fileNameList.size() - 1) {
                    currentFileIndex = 0;// ��ǰͼƬ����Ϊ��һ��ͼƬ������
                }
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// ������ǰ����ֵ��ӦͼƬ��File����
                try {
                    img = ImageIO.read(imgFile);// ������ǰͼƬ��ͼ�����
                    imgPanel.repaint();// ����paint()����
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_3.setText("��һ��");
        panel_1.add(button_3);

        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex = fileNameList.size() - 1;// ���һ��ͼƬ������
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// ������ǰ����ֵ��ӦͼƬ��File����
                try {
                    img = ImageIO.read(imgFile);// ������ǰͼƬ��ͼ�����
                    imgPanel.repaint();// ����paint()����
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_4.setText("���һ��");
        panel_1.add(button_4);
    }
    // ���������
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            if (img == null){
                return;
            }
            int imgW = img.getWidth(this);
            int imgH = img.getHeight(this);
            int panelW = getWidth();
            int panelH = getHeight();
            g.clearRect(0, 0, panelW, panelH);
            g.drawImage(img, 0, 0,imgW,imgH,this); // ����ָ����ͼƬ
        }
    }
}
