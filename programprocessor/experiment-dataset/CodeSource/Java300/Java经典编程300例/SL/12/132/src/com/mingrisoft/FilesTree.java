package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class FilesTree extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -2055459510450224221L;
    private JPanel contentPane;
    private JTree tree;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FilesTree frame = new FilesTree();
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
    public FilesTree() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        File[] disks = File.listRoots();// ������п��õ��ļ�ϵͳ��
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();// �����ڵ�
        for (File disk : disks) {// ��File�����е�Ԫ�����ӵ��ڵ���
            root.add(new DefaultMutableTreeNode(disk));
        }
        tree = new JTree(root);// ʹ�ýڵ㴴�����ؼ�
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                do_tree_valueChanged(e);
            }
        });
        scrollPane.setViewportView(tree);
    }

    protected void do_tree_valueChanged(TreeSelectionEvent e) {
        // ����û�ѡ��Ľڵ�
        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        File selectFile = (File) selectNode.getUserObject();// ��ýڵ�����File���Ͷ���
        if (selectFile.isDirectory()) {// ���File���Ͷ������ļ���
            File[] files = selectFile.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {// ���˵����������ļ�
                    if (pathname.isHidden()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });
            for (File file : files) {// ������������File���Ͷ������ӵ��û�ѡ��Ľڵ���
                selectNode.add(new DefaultMutableTreeNode(file));
            }
        } else {
            return;
        }
    }
}
