package com.lzw.ip;

import java.awt.Component;
import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.swtdesigner.FocusTraversalOnArray;

public class IpField extends JPanel {
    private CText textField;
    private CText textField_1;
    private CText textField_2;
    private CText textField_3;
    
    /**
     * Create the panel.
     */
    public IpField() {
        setPreferredSize(new Dimension(141, 25));// ���ÿؼ���ʼ��ѡ��С
        setBorder(UIManager.getBorder("TextField.border"));// �����ı���Ĭ�ϵı߿�
        setBackground(UIManager.getColor("TextField.background"));// �����ı���Ĭ�ϵı���ɫ
        setSize(200, 25);// ��ʼ��С
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));// ���ò��ֹ�����
        textField = new CText();// �����Զ����ı���
        add(textField);// ����ı������
        JLabel label = new JLabel(".");// ����IP�ָ����ı�ǩ�ؼ�
        add(label);
        textField_1 = new CText();// �����Զ����ı���
        add(textField_1);// ����ı������
        JLabel label_3 = new JLabel(".");// ����IP�ָ����ı�ǩ�ؼ�
        add(label_3);
        textField_2 = new CText();// �����Զ����ı���
        add(textField_2);
        JLabel label_2 = new JLabel(".");// ����IP�ָ����ı�ǩ�ؼ�
        add(label_2);
        textField_3 = new CText();// �����Զ����ı���
        add(textField_3);// ����ı������
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]
            { textField, textField_1, textField_2, textField_3 }));
    }
    
    public String getIpString() {// ��д��ȡIP�ַ���ֵ�ķ���
        String ipstr = textField + "." + textField_1 + "." + textField_2 + "."
                + textField_3;// ��4���ı����ֵ����ΪIP��ַ�ַ���
        return ipstr;
    }
    
    public InetAddress getIpAddress() {// ��д��ȡIP����ķ���
        InetAddress ia = null;// ����һ���յ�IP��ַ����
        try {
            ia = InetAddress.getByName(getIpString());// ���ַ���ת��ΪIP��ַ����
        } catch (UnknownHostException e) {
            e.printStackTrace();// �����쳣
        }
        return ia;// ���ص�ַ����
    }
}
