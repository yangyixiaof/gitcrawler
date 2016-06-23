import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class CalculagraphFrame extends JFrame {
    
    private JPanel contentPane;
    private long sourTime;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculagraphFrame frame = new CalculagraphFrame();
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
    public CalculagraphFrame() {
        setTitle("���������ʾ��ʱ��");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 138);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel label = new JLabel(
                "<html><p>��������������ڱ�������ʾ��ʱ������ʶ��ǰ�����Ѿ������˶�������</p></html>");
        label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        contentPane.add(label, BorderLayout.CENTER);
    }
    
    protected void do_this_windowOpened(WindowEvent e) {// ������¼�������
        sourTime = System.currentTimeMillis();// ��¼����򿪵ĳ�ʼʱ��
        // ����Timer�ؼ�
        Timer timer = new Timer(1000, new ActionListener() {
            String title = getTitle();// ��ȡԭʼ�����ı�
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��������ʱ��
                long smTime = System.currentTimeMillis() - sourTime;
                // ��ʾ��ʱ��Ϣ��������
                setTitle(title + "�������Ѿ�������" + (smTime / 1000) + "�롿");
            }
        });
        timer.start();// ����Timer�ؼ�
    }
}
