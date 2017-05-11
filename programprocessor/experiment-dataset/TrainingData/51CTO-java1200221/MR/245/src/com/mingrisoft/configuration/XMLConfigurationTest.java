package com.mingrisoft.configuration;

import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class XMLConfigurationTest {
    public static void main(String[] args) throws ConfigurationException {
        URL resource = new XMLConfigurationTest().getClass().getResource("Book.xml");
        XMLConfiguration config = new XMLConfiguration(resource);
        String bookName = config.getString("Javaͼ��.����");// �������
        String author = config.getString("Javaͼ��.����"); // �������
        String press = config.getString("Javaͼ��.������"); // ��ó�����
        String ISBN = config.getString("Javaͼ��.ISBN"); // ���ISBN
        double price = config.getDouble("Javaͼ��.�۸�"); // ��ü۸�
        int pages = config.getInt("Javaͼ��.ҳ��");// ���ҳ��
        String time = config.getString("Javaͼ��.����ʱ��");// ��ó���ʱ��
        System.out.println("ͼ����Ϣ");
        System.out.println("������" + bookName);
        System.out.println("���ߣ�" + author);
        System.out.println("�����磺" + press);
        System.out.println("ISBN��" + ISBN);
        System.out.println("�۸�" + price + "Ԫ");
        System.out.println("ҳ����" + pages);
        System.out.println("����ʱ�䣺" + time);
    }
}
