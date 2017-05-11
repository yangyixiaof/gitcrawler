package com.mingrisoft;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HobbyFrame extends JFrame {

    private static final long serialVersionUID = -3091839699328509198L;
    private JPanel contentPane;
    JCheckBox checkBox1;
    JCheckBox checkBox4;
    JCheckBox checkBox2;
    JCheckBox checkBox5;
    JCheckBox checkBox3;
    JCheckBox checkBox6;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HobbyFrame frame = new HobbyFrame();
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
    public HobbyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 344, 224);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("���˰���");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 335, 186);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("��ĸ��˰����ǣ�");
        label.setBounds(41, 21, 105, 24);
        panel.add(label);

        checkBox1 = new JCheckBox("��Ӿ");
        checkBox1.setBounds(41, 51, 103, 23);
        panel.add(checkBox1);

        checkBox4 = new JCheckBox("����");
        checkBox4.setBounds(187, 51, 103, 23);
        panel.add(checkBox4);

        checkBox2 = new JCheckBox("����");
        checkBox2.setBounds(41, 76, 103, 23);
        panel.add(checkBox2);

        checkBox5 = new JCheckBox("����");
        checkBox5.setBounds(187, 76, 103, 23);
        panel.add(checkBox5);

        checkBox3 = new JCheckBox("ƹ����");
        checkBox3.setBounds(41, 101, 103, 23);
        panel.add(checkBox3);

        checkBox6 = new JCheckBox("��ë��");
        checkBox6.setBounds(187, 101, 103, 23);
        panel.add(checkBox6);

        JButton saveButton = new JButton("д���ļ�");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        saveButton.setBounds(176, 140, 93, 23);
        panel.add(saveButton);
    }

    protected void do_button_actionPerformed(ActionEvent arg0) {
        StringBuffer buffer = new StringBuffer();
        if (checkBox1.isSelected()) { // �ж�ָ���ĸ�ѡ��checkBox�Ƿ�ѡ��
            buffer.append(checkBox1.getText() + " "); // ���ɱ���ַ����н���׷����Ϣ
        }
        if (checkBox4.isSelected()) {// �ж�ָ���ĸ�ѡ��checkBox�Ƿ�ѡ��
            buffer.append(checkBox4.getText() + " ");// ���ɱ���ַ����н���׷����Ϣ
        }
        if (checkBox2.isSelected()) {// �ж�ָ���ĸ�ѡ��checkBox�Ƿ�ѡ��
            buffer.append(checkBox2.getText() + " ");// ���ɱ���ַ����н���׷����Ϣ
        }
        if (checkBox5.isSelected()) {// �ж�ָ���ĸ�ѡ��checkBox�Ƿ�ѡ��
            buffer.append(checkBox5.getText() + " ");// ���ɱ���ַ����н���׷����Ϣ
        }
        if (checkBox3.isSelected()) {// �ж�ָ���ĸ�ѡ��checkBox�Ƿ�ѡ��
            buffer.append(checkBox3.getText() + " ");// ���ɱ���ַ����н���׷����Ϣ
        }
        if (checkBox6.isSelected()) {// �ж�ָ���ĸ�ѡ��checkBox�Ƿ�ѡ��
            buffer.append(checkBox6.getText() + " ");// ���ɱ���ַ����н���׷����Ϣ
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("d://hobby.txt");// �����ļ����������
            writer.write(buffer.toString());// д���û�ѡ��İ���
            writer.flush();// �������
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();// �ͷ���Դ
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
