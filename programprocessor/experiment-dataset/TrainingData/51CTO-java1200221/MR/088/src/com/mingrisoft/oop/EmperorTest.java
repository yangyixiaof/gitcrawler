package com.mingrisoft.oop;

public class EmperorTest {
    public static void main(String[] args) {
        System.out.println("�����ʵ�1����");
        Emperor emperor1 = Emperor.getInstance();
        emperor1.getName();
        System.out.println("�����ʵ�2����");
        Emperor emperor2 = Emperor.getInstance();
        emperor2.getName();
        System.out.println("�����ʵ�3����");
        Emperor emperor3 = Emperor.getInstance();
        emperor3.getName();
    }
}
