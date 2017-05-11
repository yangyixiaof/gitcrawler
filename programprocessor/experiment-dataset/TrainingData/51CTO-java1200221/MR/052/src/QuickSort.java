import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class QuickSort extends JFrame {
    
    private JPanel contentPane;
    
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
                    QuickSort frame = new QuickSort();
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
    public QuickSort() {
        setTitle("\u4F7F\u7528\u5FEB\u901F\u6392\u5E8F\u6CD5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
                Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);
        
        JButton button = new JButton("\u751F\u6210\u968F\u673A\u6570");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        contentPane.add(textField, gbc_textField);
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 5, 0);
        gbc_button.gridx = 0;
        gbc_button.gridy = 1;
        contentPane.add(button, gbc_button);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 2;
        contentPane.add(scrollPane_1, gbc_scrollPane_1);
        
        textArea2 = new JTextArea();
        scrollPane_1.setViewportView(textArea2);
        
        JButton button_1 = new JButton("\u5FEB\u901F\u6392\u5E8F\u6CD5");
        button_1.setActionCommand("\u5FEB\u901F\u6392\u5E8F\u6CD5");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.gridx = 0;
        gbc_button_1.gridy = 3;
        contentPane.add(button_1, gbc_button_1);
    }
    
    private int[] array = new int[10];
    private JTextField textField;
    private JTextArea textArea2;
    
    protected void do_button_actionPerformed(ActionEvent e) {
        Random random = new Random();// �������������
        String text = "";
        for (int i = 0; i < array.length; i++) {// ��ʼ������Ԫ��
            array[i] = random.nextInt(90);// ����50���ڵ������
            text += (array[i] + "  ");// ������Ԫ����ʾ���ı���ؼ���
        }
        textField.setText(text);
    }
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        textArea2.setText("");// ����ı���
        quickSort(array, 0, array.length - 1);// ���ÿ��������㷨
    }
    
    private void quickSort(int sortarray[], int lowIndex, int highIndex) {
        int lo = lowIndex;// ��¼��С����
        int hi = highIndex;// ��¼�������
        int mid;// ��¼�ֽ��Ԫ��
        if (highIndex > lowIndex) {
            mid = sortarray[(lowIndex + highIndex) / 2];// ȷ���м�ֽ��Ԫ��ֵ
            while (lo <= hi) {
                while ((lo < highIndex) && (sortarray[lo] < mid))
                    ++lo;// ȷ�������ڷֽ�Ԫ��ֵ����С����
                while ((hi > lowIndex) && (sortarray[hi] > mid))
                    --hi;// ȷ�����ڷֽ�Ԫ��ֵ���������
                if (lo <= hi) {// �����С���������û���ص�
                    swap(sortarray, lo, hi);// ��������������Ԫ��
                    ++lo;// ������С����
                    --hi;// �ݼ��������
                }
            }
            if (lowIndex < hi)// �ݹ�����û��δ�ֽ�Ԫ��
                quickSort(sortarray, lowIndex, hi);
            if (lo < highIndex)// �ݹ�����û��δ�ֽ�Ԫ��
                quickSort(sortarray, lo, highIndex);
        }
    }
    
    private void swap(int swapArray[], int i, int j) {
        int temp = swapArray[i];// ��������Ԫ��
        swapArray[i] = swapArray[j];
        swapArray[j] = temp;
        for (int k = 0; k < array.length; k++) {// ������Ԫ����ʾ���ı���
            textArea2.append(array[k] + "  ");
        }
        textArea2.append("\n");// ׷�ӻ��з�
    }
    
}
