package com.mingrisoft;

import static java.lang.Runtime.getRuntime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class RenameFileFromRAR extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3321288373569648263L;
    private JPanel contentPane;
    private JTextField rarFileField;
    private File rarFile;
    private JTable table;
    private JTextField newFileField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RenameFileFromRAR frame = new RenameFileFromRAR();
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
    public RenameFileFromRAR() {
        setTitle("\u4ECERAR\u538B\u7F29\u5305\u4E2D\u5220\u9664\u6587\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 532, 373);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 5));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(5, 0));

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
        rarFileField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.add(rarFileField, BorderLayout.CENTER);
        rarFileField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(2, 200));
        panel.add(scrollPane, BorderLayout.SOUTH);
        scrollPane.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "\u538B\u7F29\u6587\u4EF6\u5217\u8868\uFF1A",
                TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(450, 100));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u6587\u4EF6\u540D\u79F0", "\u5927\u5C0F", "\u538B\u7F29\u540E\u5927\u5C0F",
                "\u538B\u7F29\u7387", "\u65F6\u95F4" }));
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                int row = table.getSelectedRow();
                if (row < 0)
                    return;
                String value = table.getValueAt(row, 0) + "";
                newFileField.setText(value);
            }
        });
        scrollPane.setViewportView(table);

        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setHgap(30);
        contentPane.add(panel_1, BorderLayout.SOUTH);

        JButton renameButton = new JButton("\u91CD\u547D\u540D");
        renameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_renameButton_actionPerformed(e);
            }
        });
        panel_1.add(renameButton);

        JButton closeButton = new JButton("\u5173\u95ED");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_closeButton_actionPerformed(e);
            }
        });
        panel_1.add(closeButton);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JLabel label = new JLabel(
                "\u8F93\u5165\u65B0\u6587\u4EF6\u540D\u79F0\uFF08\u6CE8\u610F\u4FEE\u6539\u6587\u4EF6\u8DEF\u5F84\u4F1A\u5BFC\u81F4\u6587\u4EF6\u79FB\u52A8\uFF09:");
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        panel_2.add(label, BorderLayout.WEST);

        newFileField = new JTextField();
        newFileField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_2.add(newFileField, BorderLayout.SOUTH);
        newFileField.setColumns(10);
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
        resolveFileList();
    }

    /**
     * �����ļ��б���Ϣ�����ؼ�
     */
    private void resolveFileList() {
        try {
            // ִ����ȡע�������ע����Ϣ��������ʱ�ļ���
            Process process = getRuntime().exec("rar v -c- \"" + rarFile + "\"");
            process.getOutputStream().close();// �رս��������
            Scanner sc = new Scanner(process.getInputStream());
            int count = 0;// ����������
            // ��ȡ���ؼ�ģ��
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            Vector<String> row = new Vector<String>();// ��������������
            do {
                String line = sc.nextLine();// ��ȡ�ļ��б���Ϣ��һ��
                // �����ʼ��������
                if (line.contains("----------------------")) {
                    count = (count == 0 ? count + 1 : -1);
                    continue;
                }
                if (count == 0)// ������ʼ���
                    continue;
                if (count == -1)// �ڽ��������ֹѭ��
                    break;
                if (++count % 2 == 0) {// ��ȡ�ļ�����
                    row.add(line);
                } else {// ��ȡ�ļ���ϸ��Ϣ
                    // ���ļ���ϸ��Ϣ�ָ�Ϊ����
                    String[] split = line.trim().split("\\s+");
                    for (String string : split) {// ������ϸ��Ϣ����
                        row.add(string);// ��ÿ����ϸ�������Ϊ���Ԫ����
                    }
                    // ����������ӵ��������ģ��
                    model.addRow(row.toArray());
                    row.clear();// �����������������Ϊ��һ�н�����׼��
                }
            } while (sc.hasNext());
            process.getInputStream().close();// �ر�������
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
        dispose();
    }

    /**
     * ��������ť���¼�������
     * 
     * @param e
     */
    protected void do_renameButton_actionPerformed(ActionEvent e) {
        // ��ȡ�������ģ��
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int selectedRow = table.getSelectedRow();// ��ȡ���ǰѡ����
        if (selectedRow < 0)
            return;
        // ��ȡѡ�����е��ļ���
        String path = model.getValueAt(selectedRow, 0).toString();
        String newFile = newFileField.getText();// ��ȡ���ļ�����
        try {
            // ִ��rar��������
            Process exec = getRuntime().exec("rar rn -c- \"" + rarFile + "\" " + path + " " + newFile);
            // ��������������
            Scanner scan = new Scanner(exec.getInputStream());
            while (scan.hasNext()) {// ��������������
                scan.nextLine();// �������������
            }
            scan.close();// �ر�������
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        resolveFileList();// ���ر�����ļ��б�����
    }
}
