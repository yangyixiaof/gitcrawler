import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CheckFileType extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    CheckFileType frame = new CheckFileType();
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create the frame.
     */
    public CheckFileType() {
        setTitle("\u5224\u65AD\u6587\u4EF6\u7C7B\u578B");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 143);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u9009\u62E9\u6587\u4EF6\uFF1A");
        label.setBounds(10, 10, 84, 15);
        contentPane.add(label);
        
        textField = new JTextField();
        textField.setBounds(88, 2, 289, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton button = new JButton("\u6D4F\u89C8\u2026\u2026");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(389, 2, 90, 30);
        contentPane.add(button);
        
        textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBounds(83, 37, 396, 60);
        contentPane.add(textArea);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        Scanner scan = new Scanner(getClass()// ��ȡ˵���ļ���ɨ����
                .getResourceAsStream("extName.inf"));
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        boolean searched = false;
        int option = chooser.showOpenDialog(this);// ���ļ�ѡ��Ի���
        if (option == JFileChooser.APPROVE_OPTION) {// �����ȷѡ���ļ�
            File file = chooser.getSelectedFile();// ��ȡ�û�ѡ���ļ�
            textField.setText(file.getName());// ���ļ�����ӵ��ı���
            String name = file.getName();// ��ȡ�ļ���
            while (scan.hasNextLine()) {// ����˵���ļ�
                String line = scan.nextLine();// ��ȡһ��˵����Ϣ
                String[] extInfo = line.split("\t");// �ѵ���˵����Ϣ��ֳ�����
                // �����һ��Ԫ�����ļ���չ�������û�ѡ���ļ����Ա�
                if (name.endsWith(extInfo[0])) {
                    // �ڶ�������Ԫ�����ļ����͵�˵����Ϣ����ӵ��ı���ؼ���
                    textArea.setText(extInfo[1]);
                    searched = true;
                }
            }
            scan.close();// �ر�ɨ����
        }
        if (!searched) {// ���û�ҵ�����ļ����͵�˵��������ʾ�û�
            textArea.setText("��ѡ����ļ�����û����Ӧ��¼���������extName.info�ļ�����Ӹ����͵�������");
        }
    }
}
