package com.mingrisoft.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StringUtils {
    @SuppressWarnings("unchecked")
    public String toString(Object object) {
        Class clazz = object.getClass();// ��ô�������Class����
        StringBuilder sb = new StringBuilder(); // ����StringBuilder�������ַ���
        Package packageName = clazz.getPackage(); // ��������ڵİ�
        sb.append("������" + packageName.getName() + "\t");// ��������ڵİ�
        String className = clazz.getSimpleName(); // �����ļ�����
        sb.append("������" + className + "\n"); // �����ļ�����
        sb.append("�������췽����\n");
        // ������д����췽����Constructor����
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String modifier = Modifier.toString(constructor.getModifiers());// ��÷������η�
            if (modifier.contains("public")) {// �鿴���η��Ƿ��С�public��
                sb.append(constructor.toGenericString() + "\n");
            }
        }
        sb.append("������\n");
        Field[] fields = clazz.getDeclaredFields();// ��ô����������Field����
        for (Field field : fields) {
            String modifier = Modifier.toString(field.getModifiers());
            if (modifier.contains("public")) {// �鿴���η��Ƿ��С�public��
                sb.append(field.toGenericString() + "\n");
            }
        }
        sb.append("����������\n");
        Method[] methods = clazz.getDeclaredMethods();// ��ô������з�����Method[]����
        for (Method method : methods) {
            String modifier = Modifier.toString(method.getModifiers());
            if (modifier.contains("public")) {// �鿴���η��Ƿ��С�public��
                sb.append(method.toGenericString() + "\n");
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(new StringUtils().toString(new Object()));
    }
}
