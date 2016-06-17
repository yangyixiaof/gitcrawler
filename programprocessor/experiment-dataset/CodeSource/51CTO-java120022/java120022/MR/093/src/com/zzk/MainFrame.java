package com.zzk;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author ������
 *
 */
public class MainFrame extends JFrame {
    int fruitNumber = 0; // ��ʵ
    Farm farm = new Farm(); // ʵ����Farm��Ķ���
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        setTitle("�����Լ��Ŀ���ũ��");
        setBounds(100, 100, 466, 276);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setImage(SwingResourceManager.getImage(MainFrame.class,
                "/images/plowland.jpg"));
        backgroundPanel.setBounds(0, 0, 458, 242);
        getContentPane().add(backgroundPanel);
        
        final Crop crop = new Crop();
        crop.setBounds(180, 55, 106, 96);
        backgroundPanel.add(crop);
        
        final JButton button = new JButton();
        button.setRolloverIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/����1.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/����.png"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String message = farm.seed(crop, "/images/seed.png"); // ����
                if (!message.equals("")) { // ����ʾ��Ϣ��Ϊ��ʱ
                    JOptionPane.showMessageDialog(null, message); // ������ʾ�Ի���
                }
            }
        });
        button.setBounds(29, 185, 56, 56);
        backgroundPanel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.setContentAreaFilled(false);
        button_1.setBorderPainted(false);
        button_1.setRolloverIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/����1.png"));
        button_1.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/����.png"));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String message = farm.grow(crop, "/images/grow.png"); // ����
                if (!message.equals("")) {
                    JOptionPane.showMessageDialog(null, message); // ������ʾ�Ի���
                }
            }
        });
        backgroundPanel.add(button_1);
        button_1.setBounds(114, 185, 56, 56);
        
        final JButton button_2 = new JButton();
        button_2.setBorderPainted(false);
        button_2.setContentAreaFilled(false);
        button_2.setRolloverIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/����1.png"));
        button_2.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/����.png"));
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String message = farm.bloom(crop, "/images/bloom.png"); // ����
                if (!message.equals("")) {
                    JOptionPane.showMessageDialog(null, message); // ������ʾ�Ի���
                }
            }
        });
        button_2.setBounds(199, 185, 56, 56);
        backgroundPanel.add(button_2);
        
        final JButton button_3 = new JButton();
        button_3.setBorderPainted(false);
        button_3.setContentAreaFilled(false);
        button_3.setRolloverIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/���1.png"));
        button_3.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/���.png"));
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String message = farm.fruit(crop, "/images/fruit.png"); // ���
                if (!message.equals("")) {
                    JOptionPane.showMessageDialog(null, message); // ������ʾ�Ի���
                }
            }
        });
        button_3.setBounds(284, 185, 56, 56);
        backgroundPanel.add(button_3);
        
        final JLabel storage = new JLabel();
        storage.setHorizontalAlignment(SwingConstants.CENTER);
        storage.setText("���Ĳֿ�û���κι�ʵ����첥�Űɣ�");
        storage.setBounds(80, 15, 253, 28);
        backgroundPanel.add(storage);
        
        final JButton button_4 = new JButton();
        button_4.setRolloverIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/�ջ�1.png"));
        button_4.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/images/�ջ�.png"));
        button_4.setContentAreaFilled(false);
        button_4.setBorderPainted(false);
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String message = farm.harvest(crop, ""); // �ջ�
                if (!message.equals("")) {
                    JOptionPane.showMessageDialog(null, message); // ������ʾ�Ի���
                } else {
                    fruitNumber++;
                    storage.setText("���Ĳֿ�������" + fruitNumber + "����ʵ��");
                }
            }
        });
        button_4.setBounds(369, 185, 56, 56);
        backgroundPanel.add(button_4);
        //
    }
    
}
