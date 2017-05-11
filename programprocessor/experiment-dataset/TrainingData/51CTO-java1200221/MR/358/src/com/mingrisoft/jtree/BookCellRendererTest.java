package com.mingrisoft.jtree;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.UIManager;

public class BookCellRendererTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7518855659670595077L;
    private JPanel contentPane;
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
                    BookCellRendererTest frame = new BookCellRendererTest();
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
    public BookCellRendererTest() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u81EA\u5B9A\u4E49\u6811\u8282\u70B9");
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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("�����ŵ���ͨϵ��");
        Book java = new Book();
        java.setTitle("��Java�����ŵ���ͨ����2�棩��");
        java.setPress("�廪��ѧ������");
        java.setPublicationDate("2010-07-01");
        java.setBooksCategory("�������ʦ���Ŵ���");
        java.setPrice(59.8);
        DefaultMutableTreeNode javaNode = new DefaultMutableTreeNode(java);
        root.add(javaNode);
        Book php = new Book();
        php.setTitle("��PHP�����ŵ���ͨ����2�棩��");
        php.setPress("�廪��ѧ������");
        php.setPublicationDate("2010-07-01");
        php.setBooksCategory("�������ʦ���Ŵ���");
        php.setPrice(69.8);
        DefaultMutableTreeNode phpNode = new DefaultMutableTreeNode(php);
        root.add(phpNode);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.setRoot(root);
        tree.setModel(model);
        tree.setCellRenderer(new BookCellRenderer());
    }
}
