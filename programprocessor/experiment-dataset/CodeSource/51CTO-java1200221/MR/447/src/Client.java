

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame implements Runnable {
	private JPanel jpanel = new JPanel(); // ����������
	private JLabel nameLabel = new JLabel("������"); // ��ǩ����
	private JTextField nameField = new JTextField(); // �ı������
	private JTextArea msgArea = new JTextArea(); // �ı������
	private JTextField sendField = new JTextField();
	private JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
	private BufferedReader reader; // ����BufferedReader�����
	private PrintWriter writer;
	private Socket socket; // �����׽��ֶ���
	public Client(String title) {
		super(title);
		this.setSize(360, 340); // ���崰���С
		this.add(jpanel);
		jpanel.setLayout(null); // ���岼��
		msgArea.setEditable(false); // msgView����Ϊ���ɱ���״̬
		jpanel.add(nameLabel); // ����ǩ��ӵ������
		nameLabel.setBounds(10, 10, 60, 20); // ���ò���
		jpanel.add(nameField);
		nameField.setBounds(60, 10, 270, 21);
		jpanel.add(sendField);
		sendField.setBounds(10, 270, 320, 21);
		msgArea.setColumns(20);
		msgArea.setRows(5);
		jScrollPane1.setViewportView(msgArea);
		jpanel.add(jScrollPane1);
		jScrollPane1.setBounds(10, 40, 320, 220);
		sendField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writer.println(nameField.getText() + ":" + sendField.getText()); // ���û������û�������Ϣд������
				sendField.setText(""); // �������ı������������
			}
		});
	}
	@Override
	public void run() {
		while (true) {
			try {
				msgArea.append(reader.readLine() + "\n"); // ���ı����н���ȡ���ݷ�����ʾ
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	void getSocket() { // �����׽��ַ���
		msgArea.append("���������������");
		try {
			socket = new Socket("127.0.0.1", 7777); // �����ͻ����׽��ֶ���
			msgArea.append("����׼�����"); // �ı�������Ϣ
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream())); // ʵ����BufferedReader����
			writer = new PrintWriter(socket.getOutputStream(), true); // ʵ����PrintWriter����
			new Thread(this).start(); // �����߳�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Client client = new Client("����������");
		client.setVisible(true); // ���ô���Ϊ�ɼ�
		client.getSocket(); // ���ô����׽��ַ���
	}
}
