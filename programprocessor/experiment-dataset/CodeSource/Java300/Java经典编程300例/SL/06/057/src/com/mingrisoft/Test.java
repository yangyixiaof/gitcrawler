package com.mingrisoft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
    public static void main(String[] args) {
        System.out.println("���л�֮ǰ��");
        Address address = new Address("�й�", "����", "����");// ����address����
        Employee employee1 = new Employee("��XX", 30, address);// ����employee1����
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);// ���employee1����
        System.out.println("���л�֮��");
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Employee employee2 = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("employee.dat"));
            out.writeObject(employee1);// ������д�뵽�����ļ���
            in = new ObjectInputStream(new FileInputStream("employee.dat"));
            employee2 = (Employee) in.readObject();// �ӱ����ļ���ȡ����
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();// �ر�������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();// �ر������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (employee2 != null) {
            employee2.getAddress().setState("�й�"); // �޸�Ա����ַ
            employee2.getAddress().setProvince("�Ĵ�"); // �޸�Ա����ַ
            employee2.getAddress().setCity("�ɶ�"); // �޸�Ա����ַ
            employee2.setName("��XX"); // �޸�Ա������
            employee2.setAge(24);// �޸�Ա������
            System.out.println("Ա��1����Ϣ��");
            System.out.println(employee1);// ���employee1����
            System.out.println("Ա��2����Ϣ��");
            System.out.println(employee2);// ���employee2����
        }

    }

}
