import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class ArrayReverseString extends JFrame {
    
    private JPanel contentPane;
    private JTextField inputField;
    private JTextField outputField;
    private JTextArea infoArea;
    
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
                    ArrayReverseString frame = new ArrayReverseString();
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
    public ArrayReverseString() {
        setTitle("\u7528\u6570\u7EC4\u628A\u5B57\u7B26\u4E32\u53CD\u8F6C");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 270);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 5));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 90, 325, 0 };
        gbl_panel.rowHeights = new int[] { 30, 30, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);
        
        JLabel label = new JLabel(
                "\u8F93\u5165\u4E00\u4E2A\u5B57\u7B26\u4E32\uFF1A");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.ipadx = 1;
        gbc_label.anchor = GridBagConstraints.WEST;
        gbc_label.insets = new Insets(0, 0, 5, 0);
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        panel.add(label, gbc_label);
        
        inputField = new JTextField();
        GridBagConstraints gbc_inputField = new GridBagConstraints();
        gbc_inputField.anchor = GridBagConstraints.NORTH;
        gbc_inputField.fill = GridBagConstraints.HORIZONTAL;
        gbc_inputField.insets = new Insets(0, 0, 5, 0);
        gbc_inputField.gridx = 1;
        gbc_inputField.gridy = 0;
        panel.add(inputField, gbc_inputField);
        inputField.setColumns(10);
        
        JButton button = new JButton("\u53CD\u8F6C");
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.anchor = GridBagConstraints.NORTH;
        gbc_button.insets = new Insets(0, 0, 0, 5);
        gbc_button.gridx = 0;
        gbc_button.gridy = 1;
        panel.add(button, gbc_button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        
        outputField = new JTextField();
        GridBagConstraints gbc_outputField = new GridBagConstraints();
        gbc_outputField.anchor = GridBagConstraints.NORTH;
        gbc_outputField.fill = GridBagConstraints.HORIZONTAL;
        gbc_outputField.gridx = 1;
        gbc_outputField.gridy = 1;
        panel.add(outputField, gbc_outputField);
        outputField.setColumns(10);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        infoArea = new JTextArea();
        scrollPane.setViewportView(infoArea);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String inputStr = inputField.getText();// ��ȡ�û������ַ���
        char[] strArray = inputStr.toCharArray();// ��ȡ�ַ�����
        infoArea.setText("");// ����ı���ؼ��ı�
        for (int i = 0; i < strArray.length / 2; i++) {// ���鷴ת�㷨
            char temp = strArray[i];// ��������Ԫ��
            strArray[i] = strArray[strArray.length - i - 1];
            strArray[strArray.length - i - 1] = temp;
            infoArea.append("��" + (i + 1) + "��ѭ��:\t");// ��ʾѭ����ת����
            for (char c : strArray) {// ��ʾÿ�η�ת����Ľ��
                infoArea.append(c + "");
            }
            infoArea.append("\n");// �ı�����
        }
        String outputStr = new String(strArray);// ���ַ�����ת��Ϊ�ַ���
        outputField.setText(outputStr);// ��ʾ��ת����ַ���
    }
}
