package com.mingrisoft;

import java.awt.Color;

public class Test {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Java", 12, 21, Color.BLACK); // ����è��1��
        Cat cat2 = new Cat("C++", 12, 21, Color.WHITE); // ����è��2��
        Cat cat3 = new Cat("Java", 12, 21, Color.BLACK); // ����è��3��
        System.out.println("è��1�ţ�" + cat1);// ���è��1��
        System.out.println("è��2�ţ�" + cat2);// ���è��2��
        System.out.println("è��3�ţ�" + cat3);// ���è��3��
    }

}
