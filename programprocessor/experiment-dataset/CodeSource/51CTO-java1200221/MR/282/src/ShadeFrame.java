import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.sun.awt.AWTUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

public class ShadeFrame extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShadeFrame frame = new ShadeFrame();
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
    public ShadeFrame() {
        setTitle("���뵭���Ĵ���");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
    
protected void do_this_windowActivated(WindowEvent e) {
    AWTUtilities.setWindowOpacity(this, 0f);// ��ʼ��������ȫ͸��
    ActionListener listener = new ActionListener() {
        float alpha = 0;// ����͸���ȿ��Ʊ���
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (alpha < 0.9) {// �����͸����û�дﵽ100%
                // �����ۼ�͸���ȿ��Ʊ���
                AWTUtilities
                        .setWindowOpacity(ShadeFrame.this, alpha += 0.1);
            } else {
                // ������Ʊ�����ӵ���ȫ��͸��
                AWTUtilities.setWindowOpacity(ShadeFrame.this, 1);
                Timer source = (Timer) e.getSource();
                source.stop();// ֹͣTimer�ؼ�
            }
        }
    };
    new Timer(50, listener).start();// ����Timer�ؼ�
}
}
