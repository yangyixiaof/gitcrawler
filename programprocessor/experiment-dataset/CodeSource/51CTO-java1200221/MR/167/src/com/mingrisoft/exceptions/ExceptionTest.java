package com.mingrisoft.exceptions;

import java.lang.reflect.Field;

public class ExceptionTest {
    public static void main(String[] args) {
        Class<?> clazz = String.class;// ��ô���String��������
        Field[] fields = clazz.getDeclaredFields();// ���String���������
        for (Field field : fields) {// ����������
            if (field.getName().equals("hash")) {// ������������hash
                try {
                    System.out.println(field.getInt("hash"));// ���hash��ֵ
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
