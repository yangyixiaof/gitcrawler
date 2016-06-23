import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.sun.awt.AWTUtilities;

public class ElipseFrame extends JFrame {
    
    private BackgroundPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ElipseFrame frame = new ElipseFrame();
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
    public ElipseFrame() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                do_this_mouseClicked(e);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
        });
        setUndecorated(true);// ȥ����������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new BackgroundPanel();
        contentPane
                .setImage(new ImageIcon(getClass().getResource("photo1.jpg"))
                        .getImage());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
    
    protected void do_this_windowOpened(WindowEvent e) {
        // ������Բ����
        Ellipse2D.Float ellipse = new Ellipse2D.Float(0f, 10f, 400f, 130f);
        AWTUtilities.setWindowShape(this, ellipse);// ���ô�����Բ��״
    }
    
    protected void do_this_mouseClicked(MouseEvent e) {
        dispose();// ���ٴ���
    }
}
