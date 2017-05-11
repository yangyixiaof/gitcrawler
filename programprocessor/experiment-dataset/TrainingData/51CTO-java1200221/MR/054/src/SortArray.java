import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SortArray extends JFrame {
    
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
        setTitle("\u4F7F\u7528Sort\u65B9\u6CD5\u5BF9\u6570\u7EC4\u8FDB\u884C\u6392\u5E8F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel(
                "\u8F93\u5165\u6570\u7EC4\u5185\u5BB9\uFF0C\u7A7A\u683C\u4E3A\u6570\u7EC4\u5143\u7D20\u5206\u9694\u7B26");
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
        
        JButton button = new JButton("\u6392\u5E8F");
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
