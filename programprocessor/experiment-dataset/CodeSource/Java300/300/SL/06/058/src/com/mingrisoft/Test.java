package com.mingrisoft;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>();// �����б������
        Employee employee = new Employee("��XX", 25);// ����Employee��Ķ���
        long currentTime = System.currentTimeMillis();// ��õ�ǰϵͳʱ��
        for (int i = 0; i < 100000; i++) {
            employees.add(employee.clone());// ʹ�ÿ�¡��ʽ��ö���
        }
        System.out.println("��¡����ʱ�䣺" + (System.currentTimeMillis() - currentTime) + "����");
        currentTime = System.currentTimeMillis();// ��õ�ǰʱ��

        for (int i = 0; i < 100000; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();// �����ֽ����������
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(baos);// �������������
                out.writeObject(employee);// ������д�뵽�������
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();// �ͷ���Դ
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // ����ֽ��������������
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(bais);// ��������������
                employees.add((Employee) in.readObject());// ��ȡ����
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();// �ͷ���Դ
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("���л�����ʱ�䣺" + (System.currentTimeMillis() - currentTime) + "����");
    }
}
