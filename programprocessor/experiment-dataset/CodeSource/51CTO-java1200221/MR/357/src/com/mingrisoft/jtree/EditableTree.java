package com.mingrisoft.jtree;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class EditableTree extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2734697597567545697L;
    private JPanel contentPane;
    private JTextField textField;
    private JTree tree;
    private DefaultMutableTreeNode root;
    
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
                    EditableTree frame = new EditableTree();
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
    public EditableTree() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u53EF\u4EE5\u67E5\u627E\u5B50\u8282\u70B9\u7684\u6811");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JLabel label = new JLabel("\u5173\u952E\u5B57\uFF1A");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(label);
        
        textField = new JTextField();
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(textField);
        textField.setColumns(10);
        
        JButton button = new JButton("\u67E5\u627E\u8282\u70B9");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(button);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        tree = new JTree();
        tree.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane.setViewportView(tree);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        root = new DefaultMutableTreeNode("���տƼ�����");
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
    
    @SuppressWarnings("unchecked")
    protected void do_button_actionPerformed(ActionEvent e) {
        String key = textField.getText();
        if ((key == null) || (key.isEmpty())) {
            JOptionPane.showMessageDialog(this, "������ؼ���", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode targetNode = null;
        Enumeration enums = root.breadthFirstEnumeration();
        while (enums.hasMoreElements()) {
            DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode) enums.nextElement();
            if (("" + tempNode).equals(key)) {
                targetNode = tempNode;
            }
        }
        if (targetNode == null) {
            JOptionPane.showMessageDialog(this, "δ�ҵ���Ҫ�Ľ��", "", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            TreeNode[] nodes = model.getPathToRoot(targetNode);
            TreePath path = new TreePath(nodes);
            tree.scrollPathToVisible(path);
            tree.setSelectionPath(path);
        }
    }
}
