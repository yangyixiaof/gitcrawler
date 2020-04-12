package com.mingrisoft;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ArrayMinValue extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8388043412533827271L;
    private JPanel content_pane;
    private JTextField text_field;
    private JLabel j_label;
    private JLabel j_label_1;
    
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
                    ArrayMinValue frame = new ArrayMinValue();
                    frame.setVisible(true);
                } catch (Exception e) {
					// right 2
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public ArrayMinValue() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 149);
        content_pane = new JPanel();
        content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pane);
        setTitle("锟斤拷取一维锟斤拷锟斤拷锟斤拷锟叫≈�");
        content_pane.setLayout(null);
        
        text_field = new JTextField();
        text_field.setBounds(6, 36, 414, 30);
        content_pane.add(text_field);
        text_field.setColumns(10);
        
        JButton button = new JButton("\u8BA1\u7B97");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(16, 76, 90, 30);
        content_pane.add(button);
        
        j_label = new JLabel("锟斤拷小值锟斤拷");
        j_label.setBounds(116, 82, 304, 18);
        content_pane.add(j_label);
        
        j_label_1 = new JLabel(
                "锟斤拷锟斤拷锟侥憋拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟皆空革拷为锟街革拷锟斤拷锟斤拷锟斤拷锟界：3 5 2 562 125");
        j_label_1.setBounds(6, 6, 422, 18);
        content_pane.add(j_label_1);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String arrayStr = text_field.getText().trim();			//去锟斤拷锟斤拷锟揭空革拷
        if(arrayStr.equals("")){
        	JOptionPane.showMessageDialog(null, "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷");
        	return;
        }
        for (int i = 0; i < arrayStr.length(); i++) {				// 锟斤拷锟剿非凤拷锟斤拷锟斤拷
            char charAt = arrayStr.charAt(i);
            if (!Character.isDigit(charAt) && charAt != ' ') {
                JOptionPane.showMessageDialog(null, "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�");
                text_field.setText("");
                return;
            }
        }
        String[] numStrs = arrayStr.split(" {1,}");			// 锟街革拷锟街凤拷锟斤拷
        int[] numArray = new int[numStrs.length];			// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
        // 转锟斤拷锟斤拷锟斤拷为锟斤拷锟斤拷锟斤拷锟斤拷
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = Integer.valueOf(numStrs[i]);
        }
        int min = numArray[0];							// 锟斤拷锟斤拷锟斤拷小锟斤拷锟斤拷锟斤拷
        for (int j = 0; j < numArray.length; j++) {
            if (min > numArray[j]) {					// 锟斤拷取锟斤拷小锟斤拷锟斤拷
                min = numArray[j];
            }
        }
        j_label.setText("锟斤拷锟斤拷锟斤拷锟斤拷小锟斤拷锟斤拷锟角ｏ拷" + min);		//锟斤拷示锟斤拷小值锟斤拷指锟斤拷锟侥憋拷签锟斤拷
    }
}