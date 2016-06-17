package com.mingrisoft.jmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class HorizontalMenuTest extends JFrame {
    private static final long serialVersionUID = 1686879401938151474L;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HorizontalMenuTest frame = new HorizontalMenuTest();
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
public HorizontalMenuTest() {
        setTitle("\u7EB5\u5411\u83DC\u5355\u680F");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    Container contentPane = getContentPane();
    contentPane.setBackground(Color.WHITE);
    JMenuBar menuBar = new JMenuBar();
    menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
    contentPane.add(menuBar, BorderLayout.WEST);
    
    JMenu fileMenu = new HorizontalMenu("�ļ�(F)");
    fileMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
    fileMenu.add("�½�(N)");
    fileMenu.add("��(O)...");
    fileMenu.add("����(S)");
    fileMenu.add("���Ϊ(A)...");
    fileMenu.add("ҳ������(U)...");
    fileMenu.add("��ӡ(P)...");
    fileMenu.add("�˳�(X)");
    menuBar.add(fileMenu);
    
    JMenu editMenu = new HorizontalMenu("�༭(E)");
    editMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
    editMenu.add("����(U)");
    editMenu.add("����(T)");
    editMenu.add("����(C)");
    editMenu.add("ճ��(P)");
    editMenu.add("ɾ��(L)");
    editMenu.add("����(F)...");
    editMenu.add("������һ��(N)");
    editMenu.add("�滻(R)...");
    editMenu.add("ת��(G)...");
    editMenu.add("ȫѡ(A)");
    editMenu.add("ʱ��/����(D)");
    menuBar.add(editMenu);
    
    JMenu formatMenu = new HorizontalMenu("��ʽ(O)");
    formatMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
    formatMenu.add("�Զ�����(W)");
    formatMenu.add("����(F)...");
    menuBar.add(formatMenu);
    
    JMenu viewMenu = new HorizontalMenu("�鿴(V)");
    viewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
    viewMenu.add("״̬��(S)");
    menuBar.add(viewMenu);
    
    JMenu helpMenu = new HorizontalMenu("����(H)");
    helpMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
    helpMenu.add("�鿴����(H)");
    helpMenu.add("���ڼ��±�(A)");
    menuBar.add(helpMenu);
}
}
