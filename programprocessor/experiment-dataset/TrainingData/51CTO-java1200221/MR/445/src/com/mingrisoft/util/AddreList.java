package com.mingrisoft.util;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
public class AddreList extends JFrame implements ActionListener {
	private JTextField phonetextField;			//�����ı������
	private JTextField emailtextField;
	private JTextField nametextField;
	final JPanel panel = new JPanel();			//����������
	JMenu fileMenu;								//����˵�����
	JMenuItem reveal;
	JMenuItem kinescope;
	JPanel jPanel  = new JPanel();
	File file = new File("C://addressList.txt");	//�����ļ�����
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddreList frame = new AddreList();		//�����������
					frame.setVisible(true);			//���ô������״̬
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public AddreList() {				//���췽����ʵ�ִ��岼��
		super();
		setTitle("����ͨѶ¼");			//���ô������
		getContentPane().setLayout(null);		//���ô��岼��
		setBounds(100, 100, 382, 237);			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar bar = new JMenuBar();	
		fileMenu = new JMenu("�ļ�");
		reveal = new JMenuItem("��ʾ");
		kinescope = new JMenuItem("¼��");
		reveal.addActionListener(this);
		kinescope.addActionListener(this);
		this.setJMenuBar(bar);
		bar.add(fileMenu);
		fileMenu.add(reveal);
		fileMenu.add(kinescope);
		
		panel.setLayout(null);
		panel.setBounds(0, 0, 374, 178);
		getContentPane().add(panel);

		final JLabel namelabel = new JLabel();
		namelabel.setBounds(77, 29, 66, 18);
		namelabel.setText("����������");
		panel.add(namelabel);

		nametextField = new JTextField();
		nametextField.setBounds(149, 27, 122, 22);
		panel.add(nametextField);

		final JLabel emaillabel = new JLabel();
		emaillabel.setBounds(77, 66, 66, 18);
		emaillabel.setText("����Email:");
		panel.add(emaillabel);

		emailtextField = new JTextField();
		emailtextField.setBounds(149, 64, 122, 22);
		panel.add(emailtextField);

		final JLabel phonelabel = new JLabel();
		phonelabel.setText("����绰��");
		phonelabel.setBounds(77, 103, 66, 18);
		panel.add(phonelabel);

		phonetextField = new JTextField();
		phonetextField.setBounds(149, 101, 122, 22);
		panel.add(phonetextField);

		final JLabel label = new JLabel();
		label.setText("����¼�룺");
		label.setBounds(77, 137, 66, 18);
		panel.add(label);

		final JButton kinbutton = new JButton();
		kinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				kinbuttonActionPerformed(e);
				
			}
		});
		kinbutton.setText("¼��");
		kinbutton.setBounds(149, 132, 122, 28);
		panel.add(kinbutton);	
	}

private void kinbuttonActionPerformed(java.awt.event.ActionEvent evt) {
	try {
		if(nametextField.getText().equals("")||
				(emailtextField.getText().equals(""))||
				(phonetextField.getText().equals(""))){		//����û�û�н���Ϣ��������
			JOptionPane.showMessageDialog(this, "��������������", "��Ϣ��ʾ��",
					JOptionPane.WARNING_MESSAGE);			//������ʾ��Ϣ
			return;				//�˳�����
		}
		if(!file.exists())		//����ļ�������
			file.createNewFile();		//�½��ļ�
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file,true)));		//����BufferedWriter����
		out.write("������"+nametextField.getText()+"�� ");		//���ļ���д����
		out.write("���䣺"+emailtextField.getText()+"�� ");
		out.write("�绰��"+phonetextField.getText());
		out.newLine();			//�½�һ��
		out.close();			//�ر���
	} catch (Exception e1) {					
		e1.printStackTrace();
	}
}
	@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == reveal){			//����û�����������ʾ�˵���			
	try {
		getContentPane().remove(panel);			
		jPanel.setLayout(null);				//���ô��岼��		
		jPanel.setBounds(0, 0, 374, 178);
		JTextArea jtextarea = new JTextArea(20,10);		//�����ı������
		jtextarea.setBounds(0, 0, 374, 178);	//�����ı�����ʾλ�����С	
		getContentPane().add(jPanel);			//������������
		jPanel.add(jtextarea);					//�����������ı���
		BufferedReader in = new BufferedReader(new FileReader(file));	//����BufferedReader	����
		String name = null;
		int number = 1;
		while((name = in.readLine())!= null){	//ѭ�����ļ��ж�����			
			jtextarea.append("\n"+number+"�� "+name);		//����ȡ������ʾ���ı�����			
			name = new String(name);			
			number++;
		}
		in.close();
		repaint();
	} catch (Exception e1) {		
		e1.printStackTrace();
	}
}
	if(e.getSource() == kinescope){				//����û�����¼��˵���
		getContentPane().remove(jPanel);		//������Ƴ�����
		getContentPane().add(panel);
		repaint();						//�����ػ�
	}
}
}
