package com.mingrisoft.jtree;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class SelectedEventTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7912488705640918487L;
    private JPanel contentPane;
    private JTree tree;
    private JTextArea textArea;
    
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
                    SelectedEventTest frame = new SelectedEventTest();
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
    public SelectedEventTest() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u76D1\u542C\u8282\u70B9\u9009\u62E9\u4E8B\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1);
        
        tree = new JTree();
        tree.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                do_tree_valueChanged(e);
            }
        });
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        scrollPane1.setViewportView(tree);
        
        JScrollPane scrollPane2 = new JScrollPane();
        panel.add(scrollPane2);
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane2.setViewportView(textArea);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("���տƼ�����");
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("�����ŵ���ͨϵ��");
        root.add(parent1);
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("��̴ʵ�ϵ��");
        root.add(parent2);
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);
    }
    
    protected void do_tree_valueChanged(TreeSelectionEvent e) {
        TreePath path = tree.getSelectionPath();
        if(path==null) {
            return;
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        String text1 = "��Java�����ŵ���ͨ����2�棩��\n��PHP�����ŵ���ͨ����2�棩��\n��Visual Basic�����ŵ���ͨ����2�棩��\n��Visual C++�����ŵ���ͨ����2�棩��";
        String text2 = "��Java��̴ʵ䡷\n��PHP��̴ʵ䡷\n��Visual Basic��̴ʵ䡷\n��Visual C++��̴ʵ䡷";
        if (node.toString().equals("�����ŵ���ͨϵ��")) {
            textArea.setText(text1);
        } else {
            textArea.setText(text2);
        }
    }
}
