package com.mingrisoft.exception;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] array = new int[5];// ���峤��Ϊ5������
        Arrays.fill(array, 5);// �������е�Ԫ�ظ�ֵΪ5
        for (int i = 4; i > -1; i--) {// ������������
            if (i == 0) {// �����0
                throw new DivideZeroException("�����쳣");// ���������׳����쳣��Ϣ�Ĺ��췽��
            }// ������ǳ����������
            System.out.println("array[" + i + "] / " + i + " = " + array[i] / i);
        }
    }
}
