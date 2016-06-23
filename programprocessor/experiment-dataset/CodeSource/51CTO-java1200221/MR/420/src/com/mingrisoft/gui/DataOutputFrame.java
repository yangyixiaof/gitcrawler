package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.mingrisoft.util.DBHelper;
import javax.swing.UIManager;

public class DataOutputFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7380411131151951458L;
    private JPanel contentPane;
    private JTable table;
    private JTree tree;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DataOutputFrame frame = new DataOutputFrame();
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
    public DataOutputFrame() {
        setTitle("\u63D0\u53D6\u6570\u636E\u5E93\u5185\u5BB9\u5230\u6587\u4EF6");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                do_this_windowActivated(arg0);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JScrollPane treeScrollPane = new JScrollPane();
        
        JPanel panel = new JPanel();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                gl_contentPane.createSequentialGroup().addContainerGap().addComponent(treeScrollPane, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE).addGap(28)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE).addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                gl_contentPane.createSequentialGroup().addContainerGap().addGroup(
                        gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(
                                gl_contentPane.createSequentialGroup().addComponent(panel, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE).addGap(5)).addComponent(treeScrollPane,
                                Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)).addContainerGap()));
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton button = new JButton("\u5BFC\u51FA\u6570\u636E");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        buttonPanel.add(button);
        
        JScrollPane tableScrollPane = new JScrollPane();
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        tableScrollPane.setViewportView(table);
        
        tree = new JTree();
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent arg0) {
                do_tree_valueChanged(arg0);
            }
        });
        treeScrollPane.setViewportView(tree);
        contentPane.setLayout(gl_contentPane);
    }
    
    private static DefaultTableModel makeTableModel(String tableName) {
        String sql = "select * from " + tableName;
        Object[] tableHeader = DBHelper.getColumnNames(sql);
        List<Object[]> tableBody = DBHelper.query(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(tableHeader);
        Iterator<Object[]> iterator = tableBody.iterator();
        while (iterator.hasNext()) {
            model.addRow(iterator.next());
        }
        return model;
    }
    
    protected void do_this_windowActivated(WindowEvent arg0) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("���ݿ��еı���");
        List<Object> tableNames = DBHelper.getTableNames();
        for (int i = 0; i < tableNames.size(); i++) {
            root.add(new DefaultMutableTreeNode(tableNames.get(i)));
        }
        tree.setModel(new DefaultTreeModel(root));
        table.setModel(makeTableModel((String) tableNames.get(0)));
    }
    
    protected void do_tree_valueChanged(TreeSelectionEvent arg0) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode == null || selectedNode.getChildCount() != 0) {
            return;
        }
        String tableName = selectedNode.toString();
        table.setModel(makeTableModel(tableName));
    }
    
    @SuppressWarnings("unchecked")
    protected void do_button_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("�ı��ļ�", "txt"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showSaveDialog(tree);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();// ����û�ѡ����ļ�
            FileWriter fileWriter = null;// ��trycatch�����洴��FileWriter���󣬷����ͷ���Դ
            try {
                fileWriter = new FileWriter(file);// �����û�ѡ����ļ�����FileWriter����
                // ����û�ѡ��ı�������
                Vector tableBody = ((DefaultTableModel) table.getModel()).getDataVector();
                // ����StringBuilder�������洢��������
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < tableBody.size(); i++) {// ��������������
                    Vector row = (Vector) tableBody.elementAt(i);
                    for (int j = 0; j < row.size(); j++) {// ����һ�е�������
                        builder.append(row.elementAt(j) + "\t");// ����֮�����Ʊ���ָ�
                    }
                    builder.append("\r\n");// ����֮���û��з��ָ�
                }
                fileWriter.write(builder.toString());// ������д�뵽�ļ�
                fileWriter.flush();// �������е�����д�뵽�ļ���
                JOptionPane.showMessageDialog(this, "���ݵ����ɹ�");// ��ʾ�û����ݵ����ɹ�
            } catch (IOException e) {// ����IO�쳣
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {// ���FileWriter����ǿվ��ͷ���ռ�õ���Դ
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
