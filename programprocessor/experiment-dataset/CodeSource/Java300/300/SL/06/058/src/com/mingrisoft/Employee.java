package com.mingrisoft;

import java.io.Serializable;

public class Employee implements Cloneable, Serializable {
    private static final long serialVersionUID = 5022956767440380940L;
    private String name; // ��ʾԱ��������
    private int age; // ��ʾԱ��������

    public Employee(String name, int age) {// ���ù��췽����ʼ��������
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {// ��дtoString()����
        StringBuilder sb = new StringBuilder();
        sb.append("������" + name + ", ");
        sb.append("���䣺" + age + "\n");
        return sb.toString();
    }

    @Override
    protected Employee clone() {// ʹ�ø����clone()����ʵ�����¡
        Employee employee = null;
        try {
            employee = (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
