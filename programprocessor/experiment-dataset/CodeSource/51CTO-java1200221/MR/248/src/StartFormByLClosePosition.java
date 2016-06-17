import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.prefs.Preferences;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StartFormByLClosePosition extends JFrame {
    
    private JPanel contentPane;
    private JLabel label;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StartFormByLClosePosition frame = new StartFormByLClosePosition();
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
    public StartFormByLClosePosition() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                do_this_componentMoved(e);
            }
        });
        setTitle("\u4ECE\u4E0A\u6B21\u5173\u95ED\u4F4D\u7F6E\u542F\u52A8\u7A97\u4F53");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                do_this_windowClosing(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 346, 237);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label, BorderLayout.CENTER);
    }
    
    protected void do_this_windowOpened(WindowEvent e) {
        Preferences root = Preferences.userRoot();// ��ȡ�û���ѡ��
        int x = root.getInt("locationX", 100);// ��ȡ����X����
        int y = root.getInt("locationY", 100);// ��ȡ����Y����
        setLocation(x, y);// �ָ���������
    }
    
    protected void do_this_windowClosing(WindowEvent e) {
        Preferences root = Preferences.userRoot();// ��ȡ�û���ѡ��
        Point location = getLocation();// ��ȡ����λ��
        root.putInt("locationX", location.x);// ���洰��X����
        root.putInt("locationY", location.y);// ���洰��Y����
    }
    
    protected void do_this_componentMoved(ComponentEvent e) {
        Point location = getLocation();// ��ȡ��������
        int x = location.x;
        int y = location.y;
        // �Ѵ��嵱ǰ������ʾ�ڱ�ǩ�ؼ���
        label.setText("���嵱ǰ���꣺X = " + x + "        Y = " + y);
    }
}
