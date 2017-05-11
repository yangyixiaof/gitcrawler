import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZoomFrameContent extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ZoomFrameContent frame = new ZoomFrameContent();
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
    public ZoomFrameContent() {
        setTitle("���嶶��");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton button = new JButton("���嶶��");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(168, 107, 90, 30);
        contentPane.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        int num = 15;// ��������
        Point point = getLocation();// ����λ��
        for (int i = 30; i > 0; i--) {// ������С
            for (int j = num; j > 0; j--) {
                point.y += i;
                setLocation(point);// ���������ƶ�
                point.x += i;
                setLocation(point);// ���������ƶ�
                point.y -= i;
                setLocation(point);// ���������ƶ�
                point.x -= i;
                setLocation(point);// ���������ƶ�
            }
        }
    }
}
