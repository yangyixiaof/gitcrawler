import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MouseCursorFrame extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MouseCursorFrame frame = new MouseCursorFrame();
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
    public MouseCursorFrame() {
        setTitle("���ô���������");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 318, 205);// ���ô���λ��
        contentPane = new JPanel();// �����������
        Toolkit toolkit = getToolkit();// ��ȡ���幤�߰�
        // ���������ͼƬ����
        Image image = toolkit.getImage(getClass().getResource("1.png"));
        // ͨ��ͼƬ����������
        Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0),
                "lzw");
        contentPane.setCursor(cursor);// �����������������
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �߿�
        contentPane.setLayout(new BorderLayout(0, 0));// ����
        setContentPane(contentPane);
    }
    
}
