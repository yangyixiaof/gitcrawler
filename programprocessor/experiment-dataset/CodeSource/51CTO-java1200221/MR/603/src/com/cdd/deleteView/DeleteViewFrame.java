package com.cdd.deleteView;
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
public class DeleteViewFrame extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    private LocalTableModel model = new LocalTableModel();
    private DeleteView view = new DeleteView();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteViewFrame frame = new DeleteViewFrame();
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
    public DeleteViewFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel messageLabel = new JLabel("ɾ�����ݿ��е���ͼ");
        messageLabel.setFont(new Font("����Ҧ��", Font.PLAIN, 14));
        messageLabel.setBounds(152, 20, 132, 15);
        panel.add(messageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(67, 74, 296, 118);
        panel.add(scrollPane);
        
        table = new JTable(model);
        List list = view.executeView();
        for(int i = 0;i<list.size();i++){
            model.addRow(new Object[]{i+1,list.get(i)});
        }
        scrollPane.setViewportView(table);
        
        JButton deleteButton = new JButton("ɾ��");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_deleteButton_actionPerformed(arg0);
            }
        });
        deleteButton.setBounds(98, 214, 81, 23);
        panel.add(deleteButton);
        
        JButton closebutton = new JButton("�ر�");
        closebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_closebutton_actionPerformed(arg0);
            }
        });
        closebutton.setBounds(233, 214, 81, 23);
        panel.add(closebutton);
    }
    protected void do_deleteButton_actionPerformed(ActionEvent arg0) {
        int [] ids = table.getSelectedRows();           //����ѡ���е�����
        String values[] = new String[ids.length];     
        for(int i = 0;i<ids.length;i++){    //����ѡ���е�����
            values[i] = new String(table.getValueAt(ids[i], 1).toString());    //��ȡ�û�ѡ��ĳ��Ԫ�������            
            
        }
        view.executeUpdate(values);
        JOptionPane.showMessageDialog(getContentPane(), 
                "��ͼɾ���ɹ���", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
        repaint();
    }
    protected void do_closebutton_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
