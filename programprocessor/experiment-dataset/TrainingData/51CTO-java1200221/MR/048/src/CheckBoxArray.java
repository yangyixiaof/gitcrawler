import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class CheckBoxArray extends JFrame {
    
    private JPanel contentPane;
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
                    CheckBoxArray frame = new CheckBoxArray();
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
    public CheckBoxArray() {
        setTitle("\u590D\u9009\u6309\u94AE\u63A7\u4EF6\u6570\u7EC4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 409, 331);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel label = new JLabel(
                "\u4F60\u7684\u7231\u597D\u6709\u54EA\u4E9B\uFF1A");
        contentPane.add(label, BorderLayout.NORTH);
        contentPane.add(getPanel(), BorderLayout.CENTER);
    }
    
    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();// ����������
            panel.setLayout(new GridLayout(0, 4));// �������񲼾ֹ�����
            // �����ؼ��ı�����
            String[] labels = { "����", "����", "ħ��", "ƹ����", "����Ӱ", "ħ������", "CSս��",
                    "��ë��", "��Ӿ", "����", "��ɽ", "����", "д����", "��������", "����", "������",
                    "����ֽ", "쭳�", "���", "���̳�", "�齫", "����", "����������", "����", "����",
                    "����", "����", "����" };
            JCheckBox[] boxs = new JCheckBox[labels.length];// �����ؼ�����
            for (int i = 0; i < boxs.length; i++) {// �����ؼ�����
                boxs[i] = new JCheckBox(labels[i]);// ��ʼ�������еĸ�ѡ�����
                panel.add(boxs[i]);// ������Ԫ�أ���ÿ����ѡ����ӵ������
            }
        }
        return panel;
    }
}
