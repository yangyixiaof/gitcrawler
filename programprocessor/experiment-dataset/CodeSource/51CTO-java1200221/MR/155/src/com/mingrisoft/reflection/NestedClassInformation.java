package com.mingrisoft.reflection;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class NestedClassInformation {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.awt.geom.Point2D");
        Class<?>[] classes = cls.getDeclaredClasses();// ��ô����ڲ����Class������ɵ�����
        for (Class<?> clazz : classes) {// ����Class��������
            System.out.println("��ı�׼���ƣ�" + clazz.getCanonicalName());
            System.out.println("������η���" + Modifier.toString(clazz.getModifiers()));
            Type[] interfaces = clazz.getGenericInterfaces();// ������з��ͽӿ�
            System.out.println("����ʵ�ֵĽӿڣ�");
            if (interfaces.length != 0) {// ������ͽӿڸ�������0�����
                for (Type type : interfaces) {
                    System.out.println("\t" + type);
                }
            } else {
                System.out.println("\t" + "��");
            }
            Type superClass = clazz.getGenericSuperclass();// ���ֱ�Ӹ���
            System.out.print("���ֱ�Ӽ̳��ࣺ");
            if (superClass != null) {// ���ֱ�Ӹ��಻��Object�����
                System.out.println(superClass);
            } else {
                System.out.println("��");
            }
        }
    }
}
