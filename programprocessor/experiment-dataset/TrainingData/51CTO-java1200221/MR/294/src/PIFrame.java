import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PIFrame extends JDialog {
    
    private JPanel contentPane;
    private static PIFrame[] frames = new PIFrame[12];
    private static Point[] points = new Point[frames.length];
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < frames.length; i++) {// ��ʼ����������
                        frames[i] = new PIFrame();// ��ʼ����������Ԫ��
                        frames[i].setVisible(true);// ��ʾ����
                        points[i] = frames[i].getLocation();// ��ʼ������λ������
                    }
                    Timer timer = new Timer(50, new ActionListener() {
                        int r = 100;// �ƶ��뾶
                        int angle = 0;// �Ƕȱ���
                        double optionNum = Math.PI / 180;// �������ȽǶ�ת����λ
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < frames.length; i++) {
                                int x = (int) (r * Math.cos((angle + i * 10)
                                        % 360 * optionNum));// ���崰��X����
                                int y = (int) (r * Math
                                        .sin((angle + i * 10 % 360) * optionNum));// ���崰��Y����
                                angle = (angle + 1) % 360;// �ۼ��˶��ĽǶ�
                                frames[i].setLocation(points[i].x + x,
                                        points[i].y + y);// �ƶ�����
                            }
                        }
                    });
                    timer.start();// ����Timer�ؼ�
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public PIFrame() {
        setAlwaysOnTop(true);
        setBounds(500, 400, 356, 268);
        contentPane = new JPanel();
        contentPane.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                do_contentPane_mousePressed(e);
            }
        });
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
    
    protected void do_contentPane_mousePressed(MouseEvent e) {
        System.exit(0);
    }
}
