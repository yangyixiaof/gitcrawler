package com.mingrisoft;

public class Test {
    public static void main(String[] args) {
        System.out.println("�����ʵ�1����");
        Emperor emperor1 = Emperor.getInstance();// �����ʵ۶���
        emperor1.getName();// ����ʵ۵�����
        System.out.println("�����ʵ�2����");
        Emperor emperor2 = Emperor.getInstance();// �����ʵ۶���
        emperor2.getName();// ����ʵ۵�����
        System.out.println("�����ʵ�3����");
        Emperor emperor3 = Emperor.getInstance();// �����ʵ۶���
        emperor3.getName();// ����ʵ۵�����
    }

}
