package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesFileTool extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 901647053358382011L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PropertiesFileTool frame = new PropertiesFileTool();
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
    public PropertiesFileTool() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel label = new JLabel("\u9009\u62E9\u7684\u5C5E\u6027\u6587\u4EF6\uFF1A");
        panel.add(label);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(15);

        JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(button);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new String[] { "��", "ֵ" });
        scrollPane.setViewportView(table);
    }

    protected void do_button_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        chooser.setFileFilter(new FileNameExtensionFilter("�����ļ�", "properties"));// �����ļ�������
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// ���ý���ѡ���ļ�
        chooser.setMultiSelectionEnabled(false);// ��ֹ���ѡ��
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectFile = chooser.getSelectedFile();// ���ѡ����ļ�
            textField.setText(selectFile.getAbsolutePath());// ��ʾѡ����ļ�·��
            Properties properties = new Properties();
            FileReader reader = null;
            DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
            try {
                reader = new FileReader(selectFile);
                properties.load(reader);// ���������ļ�
                Enumeration<?> keys = properties.propertyNames();// ��������ļ��ļ�ö��
                while (keys.hasMoreElements()) {
                    String key = (String) keys.nextElement();// ��ü�
                    String value = properties.getProperty(key);// ���ֵ
                    model.addRow(new String[] { key, value });// ���������Ӽ�¼
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
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
}
