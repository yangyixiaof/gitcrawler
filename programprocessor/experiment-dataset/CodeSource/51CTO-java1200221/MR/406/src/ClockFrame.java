import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.lzw.widget.JClock;
import javax.swing.JLabel;
import java.awt.Color;

public class ClockFrame extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClockFrame frame = new ClockFrame();
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
    public ClockFrame() {
        setTitle("ʯӢ�ӿؼ�");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 346, 187);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 204, 102));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JClock clock = new JClock();
        clock.setBounds(6, 6, 132, 132);
        contentPane.add(clock);
        JLabel label = new JLabel("<html>�Զ����ʱ�ӿؼ�<br>���ı�����͸���ģ�"
                + "������ʾ���ؼ������ε����ݡ�</html>");
        label.setBounds(161, 6, 163, 132);
        contentPane.add(label);
    }
}
