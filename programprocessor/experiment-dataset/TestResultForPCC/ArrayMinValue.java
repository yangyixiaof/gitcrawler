package test;
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
    private JPanel contentPane;
    private JTextField textField;
    private JLabel label;
    private JLabel label_1;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
        	// right 1
            e.printStackTrace();
        }
    	// right 2
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ArrayMinValue frame = new ArrayMinValue();
                    // right 1
                    frame.setVisible(true);
                } catch (Exception e) {
                	// right 1
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
        // right 2
        setBounds(100, 100, 450, 149);
        // right 1
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        // right 1.5
        setTitle("获取一维数组的最小值");
        contentPane.setLayout(null);
        // right 1.5
        textField = new JTextField();
        textField.setBounds(6, 36, 414, 30);
        // right 3.5
        contentPane.add(textField);
        // right 1
        textField.setColumns(10);
        // right 4
        JButton button = new JButton("\u8BA1\u7B97");
        // right 2
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// right 2
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(16, 76, 90, 30);
        // right 1
        contentPane.add(button);
        
        label = new JLabel("最小值：");
        // right 2
        label.setBounds(116, 82, 304, 18);
        // right 2
        contentPane.add(label);
        
        label_1 = new JLabel(
                "请在文本框中输入多个整数，以空格为分隔符。例如：3 5 2 562 125");
        // right 1
        label_1.setBounds(6, 6, 422, 18);
        // right 2
        contentPane.add(label_1);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String arrayStr = textField.getText().trim();			//去除左右空格
        if(arrayStr.equals("")){
        	JOptionPane.showMessageDialog(null, "请输入数字内容");
        	/// right 1
        	return;
        }
        for (int i = 0; i < arrayStr.length(); i++) {				// 过滤非法输入
            char charAt = arrayStr.charAt(i);
            if (!Character.isDigit(charAt) && charAt != ' ') {
            	// right 1
                JOptionPane.showMessageDialog(null, "输入包含非数字内容");
                // right 1
                textField.setText("");
                // right 1
                return;
            }
        }
        // right 2
        String[] numStrs = arrayStr.split(" {1,}");			// 分割字符串
        // right 2.5
        int[] numArray = new int[numStrs.length];			// 创建整型数组
        // 转换输入为整型数组
        // right 5.5
        for (int i = 0; i < numArray.length; i++) {
        	// right 5.5
            numArray[i] = Integer.valueOf(numStrs[i]);
        }
        // right 1
        int min = numArray[0];							// 创建最小数变量
        for (int j = 0; j < numArray.length; j++) {
        	// right 4
            if (min > numArray[j]) {					// 提取最小整数
            	// right 2
                min = numArray[j];
            }
        }
        label.setText("数组中最小的数是：" + min);		//显示最小值到指定的标签中
    }
}