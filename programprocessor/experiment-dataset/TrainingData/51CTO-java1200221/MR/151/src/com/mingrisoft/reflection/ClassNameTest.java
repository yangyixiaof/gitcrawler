package com.mingrisoft.reflection;

import java.util.Date;

public class ClassNameTest {
    public static void main(String[] args) {
        String dateName = new Date().getClass().getName();// ���������������
        System.out.println("�������������͵����ƣ�" + dateName);// ���������������
        String byteName = byte.class.getName();// ���ԭʼ��������
        System.out.println("�������͵����ƣ�" + byteName);// ���ԭʼ��������
        String oneDimensionArray = new Date[4].getClass().getName();// ���һά������������
        System.out.println("һά�����������飺" + oneDimensionArray);// ���һά����������������
        String twoDimensionArray = new int[4][4].getClass().getName();// ��ö�άԭʼ����
        System.out.println("��ά�����������飺" + twoDimensionArray);// �����ά����������������
    }
}
