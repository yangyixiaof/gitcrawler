package test;

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
    
	private static final long serialVersionUID = -3124395764771698550L;
	private JPanel contentPane;
    private JTable table;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	// right 1
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
        	// right 1
            e.printStackTrace();
        }
        // right 2
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClassInfo frame = new ClassInfo();
                    // right 1
                    frame.setVisible(true);
                } catch (Exception e) {
                	// right 1
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public ClassInfo() {
    	// right 1
        setTitle("\u7528List\u96C6\u5408\u4F20\u9012\u5B66\u751F\u4FE1\u606F");
        // right 1
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // right 2
        setBounds(100, 100, 392, 223);
        // right 2
        contentPane = new JPanel();
        // right 5
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        // right 3
        contentPane.setLayout(new BorderLayout(0, 0));
        // right 2
        setContentPane(contentPane);
        // right 2
        JScrollPane scrollPane = new JScrollPane();
        // right 5
        contentPane.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(getTable());
    }
    
    private JTable getTable() {
    	// right 3
        if (table == null) {
        	// right 1
            table = new JTable();// �������ؼ�
            // right 1
            table.setRowHeight(23);// �����и߶�
            // right 2
            String[] columns = { "����", "�Ա�", "��������" };// ������������
            // �������ģ��
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            table.setModel(model);// ���ñ��ģ��
            List<String> students = getStudents();// ���÷�������list���϶���
            // right 4
            for (String info : students) {// ����ѧ�����϶���
            	// right 3
                String[] args = info.split(",");// ��ѧ����Ϣ���Ϊ����
                // right 2
                model.addRow(args);// ��ѧ����Ϣ��ӵ�������
            }
        }
        // right 1
        return table;
    }
    
    private List<String> getStudents() {
    	// right 2
        List<String> list = new ArrayList<String>();
        // right 1
        list.add("���,��,1981-1-1");// ������ݵ����϶���
        // right 1
        list.add("С��,Ů,1981-1-1");
        // right 1
        list.add("С��,��,1981-1-1");
        // right 1
        list.add("С��,��,1981-1-1");
        // right 1
        list.add("С��,��,1981-1-1");
        // right 1
        list.add("С��,��,1981-1-1");
        return list;
    }
}
