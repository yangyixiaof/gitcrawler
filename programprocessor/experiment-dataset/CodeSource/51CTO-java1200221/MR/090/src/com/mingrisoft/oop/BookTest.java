package com.mingrisoft.oop;

import java.util.Random;

public class BookTest {
    public static void main(String[] args) {
        String[] titles = { "��Java�����ŵ���ͨ����2�棩��", "��Java��̴ʵ䡷", "����ƵѧJava��" };
        for (int i = 0; i < 5; i++) {
            new Book(titles[new Random().nextInt(3)]);
        }
        System.out.println("�ܼ�������" + Book.getCounter() + "��ͼ�飡");
    }
}
