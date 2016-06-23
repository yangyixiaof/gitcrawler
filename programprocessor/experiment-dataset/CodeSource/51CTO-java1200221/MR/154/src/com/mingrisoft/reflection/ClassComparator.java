package com.mingrisoft.reflection;

import java.util.Comparator;

public class ClassComparator implements Comparator<Class<?>> {
    @Override
    // ͨ��ʵ��Comparator�ӿ���ʵ�ֱȽϹ���
    public int compare(Class<?> clazz1, Class<?> clazz2) {
        if (clazz1.equals(clazz2)) {// ��������������ͬ�򷵻�0
            return 0;
        }
        if (clazz1.isAssignableFrom(clazz2)) {
            return -1; // ���clazz1����ʾ������clazz2����ʾ����ĸ����򷵻�-1
        }
        if (clazz2.isAssignableFrom(clazz1)) {
            return 1; // ���clazz1����ʾ������clazz2����ʾ����������򷵻�1
        }
        throw new IllegalArgumentException("������֮��û�й�ϵ");// ��������׳��쳣
    }
}