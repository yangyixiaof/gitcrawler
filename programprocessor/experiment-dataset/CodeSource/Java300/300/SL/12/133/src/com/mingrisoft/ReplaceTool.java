package com.mingrisoft;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReplaceTool extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6579898848014795564L;
    private JPanel contentPane;
    private JTextField beforeTextField;
    private JTextField afterTextField;
    private File textFile;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReplaceTool frame = new ReplaceTool();
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
    public ReplaceTool() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(2, 1, 5, 5));

        JPanel contentPanel = new JPanel();
        contentPane.add(contentPanel);

        JLabel beforeLabel = new JLabel("�滻ǰ�ַ�����");
        contentPanel.add(beforeLabel);

        beforeTextField = new JTextField();
        contentPanel.add(beforeTextField);
        beforeTextField.setColumns(10);

        JButton chooseButton = new JButton("ѡ���ļ�");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_chooseButton_actionPerformed(e);
            }
        });
        contentPanel.add(chooseButton);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JButton replaceButton = new JButton("��ʼ�滻");
        replaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_replaceButton_actionPerformed(e);
            }
        });

        JLabel afterlabel = new JLabel("�滻���ַ�����");
        buttonPanel.add(afterlabel);

        afterTextField = new JTextField();
        buttonPanel.add(afterTextField);
        afterTextField.setColumns(10);
        buttonPanel.add(replaceButton);
    }

    protected void do_chooseButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("�ı��ļ�", "txt"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            textFile = chooser.getSelectedFile();
        }
    }

    protected void do_replaceButton_actionPerformed(ActionEvent e) {
        String before = beforeTextField.getText();// ����滻ǰ�ַ���
        if (before.isEmpty()) {// ��֤�ַ����Ƿ�Ϊ��
            JOptionPane.showMessageDialog(this, "�������滻ǰ�ַ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String after = afterTextField.getText();// ����滻���ַ���
        if (after.isEmpty()) {// ��֤�ַ����Ƿ�Ϊ��
            JOptionPane.showMessageDialog(this, "�������滻���ַ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        FileReader reader = null;// �����ļ�����
        FileWriter writer = null;// �����ļ�д��
        StringBuilder sb = new StringBuilder();// ʹ��StringBuilder���󱣴��ļ�����
        int flag = 0;// �����ļ������ʾ
        char[] temp = new char[1024];// ʹ���ַ���������ļ�
        try {
            reader = new FileReader(textFile);// ʹ��ѡ����ļ���������
            while ((flag = reader.read(temp)) != -1) {
                sb.append(temp);// �����ļ��е�����
            }
            String content = sb.toString().replace(before, after);// �滻�ַ���
            writer = new FileWriter(textFile);// �����ļ�д��
            writer.write(content); // ���滻����ַ���д�뵽�ļ�
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();// �ر��ļ�����
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();// �ر��ļ�д��
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        JOptionPane.showMessageDialog(this, "�ַ����滻�ɹ���");
        return;
    }
}
