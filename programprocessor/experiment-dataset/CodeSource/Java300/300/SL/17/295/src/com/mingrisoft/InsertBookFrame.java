package com.mingrisoft;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class InsertBookFrame extends JFrame {    
    private JPanel contentPane;
    private JTextField bNameTextField;
    private JTextField stockTextField;
    private JTextField priceTextField;
    private JTextField concernTextField;    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertBookFrame frame = new InsertBookFrame();
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
    public InsertBookFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("���������ʱ���˵�Σ���ַ�");
        JLabel lmessageLabel = new JLabel("��ͼ�����۱���������ݣ�");
        lmessageLabel.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        lmessageLabel.setBounds(114, 23, 193, 15);
        contentPane.add(lmessageLabel);
        
        JLabel nameLabel = new JLabel("ͼ�����ƣ�");
        nameLabel.setBounds(68, 66, 66, 15);
        contentPane.add(nameLabel);
        
        bNameTextField = new JTextField();
        bNameTextField.setBounds(144, 63, 163, 21);
        contentPane.add(bNameTextField);
        bNameTextField.setColumns(10);
        
        JLabel stockLabel = new JLabel(" �� �棺");
        stockLabel.setBounds(78, 100, 54, 15);
        contentPane.add(stockLabel);
        
        stockTextField = new JTextField();
        stockTextField.setBounds(144, 97, 163, 21);
        contentPane.add(stockTextField);
        stockTextField.setColumns(10);
        
        JLabel pricLlabel = new JLabel(" �� �ۣ�");
        pricLlabel.setBounds(79, 135, 54, 15);
        contentPane.add(pricLlabel);
        
        priceTextField = new JTextField();
        priceTextField.setBounds(144, 132, 163, 21);
        contentPane.add(priceTextField);
        priceTextField.setColumns(10);
        
        JLabel concernLable = new JLabel("�����磺");
        concernLable.setBounds(80, 173, 54, 15);
        contentPane.add(concernLable);
        
        concernTextField = new JTextField();
        concernTextField.setBounds(144, 170, 163, 21);
        contentPane.add(concernTextField);
        concernTextField.setColumns(10);
        
        JButton insertButton = new JButton("���");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_insertButton_actionPerformed(arg0);
            }
        });
        insertButton.setBounds(110, 215, 74, 23);
        contentPane.add(insertButton);
        
        JButton closeButton = new JButton("�ر�");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_closeButton_actionPerformed(arg0);
            }
        });
        closeButton.setBounds(211, 215, 74, 23);
        contentPane.add(closeButton);
    }
    //��Ӱ�ť�ĵ����¼�
    protected void do_insertButton_actionPerformed(ActionEvent arg0) {
        String bookName = bNameTextField.getText();
        String stock = stockTextField.getText();
        String price = priceTextField.getText();
        String concern = concernTextField.getText();
        DoString doString = new DoString();
        doString.setGetstr(bookName);
        String newName = doString.getCheckstr();
        GoDanger danger = new GoDanger();
        BookSell sell = new BookSell();
        sell.setBookName(newName);
        sell.setStock(stock);
        sell.setPrice(Float.parseFloat(price));
        sell.setBookConcern(concern);
        danger.insertBookSell(sell);
        JOptionPane.showMessageDialog(getContentPane(), 
                "������ӳɹ���", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
        
    }
    //�رհ�ť�ĵ����¼�
    protected void do_closeButton_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
    
}
