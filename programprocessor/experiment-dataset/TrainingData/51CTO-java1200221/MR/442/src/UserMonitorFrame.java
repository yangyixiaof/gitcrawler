import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UserMonitorFrame extends JFrame implements Runnable {    
    private JPanel contentPane;
    private JTextField pathTextField;
    private JTextField saveTextField;    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserMonitorFrame frame = new UserMonitorFrame();
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
    public UserMonitorFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 481, 231);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("�ڸ����ļ�ʱʹ�ý�����");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 465, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel pathLabel = new JLabel("�ļ���ַ��");
        pathLabel.setBounds(42, 45, 72, 15);
        panel.add(pathLabel);
        
        pathTextField = new JTextField();
        pathTextField.setBounds(124, 42, 197, 21);
        panel.add(pathTextField);
        pathTextField.setColumns(10);
        
        JButton pathButton = new JButton("ѡ���ļ�");     
        pathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_pathButton_actionPerformed(arg0);
            }
        });
        pathButton.setBounds(341, 41, 93, 23);
        panel.add(pathButton);
        
        JLabel saveLabel = new JLabel("���Ƶ�ַ��");
        saveLabel.setBounds(42, 104, 72, 15);
        panel.add(saveLabel);
        
        saveTextField = new JTextField();
        saveTextField.setBounds(124, 101, 197, 21);
        panel.add(saveTextField);
        saveTextField.setColumns(10);        
        JButton saveButton = new JButton("ѡ���ַ");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_saveButton_actionPerformed(arg0);
            }
        });      
        saveButton.setBounds(341, 100, 93, 23);
        panel.add(saveButton);
        
        JButton copyButton = new JButton("ȷ������");
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_copyButton_actionPerformed(arg0);
            }
        });       
        copyButton.setBounds(169, 145, 93, 23);
        panel.add(copyButton);
    }
    // �����ȡֻ����ѡ���ļ��е�ѡ���
    public JFileChooser getChooser() {
        JFileChooser fd = new JFileChooser();
        String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(windows);
        } catch (Exception e) {
            e.printStackTrace();
        }// ���ü�����
        SwingUtilities.updateComponentTreeUI(fd);// ʹ���õĽ�������Ч
        fd.setFileSelectionMode(fd.DIRECTORIES_ONLY); // ָʾֻ��ʾĿ¼
        fd.showOpenDialog(this);
        return fd;
    }
    //ѡ��Ҫ���Ƶ��ļ���ť�ĵ����¼�
    protected void do_pathButton_actionPerformed(ActionEvent arg0) {
        java.awt.FileDialog fd=new FileDialog(this);
        fd.setVisible(true);
        String filePath = fd.getDirectory()+fd.getFile();
        pathTextField.setText(filePath);
    }
    //ѡ�񱣴��ļ���ַ�İ�ť�����¼�
    protected void do_saveButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fd = getChooser();
        if ((fd.getSelectedFile() != null)
                && (!fd.getSelectedFile().equals(""))) {
            String strPath = fd.getSelectedFile().getPath();
            saveTextField.setText(strPath);
        }
    }
    //ȷ�����ư�ť�����¼�
    protected void do_copyButton_actionPerformed(ActionEvent arg0) {
       Thread thread = new Thread(this);
       thread.start();
    }
   //Ӧ�ö��̼߳���ʵ�ֶ�ȡ����
    @Override
    public void run() {
        ProgressMonitorTest test = new ProgressMonitorTest();
        String path = pathTextField.getText();
        String save = saveTextField.getText();
        test.useProgressMonitor(this,path,save+path.substring(path.lastIndexOf("."),path.length()));
        
    }
}
