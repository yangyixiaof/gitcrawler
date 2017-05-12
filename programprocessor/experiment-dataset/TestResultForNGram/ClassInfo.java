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
    
    private JPanel cPane;
    private JTable table;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	// right 1
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClassInfo frame = new ClassInfo();
                    frame.setVisible(true);
                } catch (Exception e) {
                	// right 2
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
        
        cPane = new JPanel();
        cPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        cPane.setLayout(new BorderLayout(0, 0));
        setContentPane(cPane);
        JScrollPane scrollPane = new JScrollPane();
        cPane.add(scrollPane, BorderLayout.CENTER);
        // wrong because of not generalizing variables.
        scrollPane.setViewportView(getTable());
    }
    
    private JTable getTable() {
        if (table == null) {
            table = new JTable();
            table.setRowHeight(23);
            String[] columns = { "����", "�Ա�", "��������" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            table.setModel(model);
            List<String> students = getStudents();
            // wrong because of not generalizing.
            for (String info : students) {
                String[] args = info.split(",");
                model.addRow(args);
            }
        }
        // wrong because of not generalizing.
        return table;
    }
    
    private List<String> getStudents() {
        List<String> list = new ArrayList<String>();
        // wrong because of not generalizing.
        list.add("���,��,1981-1-1");
        list.add("С��,Ů,1981-1-1");
        list.add("С��,��,1981-1-1");
        list.add("С��,��,1981-1-1");
        list.add("С��,��,1981-1-1");
        list.add("С��,��,1981-1-1");
        return list;
    }
}
