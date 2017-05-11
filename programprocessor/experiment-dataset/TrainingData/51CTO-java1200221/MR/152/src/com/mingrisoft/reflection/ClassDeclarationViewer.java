package com.mingrisoft.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ClassDeclarationViewer {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.util.ArrayList");// ���ArrayList�����
        System.out.println("��ı�׼���ƣ�" + clazz.getCanonicalName());
        System.out.println("������η���" + Modifier.toString(clazz.getModifiers()));
        // �����ķ��Ͳ���
        TypeVariable<?>[] typeVariables = clazz.getTypeParameters();
        System.out.print("��ķ��Ͳ�����");
        if (typeVariables.length != 0) {
            for (TypeVariable<?> typeVariable : typeVariables) {
                System.out.println(typeVariable + "\t");
            }
        } else {
            System.out.println("��");
        }
        // �������ʵ�ֵ����нӿ�
        Type[] interfaces = clazz.getGenericInterfaces();
        System.out.println("����ʵ�ֵĽӿڣ�");
        if (interfaces.length != 0) {
            for (Type type : interfaces) {
                System.out.println("\t" + type);
            }
        } else {
            System.out.println("\t" + "��");
        }
        // ������ֱ�Ӽ̳��࣬����Ǽ̳���Object�򷵻ؿ�
        Type superClass = clazz.getGenericSuperclass();
        System.out.print("���ֱ�Ӽ̳��ࣺ");
        if (superClass != null) {
            System.out.println(superClass);
        } else {
            System.out.println("��");
        }
        // ����������ע����Ϣ����Щע����Ϣ�ǲ����÷����õ�
        Annotation[] annotations = clazz.getAnnotations();
        System.out.print("���ע�⣺");
        if (annotations.length != 0) {
            for (Annotation annotation : annotations) {
                System.out.println("\t" + annotation);
            }
        } else {
            System.out.println("��");
        }
    }
}
