package com.mingrisoft;

public class Book {
    private static int counter = 0;// ����һ��������

    public Book(String title) {
        System.out.println("�۳�ͼ�飺" + title);// �������
        counter++;// ��������һ
    }

    public static int getCounter() {// ��ü������Ľ��
        return counter;
    }
}
