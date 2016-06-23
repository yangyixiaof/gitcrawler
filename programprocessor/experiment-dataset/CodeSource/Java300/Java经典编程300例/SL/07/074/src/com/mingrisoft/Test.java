package com.mingrisoft;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        Class<?> clazz = student.getClass();// ��ô���student�����Class����
        System.out.println("��ı�׼���ƣ�" + clazz.getCanonicalName());
        try {
            Field id = clazz.getDeclaredField("id");
            System.out.println("����ǰ��id��" + student.getId());
            id.setAccessible(true);
            id.setInt(student, 10);//����IdֵΪ10
            System.out.println("���ú��id��" + student.getId());
            
            Field name = clazz.getDeclaredField("name");
            System.out.println("����ǰ��name��" + student.getName());
            name.setAccessible(true);
            name.set(student, "���տƼ�");//����nameֵΪ���տƼ�
            System.out.println("���ú��name��" + student.getName());
            
            Field male = clazz.getDeclaredField("male");
            System.out.println("����ǰ��male��" + student.isMale());
            male.setAccessible(true);
            male.setBoolean(student, true);//����maleֵΪtrue
            System.out.println("���ú��male��" + student.isMale());
            
            Field account = clazz.getDeclaredField("account");
            System.out.println("����ǰ��account��" + student.getAccount());
            account.setAccessible(true);
            account.setDouble(student, 12.34);//����accountֵΪ12.34
            System.out.println("���ú��account��" + student.getAccount());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
