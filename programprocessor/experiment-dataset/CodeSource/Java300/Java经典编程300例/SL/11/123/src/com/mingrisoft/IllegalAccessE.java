package com.mingrisoft;

import java.lang.reflect.Field;

public class IllegalAccessE {
    public static void main(String[] args) {
        Class<?> clazz = String.class;						//��ô���String��������
        Field[] fields = clazz.getDeclaredFields();				//���String���������
        for (Field field : fields) {							//����������
            if (field.getName().equals("hash")) {				//������������hash
                try {
                    System.out.println(field.getInt("hash"));	// ���hash��ֵ
                } catch (IllegalArgumentException e) {		//����IllegalArgumentException�쳣
                    e.printStackTrace();
                } catch (IllegalAccessException e) {			//����IllegalAccessException�쳣
                    e.printStackTrace();
                }
            }
        }
    }
}

