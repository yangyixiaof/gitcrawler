package com.cdd.openWord;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;

import javax.swing.tree.*;
import java.awt.Rectangle;

public class OpenWord extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null; // ���ô������
    private JPanel managerjPanel = null;
    private JScrollPane fileScrollPane = null;
    private JTree jtree = null;
    private String strFile = null;
    private JButton open = null;      
    JList list = null;
    /**
     * This method initializes managerjPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getManagerjPanel() {
        if (managerjPanel == null) {
            try {
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            managerjPanel = new JPanel();
            managerjPanel.setLayout(new BorderLayout());
            JTree jtree = getTree();
            JScrollPane sp = new JScrollPane(jtree);
            sp.setBorder(BorderFactory.createEtchedBorder(Color.white,
                    new Color(148, 145, 140)));
            managerjPanel.add(sp);
        }
        managerjPanel.setBounds(0, 0, 300, this.getHeight());
        return managerjPanel;
    }
    
    /**
     * This method initializes fileScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getFileScrollPane() {
        if (fileScrollPane == null) {
            fileScrollPane = new JScrollPane();
            fileScrollPane.setBounds(new Rectangle(301, 0, 345, this
                    .getHeight()-100));
            open = new JButton("��");
            open.setBounds(391, this.getHeight()-80, 60,30);
            open.setEnabled(false);

open.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent arg0) {
        String path = list.getSelectedValue().toString();       //��ȡ�û�ѡ����б�������
        FileHeald fileHeald = new FileHeald();                  //����FileHeald����
        fileHeald.openDocument(path);           //���ô��ļ�����
    }
});
            
            jContentPane.add(open);
            
        }
        return fileScrollPane;
    }    
      
    /**
     * This method initializes fileList
     * 
     * @return javax.swing.JList
     */    
    private JTree getTree() {
        File[] root = (new FileSystem()).getRoots();
        MyNode filenod = new MyNode(root[0]);
        filenod.explore();
        jtree = new JTree(new DefaultTreeModel(filenod));
        jtree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION); // ��������ѡ��ģ��Ϊһ��ֻѡ��һ��·��
        JScrollPane sp = new JScrollPane(jtree);
        sp.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(
                148, 145, 140)));
        jtree.setShowsRootHandles(true); // �����������߲���ʾ���
        jtree.addTreeExpansionListener(new TreeExpansionListener() {
            public void treeCollapsed(TreeExpansionEvent e) { // ������ĳһ��۵�ʱ����
            }
            
            public void treeExpanded(TreeExpansionEvent e) { // ������ĳһ�չ��ʱ����
                TreePath treepath = e.getPath(); // ��ʾ�ڵ��·��
                MyNode node = (MyNode) treepath.getLastPathComponent(); // ��ȡ·�������һ�����
                if (!node.isExplored()) {
                    DefaultTreeModel model = ((DefaultTreeModel) jtree
                            .getModel());
                    node.explore();
                    model.nodeStructureChanged(node); // ���ĸ��ڵ���õķ���
                }
            }
        });
        jtree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JTree tree = (JTree) e.getSource();
                int row = tree.getRowForLocation(e.getX(), e.getY());
                if (row == -1) {
                    return;
                }
                TreePath path = tree.getPathForRow(row); // ��ȡָ����·��
                if (path == null) {
                    return;
                }
                MyNode node = (MyNode) path.getLastPathComponent();
                if (node == null) {
                    return;
                }
                String filepath = node.getString();
                String newPath = filepath.replace("\\", "//");
                FileHeald fileHeald = new FileHeald();
                Vector<String> vector = new Vector<String>();       
                List filelist = fileHeald.getList(newPath);
                for(int i = 0;i<filelist.size();i++){
                    String strName = filelist.get(i).toString();     
                    System.out.println("StrName "+strName);
                    if(strName.endsWith(".doc")){
                        vector.add(strName);
                    }
                }                      
                list = new JList(vector);
                open.setEnabled(true);
                fileScrollPane.setViewportView(list);              
            }
        });
        return jtree;
    }
    
    class MyNode extends DefaultMutableTreeNode {
        private boolean explored = false;        
        public MyNode(File file) {
            setUserObject(file); // ���˽ڵ���û���������Ϊfile
        }        
        public boolean getAllowChildren() { // �������˽ڵ�ӵ���ӽڵ㣬�򷵻� true��
            return isDirectory();
        }        
        public boolean isLeaf() { // ����˽ڵ�û���ӽڵ㣬�򷵻� true��
            return !isDirectory();
        }        
        public File getFile() { // ��ȡ�ýڵ���û�����
            return (File) getUserObject();
        }        
        public boolean isExplored() {
            return explored;
        }        
        public boolean isDirectory() {
            File file = getFile();
            return file.isDirectory();
        }        
        public String toString() {
            File file = getFile();
            String filename = file.toString();
            int index = filename.lastIndexOf("\\");
            return (index != -1 && index != filename.length() - 1) ? filename
                    .substring(index + 1) : filename;
        }        
        public String getString() {
            File file = getFile();
            String filename = file.getAbsolutePath();
            return filename;
        }        
        public void explore() {
            if (!isDirectory()) {
                return;
            }
            if (!isExplored()) {
                File file = getFile();
                File[] children = file.listFiles();
                for (int i = 0; i < children.length; ++i) {
                    if (children[i].isDirectory()) {
                        add(new MyNode(children[i]));
                    }
                }
                explored = true;
            }
        }
    }
    
    // �����ڲ���̳�FileSystemView�ļ�ϵͳ����
    class FileSystem extends FileSystemView {
        public File createNewFolder(File containingDir) throws IOException {
            return null;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              
                OpenWord thisClass = new OpenWord();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }
    
    /**
     * This is the default constructor
     */
    public OpenWord() {
        super();
        initialize();
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setBounds(100, 100, 660, 484);
        this.setContentPane(getJContentPane());
        this.setTitle("��Word�ĵ�");   
              
  }    
    // �Զ��巽��ָ����굥���¼�
    private void checktree(MouseEvent e) {
        
        if (e.isPopupTrigger()) {
            
        }
    }
    
    public String getStrFile() {
        return strFile;
    }
    
    public void setStrFile(String strFile) {
        this.strFile = strFile;
    }
    
    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);    
  
            jContentPane.add(getManagerjPanel(), null);
            jContentPane.add(getFileScrollPane(), null);
        }
        return jContentPane;
    }
   
    class FileNode extends DefaultMutableTreeNode {
        private boolean explored_ = false;
        
        public FileNode(File file) {
            setUserObject(file);
        }
        
        public boolean getAllowChildren() {
            return isDirectory();
        }
        
        public boolean isLeaf() {
            return !isDirectory();
        }
        
        public File getFile() {
            return (File) getUserObject();
        }
        
        public boolean isExplored() {
            return explored_;
        }
        
        public boolean isDirectory() {
            File file = getFile();
            return file.isDirectory();
        }
        
        public String toString() {
            File file = getFile();
            String filename = file.toString();
            int index = filename.lastIndexOf("\\");
            return (index != -1 && index != filename.length() - 1) ? filename
                    .substring(index + 1) : filename;
        }
        
        public String getString() {
            File file = getFile();
            String filename = file.getAbsolutePath();
            return filename;
        }
        
        public void explore() {
            if (!isDirectory()) {
                return;
            }
            if (!isExplored()) {
                File file = getFile();
                File[] children = file.listFiles();
                for (int i = 0; i < children.length; ++i) {
                    if (children[i].isDirectory()) {
                        add(new FileNode(children[i]));
                    }
                }
                explored_ = true;
            }
        }
    }
}
