package npanel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class NewPanel extends JPanel {
    private JTextField textField;// �����ı���
    public NewPanel() {
        super();// ���ó���Ĺ��췽��
        setLayout(null);// �������Ϊ���Բ���
        setBounds(100, 100, 254, 167);// ��������λ�úʹ�С
        final JButton button = new JButton();// ������ť
        button.setText("��ťһ");// ָ����ť����
        button.setBounds(38, 86, 73, 28);// ָ����ť������е���ʾλ�úʹ�С
        add(button);// ����ť��ӵ������
        final JButton button_1 = new JButton();// ������ť
        button_1.setText("��ť��");// ָ����ť����
        button_1.setBounds(140, 86, 73, 28);// ָ����ť������е���ʾλ�úʹ�С
        add(button_1);// ����ť��ӵ������
        textField = new JTextField();// �����ı���
        textField.setText("����һ���ı���ؼ�");// ָ���ı���ı���
        textField.setBounds(38, 35, 175, 22);// �����ı������ʾλ�úʹ�С
        add(textField);// ���ı�����ӵ������
    }
}
