package com.mingrisoft.lang;

import java.util.Arrays;

import org.apache.commons.lang.math.IntRange;

public class IntRangeTest {
    public static void main(String[] args) {
        IntRange range = new IntRange(-5, 5);// ����һ����-5��5������
        System.out.println("�����е�ȫ�������ǣ�");
        System.out.println(Arrays.toString(range.toArray()));// ��������е�ȫ������
        System.out.print("0�Ƿ��������У�");
        System.out.println(range.containsInteger(0));// �ж�0�Ƿ���������
        System.out.print("����������ǣ�");
        System.out.println(range.getMaximumInteger());// ������������
        System.out.print("����������ǣ�");
        System.out.println(range.getMinimumInteger());// ������������
        System.out.print("������ַ�����ʾ�ǣ�");
        System.out.println(range.toString());// ����������ѧ��ʾ��ʽ
    }
}
