import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class SetFormBackColor extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetFormBackColor frame = new SetFormBackColor();
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
    public SetFormBackColor() {
        setTitle("���ô��屳����ɫΪ����ɫ");// ���ô��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 308, 238);// ���ô���λ��
        contentPane = new JPanel();// �����������
        // �����������ı���ɫ
        contentPane.setBackground(new Color(102, 204, 255));
        setContentPane(contentPane);// ���ô����������
    }
    
}
