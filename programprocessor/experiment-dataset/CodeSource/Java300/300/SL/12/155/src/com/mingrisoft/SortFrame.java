package com.mingrisoft;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class SortFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -5599266804083691574L;
    private JPanel contentPane;
    private JTextField pathTextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SortFrame frame = new SortFrame();
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
    public SortFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 434, 196);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("�ļ��з������");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 418, 158);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel messageLabel = new JLabel("�����ļ��У�");
        messageLabel.setBounds(19, 48, 92, 15);
        panel.add(messageLabel);

        pathTextField = new JTextField();
        pathTextField.setBounds(121, 45, 187, 21);
        panel.add(pathTextField);
        pathTextField.setColumns(10);

        JButton choicBbutton = new JButton("ѡ��");
        choicBbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                do_choicBbutton_actionPerformed(arg0);
            }
        });
        choicBbutton.setBounds(318, 44, 77, 23);
        panel.add(choicBbutton);

        JButton sortButton = new JButton("ȷ������");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                do_sortButton_actionPerformed(arg0);
            }
        });
        sortButton.setBounds(156, 96, 93, 23);
        panel.add(sortButton);
    }

    // �����ȡֻ����ѡ���ļ��е�ѡ���
    public JFileChooser getChooser() {
        JFileChooser fd = new JFileChooser();
        String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(windows);
        } catch (Exception e) {
            e.printStackTrace();
        }// ���ü�����
        SwingUtilities.updateComponentTreeUI(fd);// ʹ���õĽ�������Ч
        fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // ָʾֻ��ʾĿ¼
        fd.showOpenDialog(this);
        return fd;
    }

    protected void do_choicBbutton_actionPerformed(ActionEvent arg0) {
        JFileChooser fd = getChooser();
        if ((fd.getSelectedFile() != null) && (!fd.getSelectedFile().equals(""))) {
            String strPath = fd.getSelectedFile().getPath();
            pathTextField.setText(strPath);
        }

    }

    @SuppressWarnings("rawtypes")
    protected void do_sortButton_actionPerformed(ActionEvent arg0) {
        SortUtil sortUtil = new SortUtil();
        List list = sortUtil.getList(pathTextField.getText()); // ��ȡ�û�ѡ���ļ����������ļ�����
        for (int i = 0; i < list.size(); i++) { // ѭ���������ļ�����
            String strFile = list.get(i).toString();
            int index = strFile.lastIndexOf(".");
            if (index != -1) {
                String strN = strFile.substring(index + 1, strFile.length()); // ���ļ��н��н�ȡ����ȡ�ļ���չ��
                int ind = strFile.lastIndexOf("\\");
                String strFileName = strFile.substring(ind, index);
                sortUtil.createFolder(pathTextField.getText() + "\\" + "����");// ���ô����ļ��з������½��ļ���
                sortUtil.createFolder(pathTextField.getText() + "\\" + "����" + "\\" + strN);
                if (strFile.endsWith(strN)) {
                    // ���ļ����������ļ���������ͬ���ļ����Ƶ���Ӧ���ļ�����
                    sortUtil.copyFile(strFile,
                            pathTextField.getText() + "\\" + "����" + "\\" + strN + "\\" + strFileName + strFile.substring(index, strFile.length()));
                }
            }
        }
        JOptionPane.showMessageDialog(getContentPane(), // �����û����������ʾ��
                "�ļ�����ɹ���", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
    }
}
