package com.mingrisoft;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageSaveTool extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -1255769044503820393L;
    private JPanel contentPane;
    private JTextField pictureNameTextField;
    private Picture picture;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ImageSaveTool frame = new ImageSaveTool();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ImageSaveTool() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 150);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(2, 1, 5, 5));

        JPanel pathPanel = new JPanel();
        contentPane.add(pathPanel);

        JLabel pictureNameLabel = new JLabel("ͼƬ�ļ����ƣ�");
        pathPanel.add(pictureNameLabel);

        pictureNameTextField = new JTextField();
        pathPanel.add(pictureNameTextField);
        pictureNameTextField.setColumns(10);

        JButton chooseButton = new JButton("ѡ��ͼƬ");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_chooseButton_actionPerformed(e);
            }
        });
        pathPanel.add(chooseButton);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JButton saveButton = new JButton("����");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_saveButton_actionPerformed(e);
            }
        });
        buttonPanel.add(saveButton);

        JButton closeButton = new JButton("�ر�");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_closeButton_actionPerformed(e);
            }
        });
        buttonPanel.add(closeButton);
    }

    protected void do_chooseButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("JEPG�ļ�", "jpg"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            picture = new Picture();
            picture.setPictureFile(chooser.getSelectedFile());
        }
    }

    protected void do_saveButton_actionPerformed(ActionEvent e) {
        String pictureName = pictureNameTextField.getText();
        if (pictureName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "������ͼƬ�ļ����ƣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (picture == null) {
            JOptionPane.showMessageDialog(this, "��ѡ��Ҫ������ļ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        picture.setPictureName(pictureName);
        if (DBHelper.savePicture(picture)) {
            JOptionPane.showMessageDialog(this, "ͼƬ�ļ�����ɹ���", "������Ϣ", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "ͼƬ�ļ�����ʧ�ܣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
    protected void do_closeButton_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
