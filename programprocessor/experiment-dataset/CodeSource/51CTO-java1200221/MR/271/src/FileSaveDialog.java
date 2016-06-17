import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class FileSaveDialog extends JFrame {
    
    private JPanel contentPane;
    private JTextArea textArea;
    
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
                    FileSaveDialog frame = new FileSaveDialog();
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
    public FileSaveDialog() {
        setTitle("\u6587\u4EF6\u9009\u62E9\u5BF9\u8BDD\u6846\u6307\u5B9A\u6570\u636E\u5907\u4EFD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 291);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu menu = new JMenu("\u6587\u4EF6");
        menuBar.add(menu);
        
        JMenuItem menuItem = new JMenuItem("\u4FDD\u5B58");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_menuItem_actionPerformed(e);
            }
        });
        menu.add(menuItem);
        
        JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA");
        menuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_menuItem_1_actionPerformed(e);
            }
        });
        menu.add(menuItem_1);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("����", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setTabSize(4);
        scrollPane.setViewportView(textArea);
    }
    
    protected void do_menuItem_actionPerformed(ActionEvent e) {
        String text = textArea.getText();// ��ȡ�û�����
        if (text.isEmpty()) {// ���˿��ı��ı������
            JOptionPane.showMessageDialog(this, "û����Ҫ������ı�");
            return;
        }
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        int option = chooser.showSaveDialog(this);// ���ļ�����Ի���
        if (option == JFileChooser.APPROVE_OPTION) {// �����ļ��������
            File file = chooser.getSelectedFile();// ��ȡ�û�ѡ����ļ�
            try {
                FileOutputStream fout = new FileOutputStream(file);// �������ļ��������
                fout.write(text.getBytes());// ���ı����浽�ļ�
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    protected void do_menuItem_1_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
