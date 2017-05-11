package com.mingrisoft;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FileAttributeModification extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -5028582953468410969L;
    private JPanel contentPane;
    private JTextField textField;
    private File selectFile;
    private JCheckBox readCheckBox;
    private JCheckBox archiveCheckBox;
    private JCheckBox systemCheckBox;
    private JCheckBox hiddenCheckBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileAttributeModification frame = new FileAttributeModification();
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
    public FileAttributeModification() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 160);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 5, 5));

        JPanel filePanel = new JPanel();
        contentPane.add(filePanel);

        JLabel label = new JLabel("�ļ�·����");
        filePanel.add(label);

        textField = new JTextField();
        textField.setEditable(false);
        filePanel.add(textField);
        textField.setColumns(15);

        JButton chooseButton = new JButton("ѡ���ļ�");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_chooseButton_actionPerformed(e);
            }
        });
        filePanel.add(chooseButton);

        JPanel attributePanel = new JPanel();
        contentPane.add(attributePanel);

        readCheckBox = new JCheckBox("ֻ��");
        attributePanel.add(readCheckBox);

        archiveCheckBox = new JCheckBox("�浵");
        attributePanel.add(archiveCheckBox);

        systemCheckBox = new JCheckBox("ϵͳ");
        attributePanel.add(systemCheckBox);

        hiddenCheckBox = new JCheckBox("����");
        attributePanel.add(hiddenCheckBox);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JButton setButton = new JButton("����");
        setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_setButton_actionPerformed(e);
            }
        });
        buttonPanel.add(setButton);

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
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectFile = chooser.getSelectedFile();
            String path = selectFile.getAbsolutePath();
            textField.setText(path);
            String command = "attrib " + path;
            try {
                Process process = Runtime.getRuntime().exec(command);
                Scanner scanner = new Scanner(process.getInputStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int pathIndex = line.indexOf(path);
                    String attribute = line.substring(0, pathIndex).trim();
                    readCheckBox.setSelected(attribute.contains("R"));
                    archiveCheckBox.setSelected(attribute.contains("A"));
                    systemCheckBox.setSelected(attribute.contains("S"));
                    hiddenCheckBox.setSelected(attribute.contains("H"));
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    protected void do_setButton_actionPerformed(ActionEvent e) {
        StringBuilder command = new StringBuilder("attrib ");// ��������
        if (readCheckBox.isSelected()) {// �����Ҫ����ֻ������
            command.append(" +R ");// �����������Ӳ���
        }
        if (archiveCheckBox.isSelected()) {// �����Ҫ���Ӵ浵����
            command.append(" +A ");// �����������Ӳ���
        }
        if (systemCheckBox.isSelected()) {// �����Ҫ����ϵͳ����
            command.append(" +S ");// �����������Ӳ���
        }
        if (hiddenCheckBox.isSelected()) {// �����Ҫ������������
            command.append(" +H ");// �����������Ӳ���
        }
        command.append(selectFile.getAbsolutePath());// �����ļ�·��
        try {
            Runtime.getRuntime().exec(command.toString());// ִ������
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    protected void do_closeButton_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
