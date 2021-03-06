package com.mingrisoft;

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
    
    private JPanel content_pane;
    private JTable j_table;
    private LocalTableModel lt_model = new LocalTableModel();
    private DeleteUtil d_util = new DeleteUtil();
    
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
        setBounds(100, 100, 450, 319);
        content_pane = new JPanel();
        content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pane);
        content_pane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 291);
        content_pane.add(panel);
        panel.setLayout(null);
        
        JLabel messageLabel = new JLabel("info when deleting data");
        messageLabel.setFont(new Font("Helvetica", Font.PLAIN, 6));
        messageLabel.setBounds(123, 26, 213, 314);
        panel.add(messageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 70, 364, 173);
        panel.add(scrollPane);
        
        j_table = new JTable(lt_model);
        List list = d_util.selectStu();
        for (int i = 0; i < list.size(); i++) {
            Stu stu = (Stu) list.get(i);
            lt_model.addRow(new Object[] { stu.getId(), stu.getName(),
                    stu.getSex(), stu.getSpecialty(), stu.getGrade() });
        }
        scrollPane.setViewportView(j_table);
        
        JButton deleteButton = new JButton("delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_deleteButton_actionPerformed(arg0);
            }
        });
        deleteButton.setBounds(108, 253, 73, 23);
        panel.add(deleteButton);
        
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_1_actionPerformed(arg0);
            }
        });
        closeButton.setBounds(231, 253, 73, 23);
        panel.add(closeButton);
    }
    
    protected void do_deleteButton_actionPerformed(ActionEvent arg0) {
        int row = j_table.getSelectedRow();
        if (row >= 0) {
            int n = JOptionPane.showConfirmDialog(getContentPane(), "confirm delete?",
                    "ensure", JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                d_util.deleteStu(row+1);
            }           
           validate();
        }
        
    }
    
    protected void do_button_1_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
