package com.mingrisoft;

import java.awt.Color;

public class Test {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Java", 12, 21, Color.BLACK); // ����è��1��
        Cat cat2 = new Cat("C++", 12, 21, Color.WHITE); // ����è��2��
        Cat cat3 = new Cat("Java", 12, 21, Color.BLACK); // ����è��3��
        System.out.println("è��1�ŵĹ�ϣ�룺" + cat1.hashCode());// ���è��1�ŵĹ�ϣ��
        System.out.println("è��2�ŵĹ�ϣ�룺" + cat2.hashCode());// ���è��2�ŵĹ�ϣ��
        System.out.println("è��3�ŵĹ�ϣ�룺" + cat3.hashCode());// ���è��3�ŵĹ�ϣ��
        System.out.println("è��1���Ƿ���è��2����ͬ��" + cat1.equals(cat2));// �Ƚ��Ƿ���ͬ
        System.out.println("è��1���Ƿ���è��3����ͬ��" + cat1.equals(cat3));// �Ƚ��Ƿ���ͬ
    }

}
