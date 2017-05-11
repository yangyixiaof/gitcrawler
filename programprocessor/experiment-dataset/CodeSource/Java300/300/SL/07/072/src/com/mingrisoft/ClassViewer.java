package com.mingrisoft;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassViewer {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.util.ArrayList");
        System.out.println("��ı�׼���ƣ�" + clazz.getCanonicalName());
        Constructor<?>[] constructors = clazz.getConstructors();// ��ø����������й��췽��
        System.out.println("��Ĺ��췽����");
        if (constructors.length != 0) {
            for (Constructor<?> constructor : constructors) {
                System.out.println("\t" + constructor);// ������췽��
            }
        } else {
            System.out.println("\t��");
        }
        Field[] fields = clazz.getDeclaredFields();// ��ø����������зǼ̳���
        System.out.println("��ķǼ̳��������");
        if (fields.length != 0) {
            for (Field field : fields) {
                System.out.println("\t" + field);// ����Ǽ̳���
            }
        } else {
            System.out.println("\t��");
        }
        Method[] methods = clazz.getDeclaredMethods();// ��ø����������зǼ̳з���
        System.out.println("��ķǼ̳з�����");
        if (methods.length != 0) {
            for (Method method : methods) {
                System.out.println(method);// ����Ǽ̳з���
            }
        } else {
            System.out.println("\t��");
        }
    }
}
