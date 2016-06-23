package com.lzw;

import static java.lang.Runtime.getRuntime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RarAnnotate extends JFrame {
    
    private JPanel contentPane;
    private JTextField rarFileField;
    private File rarFile;
    private JTextArea annotateArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RarAnnotate frame = new RarAnnotate();
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
    public RarAnnotate() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 5));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JLabel lblrar = new JLabel("\u3000\u9009\u62E9RAR\u6587\u6863\uFF1A");
        panel.add(lblrar, BorderLayout.WEST);
        
        JButton browseButton = new JButton("\u6D4F\u89C8");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_browseButton_actionPerformed(e);
            }
        });
        panel.add(browseButton, BorderLayout.EAST);
        
        rarFileField = new JTextField();
        panel.add(rarFileField, BorderLayout.CENTER);
        rarFileField.setColumns(10);
        
        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setHgap(25);
        contentPane.add(panel_1, BorderLayout.SOUTH);
        
        JButton annotateButton = new JButton("\u6DFB\u52A0/\u4FEE\u6539");
        annotateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_annotateButton_actionPerformed(e);
            }
        });
        panel_1.add(annotateButton);
        
        JButton closeButton = new JButton("\u5173\u95ED");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_closeButton_actionPerformed(e);
            }
        });
        panel_1.add(closeButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(new BevelBorder(
                BevelBorder.LOWERED, null, null, null, null),
                "\u6CE8\u91CA\u6587\u6863\uFF1A", TitledBorder.LEADING,
                TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        annotateArea = new JTextArea();
        annotateArea.setLineWrap(true);
        scrollPane.setViewportView(annotateArea);
    }
    
    /**
     * �����ť���¼�������
     * 
     * @param e
     */
    protected void do_browseButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        chooser.setFileFilter(new FileNameExtensionFilter("RAR�ĵ�", "rar"));
        chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showOpenDialog(this);// ��ʾ�ļ��򿪶Ի���
        if (option != JFileChooser.APPROVE_OPTION)
            return;
        rarFile = chooser.getSelectedFile();// ��ȡѡ���rar�ļ�
        rarFileField.setText(rarFile.toString());// ��ʾrar�ļ����ı���
        try {
            // ������ʱ�ļ�
            File tempFile = File.createTempFile("rar", ".txt");
            // ִ����ȡע�������ע����Ϣ��������ʱ�ļ���
            Process process = getRuntime().exec(
                    "rar cw \"" + rarFile + "\" \"" + tempFile + "\" -y");
            process.getOutputStream().close();// �رս��������
            Scanner sc = new Scanner(process.getInputStream());
            while (sc.hasNext()) {
                sc.nextLine();// ���������
            }
            process.getInputStream().close();// �ر�������
            annotateArea.setText("");// ����ı�������
            Scanner scan = new Scanner(tempFile);// ������ȡ��ʱ�ļ���ɨ����
            while (scan.hasNext()) {
                // ��ע����Ϣ��ʾ���ı���ؼ���
                annotateArea.append(scan.next() + "\n");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * �رհ�ť���¼�������
     * 
     * @param e
     */
    protected void do_closeButton_actionPerformed(ActionEvent e) {
        this.dispose();
    }
    
    /**
     * ����޸İ�ť���¼�������
     * 
     * @param e
     */
    protected void do_annotateButton_actionPerformed(ActionEvent e) {
        String annotateStr = annotateArea.getText();// ��ȡע���ı�
        int length = annotateStr.getBytes().length;// ��ȡע���ı�����
        if (length > 32767) {// �����ı�����
            JOptionPane.showMessageDialog(null, "ע�ͳ��Ȳ��ܴ���32767");
            return;
        }
        try {
            Process process = getRuntime().exec(// ִ�����ע������
                    "rar c \"" + rarFile + "\"");
            // ��ע���ı����ݸ�ע������
            process.getOutputStream().write(annotateStr.getBytes());
            process.getOutputStream().close();// �ر������
            process.getInputStream().close();// �ر�������
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
