package com.mingrisoft;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        String[] titles = { "��Java�����ŵ���ͨ����2�棩��", "��Java��̴ʵ䡷", "����ƵѧJava��" };// ������������
        for (int i = 0; i < 5; i++) {
            new Book(titles[new Random().nextInt(3)]);// �����������鴴��Book����
        }
        System.out.println("�ܼ�������" + Book.getCounter() + "��ͼ�飡");// �����������ĸ���
    }

}
