import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowTitleBorder extends JFrame {
    
    private JPanel contentPane;
    private ButtonGroup bg;
    private Font font = null;
    private TitledBorder titledBorder;
    private JPanel panel;
    
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
                    ShowTitleBorder frame = new ShowTitleBorder();
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
    public ShowTitleBorder() {
        setTitle("ָ������ı���߿�");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 322, 221);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        panel = new JPanel();// �������
        font = new Font("����", Font.BOLD, 18);// ��ʼ���������
        titledBorder = new TitledBorder(null, "�Զ����������", TitledBorder.LEADING,
                TitledBorder.TOP, font, new Color(255, 0, 0));// ��������߿����
        panel.setBorder(titledBorder);// �������ı߿�
        contentPane.add(panel);
        panel.setLayout(null);
        
        JRadioButton radioButton = new JRadioButton("����");// �������嵥ѡ��ť
        radioButton.setActionCommand(radioButton.getText());
        radioButton.setSelected(true);// Ĭ��Ϊѡ��״̬
        radioButton.setBounds(14, 31, 104, 32);
        panel.add(radioButton);
        
        JRadioButton radioButton_1 = new JRadioButton("����");// �������嵥ѡ��ť
        radioButton_1.setActionCommand(radioButton_1.getText());
        radioButton_1.setBounds(14, 63, 104, 32);
        panel.add(radioButton_1);
        
        JRadioButton radioButton_2 = new JRadioButton("����");// �������鵥ѡ��ť
        radioButton_2.setActionCommand(radioButton_2.getText());
        radioButton_2.setBounds(14, 95, 104, 32);
        panel.add(radioButton_2);
        
        JRadioButton radioButton_3 = new JRadioButton("����");// �������ε�ѡ��ť
        radioButton_3.setActionCommand(radioButton_3.getText());
        radioButton_3.setBounds(14, 127, 104, 32);
        panel.add(radioButton_3);
        
        bg = new ButtonGroup();// ������ť��
        bg.add(radioButton);// ��4����ѡ��ť��ӵ���ť����
        bg.add(radioButton_1);
        bg.add(radioButton_2);
        bg.add(radioButton_3);
        
        JButton button = new JButton("����");// �������ð�ť
        button.addActionListener(new ActionListener() {// Ϊ���ð�ť����¼�������
                    public void actionPerformed(ActionEvent e) {
                        do_button_actionPerformed(e);
                    }
                });
        button.setBounds(161, 128, 90, 30);
        panel.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        // ��ȡ��ѡ��ĵ�ѡ��ť���ı�
        String command = bg.getSelection().getActionCommand();
        font = new Font(command, Font.BOLD, 18);// �������������
        titledBorder.setTitleFont(font);// Ϊ�߿������������
        panel.setBorder(titledBorder);// �������ı߿����
        panel.repaint();// ����������
    }
}
