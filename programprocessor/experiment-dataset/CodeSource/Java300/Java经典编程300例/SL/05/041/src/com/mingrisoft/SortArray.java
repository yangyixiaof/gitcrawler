package com.mingrisoft;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SortArray extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6185462970912293135L;
    private JPanel contentPane;
    private JTextField arrayField;
    private JTextArea sortArea;
    
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
                    SortArray frame = new SortArray();
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
    public SortArray() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("ʹ��Sort�����������������");
        contentPane.setLayout(null);
        
        JLabel label = new JLabel(
                "�����������ݣ��ո�Ϊ����Ԫ�طָ���");
        label.setBounds(6, 6, 265, 18);
        contentPane.add(label);
        
        arrayField = new JTextField();
        arrayField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                String mask = "0123456789 " + (char) 8;
                if (mask.indexOf(key) == -1) {
                    e.consume();
                }
            }
            
        });
        arrayField.setBounds(6, 36, 422, 30);
        contentPane.add(arrayField);
        arrayField.setColumns(10);
        
        JButton button = new JButton("����");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(164, 78, 90, 30);
        contentPane.add(button);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 118, 422, 138);
        contentPane.add(scrollPane);
        
        sortArea = new JTextArea();
        sortArea.setLineWrap(true);
        scrollPane.setViewportView(sortArea);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String text = arrayField.getText();// ��ȡ�û�����
        text=text.trim();		//ȥ����β�ո�
        if("".equals(text)){
        	JOptionPane.showMessageDialog(null,"������Ҫ�������������");
        	return;
        }
        String[] arrayStr = text.split(" {1,}");// �������Ϊ����
        int[] array = new int[arrayStr.length];// ����������������
        sortArea.setText("����ԭ�����ݣ�\n");
        for (String string : arrayStr) {// ���ԭ����������
            sortArea.append(string + "    ");
        }
        for (int i = 0; i < array.length; i++) {// ��ʼ����������
            array[i] = Integer.parseInt(arrayStr[i]);
        }
        sortArea.append("\n");
        Arrays.sort(array);// ʹ��sort���������������������
        sortArea.append("�������������ݣ�\n");
        for (int value : array) {// �����������������
            sortArea.append(value + "    ");
        }
    }
    
    protected void do_arrayField_keyPressed(KeyEvent e) {
        char key = e.getKeyChar();// ��ȡ�û������ַ�
        String mask = "0123456789 " + (char) 8;// ����淶���ַ�ģ��
        if (mask.indexOf(key) == -1) {// �жϰ����ַ��Ƿ����ڹ淶���ַ���Χ
            e.consume();// ȡ���ǹ淶���ַ���������Ч��
        }
    }
}
