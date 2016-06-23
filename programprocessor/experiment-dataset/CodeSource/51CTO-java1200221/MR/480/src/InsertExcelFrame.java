import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class InsertExcelFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField sexTextField;
    private JTextField deptTextField;
    private JTextField jobTextField;
    private JTextField ageTextField;
    private JLabel laborageLabel;
    private JTextField laborageTextField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertExcelFrame frame = new InsertExcelFrame();
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
    public InsertExcelFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 337, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("����Ϣ���뵽Excel��");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 321, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel nameLabel = new JLabel("�� ����");
        nameLabel.setBounds(51, 41, 59, 15);
        panel.add(nameLabel);
        
        nameTextField = new JTextField();
        nameTextField.setBounds(97, 38, 155, 21);
        panel.add(nameTextField);
        nameTextField.setColumns(10);
        
        JLabel sexLabel = new JLabel("�� �e��");
        sexLabel.setBounds(51, 69, 54, 15);
        panel.add(sexLabel);
        
        sexTextField = new JTextField();
        sexTextField.setBounds(97, 66, 155, 21);
        panel.add(sexTextField);
        sexTextField.setColumns(10);
        
        JLabel deptLabel = new JLabel("�� �ţ�");
        deptLabel.setBounds(51, 130, 54, 15);
        panel.add(deptLabel);
        
        deptTextField = new JTextField();
        deptTextField.setBounds(97, 127, 155, 21);
        panel.add(deptTextField);
        deptTextField.setColumns(10);
        
        JLabel jobLabel = new JLabel("ְ λ��");
        jobLabel.setBounds(51, 158, 54, 15);
        panel.add(jobLabel);
        
        jobTextField = new JTextField();
        jobTextField.setBounds(97, 158, 155, 21);
        panel.add(jobTextField);
        jobTextField.setColumns(10);
        
        JLabel ageLabel = new JLabel("�� �䣺");
        ageLabel.setBounds(51, 94, 54, 15);
        panel.add(ageLabel);
        
        ageTextField = new JTextField();
        ageTextField.setBounds(97, 94, 155, 21);
        panel.add(ageTextField);
        ageTextField.setColumns(10);
        
        laborageLabel = new JLabel("�� �ʣ�");
        laborageLabel.setBounds(51, 189, 54, 15);
        panel.add(laborageLabel);
        
        laborageTextField = new JTextField();
        laborageTextField.setBounds(97, 189, 155, 21);
        panel.add(laborageTextField);
        laborageTextField.setColumns(10);
        
        JButton insertButton = new JButton("��ӵ�Excel");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_insertButton_actionPerformed(arg0);
            }
        });
        insertButton.setBounds(97, 220, 114, 23);
        panel.add(insertButton);
    }
    //��ӵ�Exvel��ť�ĵ��������¼�
    protected void do_insertButton_actionPerformed(ActionEvent arg0) {
        String name = nameTextField.getText();
        String sex = sexTextField.getText();
        String age = ageTextField.getText();
        String dept = deptTextField.getText();
        String job = jobTextField.getText();
        String laborage = laborageTextField.getText();
        CreateXL create = new CreateXL();
        File file = new File("c:/temp.xls");
        if(!file.exists()){
            create.createExcel();
        }
        create.insertvalue(name, sex, age, dept,
                job, laborage);
        JOptionPane.showMessageDialog(getContentPane(), 
                "������ӳɹ���", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);

    }
}
