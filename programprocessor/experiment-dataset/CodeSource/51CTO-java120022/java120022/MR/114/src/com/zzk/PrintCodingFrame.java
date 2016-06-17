package com.zzk;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import jbarcodebean.JBarcodeBean;

public class PrintCodingFrame extends JFrame {
    
    private JTextField textField;
    private CodePanel codePanel;
    public static PrintCodingFrame frame;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        frame = new PrintCodingFrame();
        frame.setVisible(true);
    }
    
    /**
     * Create the frame
     */
    public PrintCodingFrame() {
        super();
        setTitle("������ӡ������");
        setBounds(100, 100, 760, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);
        
        final JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        
        codePanel = new CodePanel();
        panel.add(codePanel);
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("����13λ���ڵı�ţ�");
        panel_1.add(label);
        
        textField = new JTextField();
        textField.addCaretListener(new CaretListener() {
            public void caretUpdate(final CaretEvent arg0) {
                String text = textField.getText();// ��ȡ�ı�������
                Component[] codes = codePanel.getComponents();// ��ȡ����������
                for (Component component : codes) {// �����������
                    if (component instanceof JBarcodeBean) {// �����������������
                        JBarcodeBean bean = (JBarcodeBean) component;// ǿ��ת��Ϊ���������
                        bean.setCode(text);// ��������ı���
                    }
                }
                codePanel.repaint();
            }
        });
        textField.setColumns(15);
        panel_1.add(textField);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // ������ӡԤ���Ի���
                PreviewDialog dialog = new PreviewDialog(PrintCodingFrame.this, true);
                dialog.setSize(600, 400);// ���öԻ����С
                dialog.setVisible(true);// ��ʾ�Ի���
            }
        });
        button_1.setText("��ӡԤ��");
        panel_1.add(button_1);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                codePanel.pageSetup();// ����ҳ�����÷���
            }
        });
        button.setText("ҳ������");
        panel_1.add(button);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                codePanel.doPrint();// ���ô�ӡ����
            }
        });
        button_2.setText("��ӡ");
        panel_1.add(button_2);
        //
    }
    
    public CodePanel getCodePanel() {
        return codePanel;
    }
    
}
