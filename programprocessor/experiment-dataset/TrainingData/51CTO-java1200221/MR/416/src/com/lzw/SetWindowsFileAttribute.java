package com.lzw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

public class SetWindowsFileAttribute extends JFrame {
    
    private JPanel contentPane;
    private JTextField pathField;
    private JLabel fileLabel;
    private File file;
    private JCheckBox docCheckBox;
    private JCheckBox hideCheckBox;
    private JCheckBox readonlyCheckBox;
    private JCheckBox systemCheckBox;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetWindowsFileAttribute frame = new SetWindowsFileAttribute();
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
    public SetWindowsFileAttribute() {
        setTitle("\u8BBE\u7F6EWindows\u7684\u6587\u4EF6\u5C5E\u6027");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 415, 222);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 85, 90, 60, 57, 97, 0 };
        gbl_contentPane.rowHeights = new int[] { 73, 23, 25, 25, 0 };
        gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);
        
        JButton chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_chooseButton_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_chooseButton = new GridBagConstraints();
        gbc_chooseButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_chooseButton.insets = new Insets(0, 0, 5, 5);
        gbc_chooseButton.gridx = 0;
        gbc_chooseButton.gridy = 0;
        contentPane.add(chooseButton, gbc_chooseButton);
        
        fileLabel = new JLabel("\u9009\u62E9\u6587\u4EF6");
        fileLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        fileLabel.setPreferredSize(new Dimension(80, 85));
        fileLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        fileLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_fileLabel = new GridBagConstraints();
        gbc_fileLabel.fill = GridBagConstraints.BOTH;
        gbc_fileLabel.insets = new Insets(0, 0, 5, 0);
        gbc_fileLabel.gridwidth = 4;
        gbc_fileLabel.gridx = 1;
        gbc_fileLabel.gridy = 0;
        contentPane.add(fileLabel, gbc_fileLabel);
        
        JLabel label_1 = new JLabel("\u6587\u4EF6\u8DEF\u5F84\uFF1A");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 1;
        contentPane.add(label_1, gbc_label_1);
        
        pathField = new JTextField();
        GridBagConstraints gbc_pathField = new GridBagConstraints();
        gbc_pathField.anchor = GridBagConstraints.NORTH;
        gbc_pathField.fill = GridBagConstraints.HORIZONTAL;
        gbc_pathField.insets = new Insets(0, 0, 5, 0);
        gbc_pathField.gridwidth = 4;
        gbc_pathField.gridx = 1;
        gbc_pathField.gridy = 1;
        contentPane.add(pathField, gbc_pathField);
        pathField.setColumns(10);
        
        docCheckBox = new JCheckBox("\u5F52\u6863");
        GridBagConstraints gbc_docCheckBox = new GridBagConstraints();
        gbc_docCheckBox.anchor = GridBagConstraints.NORTHEAST;
        gbc_docCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_docCheckBox.gridx = 1;
        gbc_docCheckBox.gridy = 2;
        contentPane.add(docCheckBox, gbc_docCheckBox);
        
        hideCheckBox = new JCheckBox("\u9690\u85CF");
        GridBagConstraints gbc_hideCheckBox = new GridBagConstraints();
        gbc_hideCheckBox.anchor = GridBagConstraints.NORTH;
        gbc_hideCheckBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_hideCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_hideCheckBox.gridx = 2;
        gbc_hideCheckBox.gridy = 2;
        contentPane.add(hideCheckBox, gbc_hideCheckBox);
        
        readonlyCheckBox = new JCheckBox("\u53EA\u8BFB");
        GridBagConstraints gbc_readonlyCheckBox = new GridBagConstraints();
        gbc_readonlyCheckBox.anchor = GridBagConstraints.NORTH;
        gbc_readonlyCheckBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_readonlyCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_readonlyCheckBox.gridx = 3;
        gbc_readonlyCheckBox.gridy = 2;
        contentPane.add(readonlyCheckBox, gbc_readonlyCheckBox);
        
        systemCheckBox = new JCheckBox("\u7CFB\u7EDF");
        GridBagConstraints gbc_systemCheckBox = new GridBagConstraints();
        gbc_systemCheckBox.anchor = GridBagConstraints.NORTHWEST;
        gbc_systemCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_systemCheckBox.gridx = 4;
        gbc_systemCheckBox.gridy = 2;
        contentPane.add(systemCheckBox, gbc_systemCheckBox);
        
        JButton setButton = new JButton("\u8BBE\u7F6E");
        setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_setButton_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_setButton = new GridBagConstraints();
        gbc_setButton.anchor = GridBagConstraints.NORTH;
        gbc_setButton.insets = new Insets(0, 0, 0, 5);
        gbc_setButton.gridwidth = 2;
        gbc_setButton.gridx = 1;
        gbc_setButton.gridy = 3;
        contentPane.add(setButton, gbc_setButton);
        
        JButton closeButton = new JButton("\u5173\u95ED");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_closeButton_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_closeButton = new GridBagConstraints();
        gbc_closeButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_closeButton.gridwidth = 2;
        gbc_closeButton.gridx = 3;
        gbc_closeButton.gridy = 3;
        contentPane.add(closeButton, gbc_closeButton);
    }
    
    /**
     * ѡ���ļ���ť���¼�������
     * 
     * @param e
     */
    protected void do_chooseButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        chooser.setFileHidingEnabled(false);// ��ʾ�����ļ�
        int option = chooser.showOpenDialog(this);// ��ʾ�ļ��򿪶Ի���
        if (option == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();// ��ȡ�û�ѡ���ļ�
            // ��ȡ�ļ�ϵͳ��ͼ
            FileSystemView view = chooser.getFileSystemView();
            Icon icon = view.getSystemIcon(file);// ��ȡ�ļ�ͼ��
            getFileLabel().setIcon(icon);// ��ʾ�ļ�ͼ��
            getFileLabel().setText(file.getName());// ��ʾ�ļ�����
            pathField.setText(file.getPath());// ��ʾ�ļ�·��
            // ���������ı�
            String command = "attrib " + file.getPath();
            try {
                // ִ�������ı�
                Process exec = Runtime.getRuntime().exec(command);
                // ��������ִ�л������ı�ɨ����
                Scanner in = new Scanner(exec.getInputStream());
                if (in.hasNextLine()) {
                    // ��ȡ����ִ�н��
                    String line = in.nextLine();
                    int of = line.indexOf(file.getPath());
                    // ��ȡ���������ļ���������Ϣ
                    String attribStr = line.substring(0, of).trim();
                    // �����������ø���ѡ��ѡ��״̬
                    docCheckBox.setSelected(attribStr.contains("A"));
                    hideCheckBox.setSelected(attribStr.contains("H"));
                    readonlyCheckBox.setSelected(attribStr.contains("R"));
                    systemCheckBox.setSelected(attribStr.contains("S"));
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            file = null;
        }
    }
    
    public JLabel getFileLabel() {
        return fileLabel;
    }
    
    /**
     * �رհ�ť���¼�������
     * 
     * @param e
     */
    protected void do_closeButton_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    
    /**
     * ���ð�ť���¼�������
     * 
     * @param e
     */
    protected void do_setButton_actionPerformed(ActionEvent e) {
        if (file == null)
            return;
        // ���������ı�
        StringBuilder attrib = new StringBuilder("attrib " + file.getPath());
        attrib.append(docCheckBox.isSelected() ? " +a" : " -a");// ���ù鵵����
        attrib.append(hideCheckBox.isSelected() ? " +h" : " -h");// ������������
        attrib.append(readonlyCheckBox.isSelected() ? " +r" : " -r");// ����ֻ������
        attrib.append(systemCheckBox.isSelected() ? " +s" : " -s");// ����ϵͳ����
        try {
            Runtime.getRuntime().exec(attrib.toString());// ִ������
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
