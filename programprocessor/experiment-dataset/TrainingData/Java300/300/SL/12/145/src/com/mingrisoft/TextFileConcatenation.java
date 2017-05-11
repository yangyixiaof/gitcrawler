package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextFileConcatenation extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 5224889437729791565L;
    private JPanel contentPane;
    private JTextField textField;
    private File[] textFiles;
    private JTextArea textArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TextFileConcatenation frame = new TextFileConcatenation();
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
    public TextFileConcatenation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel fileLabel = new JLabel("\u6587\u4EF6\u5939\u6240\u5728\u4F4D\u7F6E\uFF1A");
        panel.add(fileLabel);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);

        JButton button = new JButton("\u9009\u62E9\u6587\u4EF6\u5939");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(button);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton concatButton = new JButton("\u5408\u5E76\u6587\u4EF6");
        concatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_concatButton_actionPerformed(e);
            }
        });
        buttonPanel.add(concatButton);
    }

    protected void do_button_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectFile = chooser.getSelectedFile();
            textField.setText(selectFile.getAbsolutePath());
            textFiles = selectFile.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    if (pathname.getAbsolutePath().endsWith(".txt")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            for (File textFile : textFiles) {
                textArea.append(textFile.getAbsolutePath() + "\n\r");
            }
        }
    }

    protected void do_concatButton_actionPerformed(ActionEvent e) {
        if (textFiles == null) {
            JOptionPane.showMessageDialog(this, "��ѡ���ļ��ļ�λ�ã�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (textFiles.length == 0) {
            JOptionPane.showMessageDialog(this, "�ļ����в������ı��ļ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            writer = new FileWriter("d://concatenation.txt");// �����ļ������
            for (File textFile : textFiles) {// �����û�ѡ����ı��ļ�
                reader = new BufferedReader(new FileReader(textFile));// ��������������
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);// �����������д�뵽�ļ���
                }
            }
            JOptionPane.showMessageDialog(this, "�ļ��ϲ��ɹ���", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
