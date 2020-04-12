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
        setTitle("unseen word");
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
        
        j_label = new JLabel("unseen word");
        j_label.setBounds(116, 82, 304, 18);
        content_pane.add(j_label);
        
        j_label_1 = new JLabel(
                "unseen: 3 5 2 562 125");
        j_label_1.setBounds(6, 6, 422, 18);
        content_pane.add(j_label_1);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String arrayStr = text_field.getText().trim();
        if(arrayStr.equals("")){
        	JOptionPane.showMessageDialog(null, "unseed");
        	return;
        }
        for (int i = 0; i < arrayStr.length(); i++) {
            char charAt = arrayStr.charAt(i);
            if (!Character.isDigit(charAt) && charAt != ' ') {
                JOptionPane.showMessageDialog(null, "unseen");
                text_field.setText("");
                return;
            }
        }
        String[] numStrs = arrayStr.split(" {1,}");
        int[] numArray = new int[numStrs.length];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = Integer.valueOf(numStrs[i]);
        }
        int min = numArray[0];
        for (int j = 0; j < numArray.length; j++) {
            if (min > numArray[j]) {
                min = numArray[j];
            }
        }
        j_label.setText("unseed" + min);
    }
}