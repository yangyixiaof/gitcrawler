import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QQFrame extends JFrame {
    
    private JPanel contentPane;
    private boolean collection;
    private boolean over = false;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QQFrame frame = new QQFrame();
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
    public QQFrame() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                do_this_mouseEntered(e);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                do_this_mouseExited(e);
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                do_this_componentMoved(e);
            }
        });
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 412, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel label = new JLabel("<html><body align=center>����һ����QQ���ش���"
                + "<br><font color=green size=6>���ҷŵ����嶥�ˣ�"
                + "�һ��Զ�����</font></body></html>");
        contentPane.add(label, BorderLayout.CENTER);
    }
    
    protected void do_this_componentMoved(ComponentEvent e) {// �����ƶ��¼�������
        if (over)// �������ڴ����У��Ͳ����������ز���
            return;
        Point point = getLocation();// ��ȡ����λ��
        if (point.y < 10) {// ������忿����Ļ����
            collection = true;// ȷ�����ش����ʶ
            Dimension size = getSize();// ��ȡ�����С
            setLocation(point.x, -size.height + 5);// ���ش���
        } else {
            collection = false;// �������û�п�����Ļ������ȡ�����ر�ʶ
        }
    }
    
    protected void do_this_mouseEntered(MouseEvent e) {// �����봰����¼�������
        Point point = getLocation();// ��ȡ����λ��
        if (point.y > 0)// �������û�б����ز����κβ���
            return;
        setLocation(point.x, 8);// ���ô�����ʾ
        over = true;// ��ʶ����ڴ����ڲ�
        try {
            Thread.sleep(1000);// ������1����ʱ��������λ
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
    
    protected void do_this_mouseExited(MouseEvent e) {// ����뿪�¼�������
        if (over) {// �������ʶ�ڴ����ڲ�
            over = false;// ȡ�����λ�õı�ʶ
            do_this_componentMoved(null);// ���ش���
        }
    }
}
