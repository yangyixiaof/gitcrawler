import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class ArrayCreateTable extends JFrame {
    
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JTable table;
    
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
                    ArrayCreateTable frame = new ArrayCreateTable();
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
    public ArrayCreateTable() {
        setTitle("\u7528\u6570\u7EC4\u8BBE\u7F6EJTable\u8868\u683C\u7684\u5217\u540D\u4E0E\u5217\u5BBD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 557, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        contentPane.add(getScrollPane(), BorderLayout.CENTER);
    }
    
    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(getTable());
        }
        return scrollPane;
    }
    
    private JTable getTable() {
        if (table == null) {
            table = new JTable();
            // ������������
            String[] columns = { "����һ", "���ڶ�", "������", "������", "������", "������",
                    "������" };
            // �����п�����
            int[] columnWidth = { 10, 20, 30, 40, 50, 60, 70 };
            // �����������ģ��
            DefaultTableModel model = new DefaultTableModel(columns, 15);
            table.setModel(model);// ���ñ������ģ��
            TableColumnModel columnModel = table.getColumnModel();// ��ȡ��ģ��
            int count = columnModel.getColumnCount();// ��ȡ������
            for (int i = 0; i < count; i++) {// ������
                TableColumn column = columnModel.getColumn(i);// ��ȡ�ж���
                column.setPreferredWidth(columnWidth[i]);// ������Ԫ�������еĿ��
            }
        }
        return table;
    }
}
