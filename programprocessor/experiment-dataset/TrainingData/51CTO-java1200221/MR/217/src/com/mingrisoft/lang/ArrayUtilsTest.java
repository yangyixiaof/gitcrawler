package com.mingrisoft.lang;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

public class ArrayUtilsTest {
    public static void main(String[] args) {
        int[] array0 = { 1, 2, 3, 2, 1 };// �ڶ�������ʱʵ�ֳ�ʼ��
        System.out.println("�����е�Ԫ���ǣ�");
        System.out.println(Arrays.toString(array0));// ��������е�ȫ��Ԫ��
        System.out.println("ɾ�����һ��Ԫ��");
        int[] array1 = ArrayUtils.remove(array0, 4);// ɾ������Ϊ4��Ԫ��
        System.out.println("�����е�Ԫ���ǣ�");
        System.out.println(Arrays.toString(array1));// ����������е�ȫ��Ԫ��
        System.out.println("ɾ��Ԫ��2");
        int[] array2 = ArrayUtils.removeElement(array0, 2);// ɾ��Ԫ��2
        System.out.println("�����е�Ԫ���ǣ�");
        System.out.println(Arrays.toString(array2));// ����������е�ȫ��Ԫ��
    }
}
