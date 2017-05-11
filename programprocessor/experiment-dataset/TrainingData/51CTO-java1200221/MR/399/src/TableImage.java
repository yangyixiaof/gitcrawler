import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.UIManager;

public class TableImage extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TableImage frame = new TableImage();
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
    public TableImage() {
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
        ImageIcon[] icons = new ImageIcon[12];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = new ImageIcon(getClass().getResource(
                    "/res/" + (i + 1) + ".png"));
        }
        table.setModel(new DefaultTableModel(
                new Object[][]
                {// ���ñ������ģ��
                { icons[0], "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[0], "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[1], "�������ϵͳҵ��ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[2], "�������ϵͳͳ��ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[3], "�������ϵͳ��¼ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[4], "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[5], "�������ϵͳҵ��ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[6], "�������ϵͳͳ��ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[7], "�������ϵͳ��¼ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[8], "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[9], "�������ϵͳҵ��ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[10], "�������ϵͳͳ��ģ��", "��ĳ", "Ӧ�ó���" },
                        { icons[11], "�������ϵͳ����ģ��", "��ĳ", "Ӧ�ó���" } },
                new String[]
                { "ģ���ʶ", "��Ŀ����", "��Ŀ������", "��Ŀ����" }));
        table.getColumnModel().getColumn(1).setPreferredWidth(146);// �����п�
        TableColumn column = table.getColumnModel().getColumn(0);// ��ȡ����4�ж���
        table.setRowHeight(32);
        column.setCellRenderer(new TableCellRenderer() {// ���õ�4�е���Ⱦ��
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {
                        ImageIcon icon = (ImageIcon) value;
                        JLabel label = new JLabel(icon);// ����������
                        label.setBackground(table.getSelectionBackground());
                        if (isSelected)// ��ѡ��ı�ǩ����Ϊ��͸��
                            label.setOpaque(true);
                        return label;// �ѽ�������Ϊ��Ⱦ�ؼ�
                    }
                });
        scrollPane.setViewportView(table);// �ѱ����ӵ��������
    }
}
