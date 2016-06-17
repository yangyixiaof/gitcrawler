package com.mingrisoft.jtree;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChinaGeographyTree extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6221329006071145576L;
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
                    ChinaGeographyTree frame = new ChinaGeographyTree();
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
    public ChinaGeographyTree() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u4E2D\u56FD\u884C\u653F\u533A\u57DF\u6811");
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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("�й�");
        DefaultMutableTreeNode municipalities = new DefaultMutableTreeNode("ֱϽ��");
        municipalities.add(new DefaultMutableTreeNode("����"));
        municipalities.add(new DefaultMutableTreeNode("�Ϻ�"));
        municipalities.add(new DefaultMutableTreeNode("���"));
        municipalities.add(new DefaultMutableTreeNode("����"));
        DefaultMutableTreeNode province = new DefaultMutableTreeNode("ʡ");
        province.add(new DefaultMutableTreeNode("������"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("�Ĵ�"));
        province.add(new DefaultMutableTreeNode("�ຣ"));
        province.add(new DefaultMutableTreeNode("ɽ��"));
        province.add(new DefaultMutableTreeNode("�㶫"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("ɽ��"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("�ӱ�"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("�㽭"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("̨��"));
        province.add(new DefaultMutableTreeNode("����"));
        province.add(new DefaultMutableTreeNode("����"));
        DefaultMutableTreeNode ARegion = new DefaultMutableTreeNode("������");
        ARegion.add(new DefaultMutableTreeNode("���ɹ�������"));
        ARegion.add(new DefaultMutableTreeNode("���Ļ���������"));
        ARegion.add(new DefaultMutableTreeNode("�½�ά�����������"));
        ARegion.add(new DefaultMutableTreeNode("����������"));
        ARegion.add(new DefaultMutableTreeNode("����׳��������"));
        DefaultMutableTreeNode SARegion = new DefaultMutableTreeNode("�ر�������");
        SARegion.add(new DefaultMutableTreeNode("���"));
        SARegion.add(new DefaultMutableTreeNode("����"));
        root.add(municipalities);
        root.add(province);
        root.add(ARegion);
        root.add(SARegion);
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);
    }
}
