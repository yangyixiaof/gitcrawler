package com.mingrisoft.jtable;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Font;

public class NodeIcon extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -9048535408788781352L;
    private JPanel contentPane;
    private JTree tree;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        UIManager.put("Tree.openIcon", new ImageIcon("src/image/open.png"));
        UIManager.put("Tree.closedIcon", new ImageIcon("src/image/closed.png"));
        UIManager.put("Tree.leafIcon", new ImageIcon("src/image/leaf.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NodeIcon frame = new NodeIcon();
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
    public NodeIcon() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u4FEE\u6539\u6811\u8282\u70B9\u7684\u56FE\u6807");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        tree = new JTree();
        tree.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane.setViewportView(tree);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("���տƼ�����");
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("�����ŵ���ͨϵ��");
        parent1.add(new DefaultMutableTreeNode("��Java�����ŵ���ͨ����2�棩��"));
        parent1.add(new DefaultMutableTreeNode("��PHP�����ŵ���ͨ����2�棩��"));
        parent1.add(new DefaultMutableTreeNode("��Visual Basic�����ŵ���ͨ����2�棩��"));
        parent1.add(new DefaultMutableTreeNode("��Visual C++�����ŵ���ͨ����2�棩��"));
        root.add(parent1);
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("��̴ʵ�ϵ��");
        parent2.add(new DefaultMutableTreeNode("��Java��̴ʵ䡷"));
        parent2.add(new DefaultMutableTreeNode("��PHP��̴ʵ䡷"));
        parent2.add(new DefaultMutableTreeNode("��Visual Basic��̴ʵ䡷"));
        parent2.add(new DefaultMutableTreeNode("��Visual C++��̴ʵ䡷"));
        root.add(parent2);
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);
    }
}
