package com.mingrisoft;

import java.util.Arrays;

public class ArrayExceptionTest {
    public static void main(String[] args) {
        int[] array = new int[5]; // ����һ������Ϊ5����������
        Arrays.fill(array, 8); // ������������������Ԫ�ظ�ֵΪ8
        for (int i = 0; i < 6; i++) {// ���������������Ԫ��
            System.out.println("array[" + i + "] = " + array[i]);
        }
    }
}
