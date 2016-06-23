import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoDemoFrame extends JFrame {
    
    private JPanel contentPane;
    private InfoWindow window = new InfoWindow();
    private Timer timer;
    private Point location;
    private Dimension screenSize;
    private Dimension windowSize;;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InfoDemoFrame frame = new InfoDemoFrame();
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
    public InfoDemoFrame() {
        setTitle("���½ǵ�����Ϣ����");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 337, 190);// �����С
        contentPane = new JPanel();// �����������
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);// ȡ�����ֹ�����
        JButton button = new JButton("��ȡ��ʱ��Ϣ");// ������ť
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);// ���ð�ť�¼�������
            }
        });
        button.setBounds(97, 59, 122, 30);
        contentPane.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        // ����Timer�ؼ�
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location.y -= 1;// ������Ϣ���崹ֱ����
                // ����Ϣ������ʾ����û�дﵽ����λ��֮ǰ�����ƶ�����
                if (window.isShowing()
                        && location.y > screenSize.height - windowSize.height)
                    window.setLocation(location);
                else {// ����δ��ʾ�򳬳��ƶ���Χ��ֹͣ
                    Timer source = (Timer) e.getSource();
                    source.stop();
                }
            }
        });
        screenSize = getToolkit().getScreenSize();// ��ȡ��Ļ��С
        window.setVisible(true);// ��ʾ��Ϣ����
        window.setAlwaysOnTop(true);// ����Ϣ�����ö�
        windowSize = window.getSize();// ��ȡ��Ϣ�����С
        location = new Point();// ����λ�ö���
        location.x = screenSize.width - windowSize.width;// ��ʼ������λ��
        location.y = screenSize.height;
        timer.start();// ����Timer�ؼ�
    }
}
