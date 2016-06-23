import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class DeleteFrame extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    private LocalTableModel model = new LocalTableModel();
    private DeleteUtil util = new DeleteUtil();
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteFrame frame = new DeleteFrame();
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
    public DeleteFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 329);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 291);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel messageLabel = new JLabel("ɾ������ʱ������ʾ��Ϣ");
        messageLabel.setFont(new Font("��������", Font.PLAIN, 16));
        messageLabel.setBounds(123, 26, 213, 34);
        panel.add(messageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 70, 364, 173);
        panel.add(scrollPane);
        
        table = new JTable(model);
        List list = util.selectStu();
        for (int i = 0; i < list.size(); i++) {
            Stu stu = (Stu) list.get(i);
            model.addRow(new Object[] { stu.getId(), stu.getName(),
                    stu.getSex(), stu.getSpecialty(), stu.getGrade() });
        }
        scrollPane.setViewportView(table);
        
        JButton deleteButton = new JButton("ɾ��");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_deleteButton_actionPerformed(arg0);
            }
        });
        deleteButton.setBounds(108, 253, 73, 23);
        panel.add(deleteButton);
        
        JButton closeButton = new JButton("�ر�");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_1_actionPerformed(arg0);
            }
        });
        closeButton.setBounds(231, 253, 73, 23);
        panel.add(closeButton);
    }
    
    // ɾ����ť�ĵ����¼�
    protected void do_deleteButton_actionPerformed(ActionEvent arg0) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int n = JOptionPane.showConfirmDialog(getContentPane(), "ȷ����ȷ��",
                    "ȷ�϶Ի���", JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == JOptionPane.YES_OPTION) { // ����û�ȷ����Ϣ
                util.deleteStu(row+1);
            }           
           validate();
        }
        
    }
    
    // �رհ�ť�ĵ����¼�
    protected void do_button_1_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
