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
        setTitle("批量打印条形码");
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
        label.setText("输入13位以内的编号：");
        panel_1.add(label);
        
        textField = new JTextField();
        textField.addCaretListener(new CaretListener() {
            public void caretUpdate(final CaretEvent arg0) {
                String text = textField.getText();// 获取文本框内容
                Component[] codes = codePanel.getComponents();// 获取面板所有组件
                for (Component component : codes) {// 遍历组件数组
                    if (component instanceof JBarcodeBean) {// 如果组件是条形码组件
                        JBarcodeBean bean = (JBarcodeBean) component;// 强制转换为条形码对象
                        bean.setCode(text);// 设置组件的编码
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
                // 创建打印预览对话框
                PreviewDialog dialog = new PreviewDialog(PrintCodingFrame.this, true);
                dialog.setSize(600, 400);// 设置对话框大小
                dialog.setVisible(true);// 显示对话框
            }
        });
        button_1.setText("打印预览");
        panel_1.add(button_1);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                codePanel.pageSetup();// 调用页面设置方法
            }
        });
        button.setText("页面设置");
        panel_1.add(button);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                codePanel.doPrint();// 调用打印方法
            }
        });
        button_2.setText("打印");
        panel_1.add(button_2);
        //
    }
    
    public CodePanel getCodePanel() {
        return codePanel;
    }
    
}
