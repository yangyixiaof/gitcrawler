package com.mingrisoft.lang;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

public class ArrayUtilsTest {
    public static void main(String[] args) {
        int[] array0 = new int[5]; // ��������Ϊ5��int��������
        Arrays.fill(array0, 8); // �������е�Ԫ��ȫ����ʼ��Ϊ8
        System.out.println("�����е�Ԫ���ǣ�");
        System.out.println(Arrays.toString(array0)); // ��������е�ȫ��Ԫ��
        System.out.println("��������������Ԫ��10");
        int[] array1 = ArrayUtils.add(array0, 10); // ��������������Ԫ��10
        System.out.println("�����е�Ԫ���ǣ�");
        System.out.println(Arrays.toString(array1)); // ����������е�ȫ��Ԫ��
        System.out.println("������Ŀ�ͷ����Ԫ��10");
        int[] array2 = ArrayUtils.add(array0, 0, 10); // ������Ŀ�ͷ����Ԫ��10
        System.out.println("�����е�Ԫ���ǣ�");
        System.out.println(Arrays.toString(array2)); // ����������е�ȫ��Ԫ��
        System.out.println("�������ɵ���������ϲ�");
        int[] array3 = ArrayUtils.addAll(array1, array2);// �ϲ������ɵ���������
        System.out.println("�����е�Ԫ���ǣ�");
        System.out.println(Arrays.toString(array3)); // ����������е�ȫ��Ԫ��
    }
}
