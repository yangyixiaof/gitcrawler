package com.mingrisoft.exceptions;

public class ExceptionTest {
    public static void main(String[] args) {
        Object array[] = new String[3]; // ����һ������Ϊ3��Object���͵�����
        array[0] = new Integer(1); // ������ĵ�һ��Ԫ�ظ�ֵΪ��������1
        System.out.println(array[0]); // �������ĵ�һ��Ԫ��
    }
}
