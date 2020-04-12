import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;

public class ClassInfo extends JFrame {
    
    private JPanel content_pane;
    private JTable c_table;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClassInfo ci_frame = new ClassInfo();
                    ci_frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public ClassInfo() {
        setTitle("\u7528List\u96C6\u5408\u4F20\u9012\u5B66\u751F\u4FE1\u606F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 392, 223);
        content_pane = new JPanel();
        content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        content_pane.setLayout(new BorderLayout(0, 0));
        setContentPane(content_pane);
        
        JScrollPane scroll_pane = new JScrollPane();
        content_pane.add(scroll_pane, BorderLayout.CENTER);
        scroll_pane.setViewportView(getTable());
    }
    
    private JTable getTable() {
        if (c_table == null) {
            c_table = new JTable();// �������ؼ�
            c_table.setRowHeight(23);// �����и߶�
            String[] columns = { "����", "�Ա�", "��������" };// ������������
            // �������ģ��
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            c_table.setModel(model);// ���ñ��ģ��
            List<String> students = getStudents();// ���÷�������list���϶���
            for (String info : students) {// ����ѧ�����϶���
                String[] args = info.split(",");// ��ѧ����Ϣ���Ϊ����
                model.addRow(args);// ��ѧ����Ϣ��ӵ�������
            }
        }
        return c_table;
    }
    
    private List<String> getStudents() {
        // ����List���϶���
        List<String> list = new ArrayList<String>();
        list.add("���,��,1981-1-1");// ������ݵ����϶���
        list.add("С��,Ů,1981-1-1");
        list.add("С��,��,1981-1-1");
        list.add("С��,��,1981-1-1");
        list.add("С��,��,1981-1-1");
        list.add("С��,��,1981-1-1");
        return list;
    }
}
