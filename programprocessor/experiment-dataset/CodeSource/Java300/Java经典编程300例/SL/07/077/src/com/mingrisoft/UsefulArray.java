package com.mingrisoft;

import java.lang.reflect.Array;
import java.util.Arrays;

public class UsefulArray {
    public static Object increaseArray(Object array) {
        Class<?> clazz = array.getClass();// ��ô��������Class����
        if (clazz.isArray()) {//���������һ������
            Class<?> componentType = clazz.getComponentType();//�������Ԫ�ص�����
            int length = Array.getLength(array);//������������ĳ���
            Object newArray = Array.newInstance(componentType, length + 5);//�½�����
            System.arraycopy(array, 0, newArray, 0, length);//����ԭ�������е���������
            return newArray;//�����½�����
        }
        return null;//�������Ĳ�������ͷ��ؿ�
    }
    public static void main(String[] args) {
        int[] intArray = new int[10];
        System.out.println("��������ԭʼ�����ǣ�" + intArray.length);
        Arrays.fill(intArray, 8);//�������е�Ԫ��ȫ����ֵΪ8
        System.out.println("������������ݣ�");
        System.out.println(Arrays.toString(intArray));
        int[] newIntArray = (int[]) increaseArray(intArray);//��������ĳ���
        System.out.println("����������չ�󳤶��ǣ�" + newIntArray.length);
        System.out.println("������������ݣ�");
        System.out.println(Arrays.toString(newIntArray));
    }
}
