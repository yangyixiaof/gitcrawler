import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class MaxChildForm extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MaxChildForm frame = new MaxChildForm();
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
    public MaxChildForm() {
        setTitle("\u4F7F\u5B50\u7A97\u4F53\u6700\u5927\u5316\u663E\u793A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 343);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        contentPane.add(desktopPane, BorderLayout.CENTER);
        
        button = new JButton("\u6700\u5927\u5316\u6253\u5F00");
        button.setBounds(359, 259, 89, 30);
        desktopPane.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
    }
    
    private JDesktopPane desktopPane = new JDesktopPane();
    private JButton button;
    
    protected void do_button_actionPerformed(ActionEvent e) {
        // �����ڲ��Ӵ���
        JInternalFrame jif = new JInternalFrame("�Ӵ���", true, true, true, true);
        jif.setSize(200, 200);// ���ô����С
        desktopPane.add(jif);// ����Ӵ��嵽�������
        jif.setVisible(true);// ��ʾ�Ӵ���
        try {
            jif.setMaximum(true);// �����ڲ��Ӵ������״̬
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
        desktopPane.setComponentZOrder(button, 0);// �Ѱ�ť�ö�
    }
}
