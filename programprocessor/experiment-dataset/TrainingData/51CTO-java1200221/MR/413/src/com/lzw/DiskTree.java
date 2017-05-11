package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class DiskTree extends JFrame {
    
    /**
     * ��ʵ�����ؼ���Ԫ����Ⱦ��
     * 
     * @author ����ξ
     */
    private final class FileRenderer implements TreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            // ת��valueΪ�ڵ����
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object userObject = node.getUserObject();
            if (!(userObject instanceof File)) {
                return new JLabel(userObject.toString());
            }
            File folder = (File) userObject;
            FileSystemView view = FileSystemView.getFileSystemView();
            Icon icon = view.getSystemIcon(folder);
            JLabel label = new JLabel("" + folder, icon, JLabel.LEADING);
            label.setBackground(Color.CYAN);
            if (selected) {
                label.setOpaque(true);
            } else {
                label.setOpaque(false);
            }
            return label;
        }
    }
    
    private JPanel contentPane;
    private JTree tree;
    private DefaultMutableTreeNode rootNode;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DiskTree frame = new DiskTree();
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
    public DiskTree() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        tree = new JTree();
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                do_tree_valueChanged(e);
            }
        });
        tree.setCellRenderer(new FileRenderer());
        scrollPane.setViewportView(tree);
        rootNode = new DefaultMutableTreeNode("�ҵĵ���");
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        tree.setModel(model);
    }
    
    /**
     * ���弤��ʱ���õķ���
     * 
     * @param e
     */
    protected void do_this_windowActivated(WindowEvent e) {
        File[] disks = File.listRoots();// ��ȡ�����б�
        for (File file : disks) {// �����б�
            // ʹ���ļ����󴴽����ڵ�
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
            rootNode.add(node);// ��ӽڵ㵽���ؼ��ĸ��ڵ�
        }
        tree.expandPath(new TreePath(rootNode));// չ�����ڵ�
    }
    
    public JTree getTree() {
        return tree;
    }
    
    /**
     * �ı�ڵ�ѡ��ʱ��ִ�еķ���
     * 
     * @param e
     */
    protected void do_tree_valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();// ��ȡ��ѡ��·��
        // ��ȡѡ��·���еĽڵ�
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
                .getLastPathComponent();
        // ��ȡ�ڵ��е��û�����
        Object userObject = node.getUserObject();
        if (!(userObject instanceof File)) {
            return;
        }
        File folder = (File) userObject;// ���û�����ת��Ϊ�ļ�����
        if (!folder.isDirectory())// ���˷��ļ��е�ѡ�����
            return;
        File[] files = folder.listFiles();// ��ȡ�ļ����е��ļ��б�
        for (File file : files) {// �����ļ��б�����
            // ʹ���ļ����û����󴴽��ڵ�
            node.add(new DefaultMutableTreeNode(file));
        }
    }
}
