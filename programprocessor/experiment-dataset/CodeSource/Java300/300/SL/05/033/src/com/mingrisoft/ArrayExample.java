package com.mingrisoft;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ArrayExample extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2577935223412903226L;
    private JPanel contentPane;
    private JTextField nameField;
    private JTextArea personnelArea;
    private JTextArea resultArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ArrayExample frame = new ArrayExample();
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
    public ArrayExample() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 498, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("�������������ȡ���˹���");
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel
                .setBorder(new TitledBorder(
                        null,
                        "�����ڳ������������س�",
                        TitledBorder.LEADING, TitledBorder.TOP, null,
                        new Color(59, 59, 59)));
        panel.setBounds(10, 10, 174, 242);
        contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 5));
        
        nameField = new JTextField();
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                do_textField_keyPressed(e);
            }
        });
        panel.add(nameField, BorderLayout.NORTH);
        nameField.setColumns(10);
        
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);
        
        personnelArea = new JTextArea();
        personnelArea.setEditable(false);
        scrollPane.setViewportView(personnelArea);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null,
                "ѡȡ������Ա��",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59,
                        59)));
        panel_1.setBounds(183, 10, 219, 242);
        contentPane.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane_1 = new JScrollPane();
        panel_1.add(scrollPane_1);
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        scrollPane_1.setViewportView(resultArea);
        
        JButton button = new JButton("��ȡ");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(407, 164, 63, 25);
        contentPane.add(button);
        
        JButton button_1 = new JButton("�˳�");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        button_1.setBounds(407, 215, 63, 25);
        contentPane.add(button_1);
    }
    
    protected void do_textField_keyPressed(KeyEvent e) {
        if (e.getKeyChar() != '\n')// ���ǻس��ַ���������
            return;
        String name = nameField.getText();
        if (name.isEmpty())// ����ı���û���ַ�����������
            return;
        personnelArea.append(name + "\n");// ������������س�����ӵ���Ա�б�
        nameField.selectAll();// ѡ���ı��������ַ�
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String perstring = personnelArea.getText();// ��ȡ��Ա�б��ı�
        String[] personnelArray = perstring.split("\n{1,}");// ��ȡ��Ա����
        int index = (int) (Math.random() * personnelArray.length);// ���������������
        // ���������ʽ�������н���Ϣ
        String formatArg = "���γ�ȡ������Ա��\n\t%1$s\n��ϲ%1$5s��Ϊ���ι��ڳ齱�Ĵ󽱵�����"
                + "\n\n���ǽ�Ϊ%1$s**�䷢��\n\t���ڵ����̶�ʮ�䡣";
        // Ϊ�н���Ϣ�����Ա����
        String info = String.format(formatArg, personnelArray[index]);
        resultArea.setText(info);// ���ı�����ʾ�н���Ϣ
    }
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        dispose();
    }
}
