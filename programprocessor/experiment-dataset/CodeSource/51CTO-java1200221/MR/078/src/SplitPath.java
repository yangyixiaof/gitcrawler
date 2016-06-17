import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SplitPath extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SplitPath frame = new SplitPath();
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
    public SplitPath() {
        setTitle("\u4ECE\u5B57\u7B26\u4E32\u4E2D\u5206\u79BB\u6587\u4EF6\u8DEF\u5F84\u3001\u6587\u4EF6\u540D\u53CA\u6269\u5C55\u540D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 408, 252);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(5, 5));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(10, 0));
        
        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);
        
        JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(button, BorderLayout.EAST);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(null, "\u6587\u4EF6\u4FE1\u606F",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setOpaque(false);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        int option = chooser.showOpenDialog(this);// ��ʾ�ļ��򿪶Ի���
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();// ��ȡ�û�ѡ����ļ�
            String path = file.getAbsolutePath();// ��ȡ�ļ�����·��
            textField.setText(path);// ����·����Ϣ���ı���
            int splitIndex = path.lastIndexOf("\\");// �ļ��ָ�������
            int typeIndex = path.lastIndexOf(".");// �ļ����ͷָ������
            if (typeIndex < 0)
                typeIndex = path.length();
            String filePath = path.substring(0, splitIndex);// ��ȡ·��
            String fileName = path.substring(splitIndex + 1, typeIndex);// ��ȡ�ļ���
            String extName = path.substring(typeIndex);// ��ȡ��չ��
            textArea.setText("");// ����ı���
            textArea.append("�ļ����ƣ�" + fileName + "\n");// ����ļ�����Ϣ���ı���
            textArea.append("��չ���ƣ�" + extName + "\n");// �����չ����Ϣ���ı���
            textArea.append("�ļ�·����" + filePath + "\n");// ����ļ�·����Ϣ���ı���
        }
    }
}
