package com.mingrisoft.ballot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

class Candidate extends JCheckBox { // ���������࣬����̳�JCheckBox��
    int len = 0;
    
    Candidate(String name, Icon icon) { // �����������������
        super(name, icon);
    }
    
    public int getBallot(String name) {
        File file = new File("C://count.txt"); // �����ļ�����
        FileReader fis;
        try {
            if (!file.exists()) // ������ļ�������
                file.createNewFile(); // �½��ļ�
            fis = new FileReader(file);
            BufferedReader bis = new BufferedReader(fis); // ����BufferedReader����
            String str[] = new String[3];
            String size;
            int i = 0;
            while ((size = bis.readLine()) != null) { // ѭ����ȡ�ļ�����
                str[i] = size.trim(); // ȥ���ַ����еĿո�
                if (str[i].startsWith(name)) {
                    int length = str[i].indexOf(":");
                    String sub = str[i].substring(length + 1, str[i].length()); // ���ַ������н�ȡ
                    len = Integer.parseInt(sub);
                    continue;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }
    
    public void addBallot(String name) { // ��������ѡƱ����
        File file = new File("C://count.txt"); // �����ļ�����
        FileReader fis;
        try {
            if (!file.exists()) // ������ļ�������
                file.createNewFile(); // �½��ļ�
            fis = new FileReader(file); // ��FileReader�������ʵ����
            BufferedReader bis = new BufferedReader(fis);
            String str[] = new String[3];
            String size;
            int i = 0;
            while ((size = bis.readLine()) != null) { // ѭ����ȡ�ļ�
                str[i] = size.trim();
                if (str[i].startsWith(name)) {
                    int length = str[i].indexOf(":"); // ��ȡָ���ַ�����λ��
                    String sub = str[i].substring(length + 1, str[i].length()); // ���ַ������н�ȡ
                    len = Integer.parseInt(sub) + 1;
                    break;
                }
                i++;
            }
            FileWriter fw = new FileWriter(file); // ����FileWriter ����
            BufferedWriter bufw = new BufferedWriter(fw);
            bufw.write(name + ":" + len); // ������д����
            
            bufw.close(); // �ر���
            fw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyMin extends JFrame implements ActionListener {
    Box baseBox, boxH, boxV; // ����Box����
    JTextArea text; // ����JTextArea����
    JButton button; // ����JButton����
    Candidate candidateOne, candidateTwo, candidateThree;
    
    public MyMin() { // �ڹ��췽�������ô��岼��
        setBounds(100, 100, 500, 120);
        setVisible(true);
        setTitle("ѡ�������еĺøɲ�����");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { // ����ر��¼�
                System.exit(0);
            }
        });
        baseBox = Box.createHorizontalBox();
        boxH = Box.createHorizontalBox();
        boxV = Box.createHorizontalBox();
        candidateOne = new Candidate("С��", new ImageIcon(getClass()
                .getResource("0.gif")));
        candidateTwo = new Candidate("С��", new ImageIcon(getClass()
                .getResource("1.gif")));
        candidateThree = new Candidate("С��", new ImageIcon(getClass()
                .getResource("2.gif")));
        candidateOne.setSelectedIcon(new ImageIcon(getClass().getResource(
                "0.gif")));
        candidateTwo.setSelectedIcon(new ImageIcon(getClass().getResource(
                "1.gif")));
        candidateThree.setSelectedIcon(new ImageIcon(getClass().getResource(
                "2.gif")));
        boxH.add(candidateOne);
        boxH.add(candidateTwo);
        boxH.add(candidateThree);
        text = new JTextArea();
        button = new JButton("��ʾ��Ʊ��");
        button.addActionListener(this);
        boxV.add(text);
        boxV.add(button);
        boxV.add(boxH);
        baseBox.add(boxV);
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        con.add(baseBox);
        con.validate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        text.setText(null);
        File file = new File("C://count.txt"); // �����ļ�����
        if (!file.exists()) { // ������ļ�������
            try {
                file.createNewFile(); // �½��ļ�
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (candidateOne.isSelected()) {
            candidateOne.addBallot(candidateOne.getText());
        }
        if (candidateTwo.isSelected()) {
            candidateTwo.addBallot(candidateTwo.getText());
        }
        if (candidateThree.isSelected()) {
            candidateThree.addBallot(candidateThree.getText());
        }
        text.append(candidateOne.getText() + ":"
                + candidateOne.getBallot(candidateOne.getText()) + "\n");
        // ���ı�����׷����Ϣ
        text.append(candidateTwo.getText() + ":"
                + candidateTwo.getBallot(candidateTwo.getText()) + "\n");
        text.append(candidateThree.getText() + ":"
                + candidateThree.getBallot(candidateThree.getText()) + "\n");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file); // ����FileWriter�����
            BufferedWriter bufw = new BufferedWriter(fw); // ����BufferedWriter�����
            bufw.write(text.getText()); // ���ַ���������Ԫ��д�뵽�����ļ���
            bufw.close(); // ��BufferedWriter���ر�
            fw.close();
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        candidateOne.setSelected(false);
        candidateTwo.setSelected(false);
        candidateThree.setSelected(false);
    }
    
}

public class Ballot {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MyMin();
    }
}
