import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.lzw.widget.CalendarPanel;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import javax.swing.UIManager;
import java.awt.Color;

public class CalendarFrame extends JFrame {
    
    private JPanel contentPane;
    private JLabel label;
    private String InfoStr;
    private CalendarPanel calendarPanel;
    
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
                    CalendarFrame frame = new CalendarFrame();
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
    public CalendarFrame() {
        setTitle("�����ؼ�");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 367, 222);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(102, 204, 204));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);// ʹ�þ��Զ�λ����
        calendarPanel = new CalendarPanel();// ���������ؼ�
        calendarPanel.addDateChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                do_calendarPanel_propertyChange(evt);// �����¼�������
            }
        });
        calendarPanel.setBounds(6, 6, 162, 170);
        contentPane.add(calendarPanel);
        // �����ַ���ģ��
        InfoStr = "<html>��ѡ��������ǣ�<br><font size=6 color=red>%1s</font></html>";
        // ���ñ�ǩ�ؼ���ʾ����
        label = new JLabel(String.format(InfoStr, calendarPanel.getDate()));
        label.setBounds(180, 6, 162, 170);
        contentPane.add(label);
    }
    
    protected void do_calendarPanel_propertyChange(PropertyChangeEvent evt) {
        // ͨ���¼����±�ǩ�ؼ�������
        label.setText(String.format(InfoStr, calendarPanel.getDate()));
    }
}
