import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JWindow;

import com.lzw.panel.BGPanel;

public class InfoWindow extends JWindow {
    
    /**
     * Create the frame.
     */
    public InfoWindow() {
        addMouseListener(new MouseAdapter() {// �������¼�������
            @Override
            public void mousePressed(MouseEvent e) {
                do_this_mousePressed(e);// ��������¼�������
            }
        });
        setBounds(100, 100, 359, 228);// ���ô����С
        BGPanel panel = new BGPanel();// �����������
        // ���ñ���ͼƬ
        panel.setImage(Toolkit.getDefaultToolkit().getImage(
                InfoWindow.class.getResource("/com/lzw/panel/back.jpg")));
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    protected void do_this_mousePressed(MouseEvent e) {// ����¼�������
        dispose();// ��굥�����������������
    }
}
