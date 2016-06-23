package com.mingrisoft.reflection;

import java.util.Date;

public class ClassTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("��1�ַ�����Object.getClass()");
        Class c1 = new Date().getClass();// ʹ��getClass()��ʽ���Class����
        System.out.println(c1.getName());// �����������
        System.out.println("��2�ַ�����.class�﷨");
        Class c2 = boolean.class;// ʹ��.class�﷨���Class����
        System.out.println(c2.getName());// �����������
        System.out.println("��3�ַ�����Class.forName()");
        Class c3 = Class.forName("java.lang.String");// ʹ��Class.forName()���Class����
        System.out.println(c3.getName());// �����������
        System.out.println("��4�ַ�������װ���TYPE��");
        Class c4 = Double.TYPE;// ʹ�ð�װ����Class����
        System.out.println(c4.getName());// �����������
    }
}
