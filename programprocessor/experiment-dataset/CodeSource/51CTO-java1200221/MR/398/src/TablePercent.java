import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.UIManager;

public class TablePercent extends JFrame {
    
    private JPanel contentPane;
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
                    TablePercent frame = new TablePercent();
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
    public TablePercent() {
        setTitle("�ڱ������ʾ�������Ȱٷֱ�");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 470, 300);// ���ô���λ�����С
        contentPane = new JPanel();// �����������
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JScrollPane scrollPane = new JScrollPane();// �����������
        contentPane.add(scrollPane, BorderLayout.CENTER);// ��ӹ�����嵽����
        table = new JTable();// �������ؼ�
        table.setModel(new DefaultTableModel(
                new Object[][] {// ���ñ������ģ��
                { "�������ϵͳ��¼ģ��", "��ĳ", "Ӧ�ó���", new Integer(93) },
                        { "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���", new Integer(63) },
                        { "�������ϵͳҵ��ģ��", "��ĳ", "Ӧ�ó���", new Integer(73) },
                        { "�������ϵͳͳ��ģ��", "��ĳ", "Ӧ�ó���", new Integer(43) },
                        { "�������ϵͳ��¼ģ��", "��ĳ", "Ӧ�ó���", new Integer(93) },
                        { "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���", new Integer(63) },
                        { "�������ϵͳҵ��ģ��", "��ĳ", "Ӧ�ó���", new Integer(73) },
                        { "�������ϵͳͳ��ģ��", "��ĳ", "Ӧ�ó���", new Integer(43) },
                        { "�������ϵͳ��¼ģ��", "��ĳ", "Ӧ�ó���", new Integer(93) },
                        { "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���", new Integer(63) },
                        { "�������ϵͳҵ��ģ��", "��ĳ", "Ӧ�ó���", new Integer(73) },
                        { "�������ϵͳͳ��ģ��", "��ĳ", "Ӧ�ó���", new Integer(43) },
                        { "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���", new Integer(53) } },
                new String[] { "��Ŀ����", "��Ŀ������", "��Ŀ����", "��������" }));
        table.getColumnModel().getColumn(0).setPreferredWidth(146);// �����п�
        TableColumn column = table.getColumnModel().getColumn(3);// ��ȡ����4�ж���
        column.setCellRenderer(new TableCellRenderer() {// ���õ�4�е���Ⱦ��
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {
                        if (value instanceof Integer) {// ����������Ⱦ�ؼ�
                            JProgressBar bar = new JProgressBar();// ����������
                            Integer percent = (Integer) value;// �ѵ�ǰֵת��Ϊ����
                            bar.setValue(percent);// ���ý�������ֵ
                            bar.setStringPainted(true);// ��ʾ�������ı�
                            return bar;// �ѽ�������Ϊ��Ⱦ�ؼ�
                        } else {
                            return null;
                        }
                    }
                });
        scrollPane.setViewportView(table);// �ѱ����ӵ��������
    }
    
}
