import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AutoImageSizeByForm extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AutoImageSizeByForm frame = new AutoImageSizeByForm();
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
    public AutoImageSizeByForm() {
        setTitle("ʹ����ͼƬ�Զ���Ӧ����Ĵ�С");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);// ���ô���λ��
        contentPane = new JPanel();// �����������
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        // �����Զ��屳�����
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setImage(getToolkit().getImage(
                getClass().getResource("Penguins.jpg")));// ���ñ�������ͼƬ
        contentPane.add(backgroundPanel, BorderLayout.CENTER);// ��ӱ�����嵽�������
    }
    
}
