import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


public class ComminuteFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField sourceTextField;
    private JTextField sizeTextField;
   
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ComminuteFrame frame = new ComminuteFrame();
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
    public ComminuteFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 430, 211);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("�ļ��ָ�");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 414, 181);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel messagelabel = new JLabel("Դ�ļ���");
        messagelabel.setBounds(47, 41, 54, 20);
        panel.add(messagelabel);
        
        sourceTextField = new JTextField();
        sourceTextField.setBounds(105, 41, 178, 21);
        panel.add(sourceTextField);
        sourceTextField.setColumns(10);
        
        JButton sourceButton = new JButton("ѡ��");
        sourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_sourceButton_actionPerformed(arg0);
            }
        });
        sourceButton.setBounds(303, 40, 67, 23);
        panel.add(sourceButton);
        
        JLabel sizelabel = new JLabel("�ָ��С��");
        sizelabel.setBounds(34, 86, 67, 15);
        panel.add(sizelabel);
        
        sizeTextField = new JTextField();
        sizeTextField.setBounds(105, 83, 178, 21);
        panel.add(sizeTextField);
        sizeTextField.setColumns(10);
        sizeTextField.addKeyListener(new KeyAdapter() {         
            public void keyTyped(KeyEvent event) {  //ĳ������ʱ���õķ���
                char ch = event.getKeyChar();       //��ȡ�û�������ַ�             
                if((ch<'0'||ch>'9')){  //����û��������Ϣ��Ϊ���ֻ�С��
                    event.consume();                //�������û�����
                }
                
            }
        });

        
        JLabel lblM = new JLabel("M");
        lblM.setBounds(313, 86, 44, 15);
        panel.add(lblM);
        
        JButton cominButton = new JButton("�ָ�");
        cominButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_cominButton_actionPerformed(arg0);
            }
        });
        cominButton.setBounds(101, 138, 93, 23);
        panel.add(cominButton);
        
        JButton close = new JButton("�˳�");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_close_actionPerformed(arg0);
            }
        });
        close.setBounds(229, 138, 93, 23);
        panel.add(close);
    }
    protected void do_sourceButton_actionPerformed(ActionEvent arg0) {
        java.awt.FileDialog fd=new FileDialog(this);
        fd.setVisible(true);
        String path = fd.getDirectory()+fd.getFile();
        if(!path.equals("") && !(path == null)){
            sourceTextField.setText(path);
        }    
    }
    protected void do_cominButton_actionPerformed(ActionEvent arg0) {
        ComminuteUtil util = new ComminuteUtil();
        String path = sourceTextField.getText();
        int size = Integer.parseInt(sizeTextField.getText());
        String subPath = path.substring(0,path.lastIndexOf("\\"));
        util.fenGe(new File(path), new File(subPath),size);
        JOptionPane.showMessageDialog(getContentPane(),
                "�ļ��ָ�ɹ���", 
                "��Ϣ��ʾ��", JOptionPane.PLAIN_MESSAGE);
    }
    protected void do_close_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
