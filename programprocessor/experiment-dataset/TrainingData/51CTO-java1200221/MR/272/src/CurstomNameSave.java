import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;

public class CurstomNameSave extends JFrame {
    
    private JTextArea textArea;
    private JLabel label;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CurstomNameSave frame = new CurstomNameSave();
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
    public CurstomNameSave() {
        setTitle("\u4E3A\u4FDD\u5B58\u5BF9\u8BDD\u6846\u8BBE\u7F6E\u9ED8\u8BA4\u6587\u4EF6\u540D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu menu = new JMenu("\u6587\u4EF6");
        menuBar.add(menu);
        
        JMenuItem menuItem = new JMenuItem("\u65B0\u5EFA\u6587\u6863");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_menuItem_actionPerformed(e);
            }
        });
        menu.add(menuItem);
        
        JMenuItem menuItem_2 = new JMenuItem("\u9000\u51FA");
        menuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_menuItem_2_actionPerformed(e);
            }
        });
        menu.add(menuItem_2);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setTabSize(4);
        ;
        textArea.setEnabled(false);
        scrollPane.setViewportView(textArea);
        
        label = new JLabel("\u65B0\u5EFA\u6587\u6863");
        contentPane.add(label, BorderLayout.NORTH);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JButton button = new JButton("\u4FDD\u5B58");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(button);
    }
    
    protected void do_menuItem_actionPerformed(ActionEvent e) {
        // �����û�����
        String string = JOptionPane.showInputDialog("�������½��ĵ�����");
        if (string == null)
            return;
        label.setText(string);// �ñ�ǩ�ؼ���ʾ�û�������ȶ�����
        textArea.setEnabled(true);// �����ı���ؼ�
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String text = label.getText();// ��ȡ��ǩ�ؼ�������ĵ����
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        File file = new File(text + ".txt");// ���ĵ����ƴ����ļ�����
        chooser.setSelectedFile(file);// �����ļ�ѡ������ѡ���ļ�
        chooser.showSaveDialog(this);// ��ʾ����Ի���
        File selectedFile = chooser.getSelectedFile();
        JOptionPane.showMessageDialog(this, "�ļ�����·����\n" + selectedFile);
    }
    
    protected void do_menuItem_2_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
