import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class ExampleFrame extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    
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
                    ExampleFrame frame = new ExampleFrame();
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
    public ExampleFrame() {
        // Ϊ������Ӵ��¼�������
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);// ���ô�����¼�������
            }
        });
        setTitle("��̬���ر������");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);// ���ô����С
        contentPane = new JPanel();// �����������
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JScrollPane scrollPane = new JScrollPane();// �����������
        contentPane.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();// �������ؼ�
        model = new DefaultTableModel(new Object[][] {}, new String[]
            { "ѧ��", "��������", "�������" });// ����Ĭ�ϵı������ģ��
        table.setModel(model);// ���ñ������ģ��
        scrollPane.setViewportView(table);// �ѱ����ӵ����������ͼ
    }
    
    protected void do_this_windowOpened(WindowEvent e) {
        // ����Timer�ؼ�
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();// �������������
                Integer[] values = new Integer[]
                    // ��������������Ϊ���������
                    { random.nextInt(100), random.nextInt(100),
                            random.nextInt(100) };
                model.addRow(values);// Ϊ�������ģ�����һ������
            }
        });
        timer.start();// ����Timer�ؼ�
    }
}
