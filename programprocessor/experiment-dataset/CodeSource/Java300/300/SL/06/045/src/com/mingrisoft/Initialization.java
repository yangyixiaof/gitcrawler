package com.mingrisoft;

public class Initialization {
    private byte b; // �����������ͱ���b
    private short s; // �������������ͱ���s
    private int i; // �����������ͱ���i
    private long l; // �������������ͱ���l
    private float f; // ���������ȸ������ͱ���f
    private double d; // ����˫���ȸ������ͱ���d
    private boolean bl; // �����������ͱ���bl
    private char c; // �����ַ����ͱ���c
    private String string; // �����������ͱ���string

    public static void main(String[] args) {
        Initialization init = new Initialization();
        System.out.println("�������͵ĳ�ʼֵ��" + init.b); // ����������ͱ�����ʼֵ
        System.out.println("���������͵ĳ�ʼֵ��" + init.s); // ������������ͱ�����ʼֵ
        System.out.println("�������͵ĳ�ʼֵ��" + init.i); // ����������ͱ�����ʼֵ
        System.out.println("���������͵ĳ�ʼֵ��" + init.l); // ������������ͱ�����ʼֵ
        System.out.println("�����ȸ������͵ĳ�ʼֵ��" + init.f);// ��������ȸ������ͱ�����ʼֵ
        System.out.println("˫���ȸ������͵ĳ�ʼֵ��" + init.d);// ���˫���ȸ������ͱ�����ʼֵ
        System.out.println("�������͵ĳ�ʼֵ��" + init.bl); // ����������ͱ�����ʼֵ
        System.out.println("�ַ����͵ĳ�ʼֵ��" + init.c); // ����ַ����ͱ�����ʼֵ
        System.out.println("�������͵ĳ�ʼֵ��" + init.string);// ����������ͱ�����ʼֵ
    }
}
