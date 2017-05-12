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
    private JPanel cPane;
    private JTextField textField;
    private JLabel lbl;
    private JLabel lbl_1;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
			// right 1
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
			// right 2
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
		// right 1
        setBounds(100, 100, 450, 149);
        cPane = new JPanel();
        cPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(cPane);
        setTitle("��ȡһά�������Сֵ");
        cPane.setLayout(null);
        // wrong because of too long tokens training and no context search.
        textField = new JTextField();
        // wrong because of too long tokens training and no context search.
        textField.setBounds(6, 36, 414, 30);
        cPane.add(textField);
        // right 1
        textField.setColumns(10);
        // right 1
        JButton button = new JButton("\u8BA1\u7B97");
        // wrong because of no context search.
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1) {
                do_button_actionPerformed(e1);
            }
        });
        // right 3
        button.setBounds(16, 76, 90, 30);
        cPane.add(button);
        
        lbl = new JLabel("��Сֵ��");
        lbl.setBounds(116, 82, 304, 18);
        cPane.add(lbl);
        
        lbl_1 = new JLabel(
                "�����ı������������������Կո�Ϊ�ָ��������磺3 5 2 562 125");
        lbl_1.setBounds(6, 6, 422, 18);
        cPane.add(lbl_1);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String arrayStr = textField.getText().trim();			//ȥ�����ҿո�
        if(arrayStr.equals("")){
        	// rithg 1
        	JOptionPane.showMessageDialog(null, "��������������");
        	// right 5
        	return;
        }
        for (int i = 0; i < arrayStr.length(); i++) {				// ���˷Ƿ�����
            char charVal = arrayStr.charAt(i);
            if (!Character.isDigit(charVal) && charVal != ' ') {
            	// right 1
                JOptionPane.showMessageDialog(null, "�����������������");
                textField.setText("");
                // right 5
                return;
            }
        }
        String[] numStrings = arrayStr.split(" {1,}");
        int[] numArray = new int[numStrings.length];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = Integer.valueOf(numStrings[i]);
        }
        // right 1
        int minVal = numArray[0];
        // right 1
        for (int j = 0; j < numArray.length; j++) {
            if (minVal > numArray[j]) {
                minVal = numArray[j];
            }
        }
        lbl.setText("��������С�����ǣ�" + minVal);
    }
}