package com.mingrisoft;

import java.lang.reflect.Method;

public class MethodTest {
    public static void main(String[] args) {
        try {
            System.out.println("����Math��ľ�̬����sin()");
            Method sin = Math.class.getDeclaredMethod("sin", Double.TYPE);
            Double sin1 = (Double) sin.invoke(null, new Integer(1));
            System.out.println("1������ֵ�ǣ�" + sin1);
            System.out.println("����String��ķǾ�̬����equals()");
            Method equals = String.class.getDeclaredMethod("equals", Object.class);
            Boolean mrsoft = (Boolean) equals.invoke(new String("���տƼ�"), "���տƼ�");
            System.out.println("�ַ����Ƿ������տƼ���" + mrsoft);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
