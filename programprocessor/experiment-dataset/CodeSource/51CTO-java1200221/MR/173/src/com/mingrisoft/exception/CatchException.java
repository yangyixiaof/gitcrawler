package com.mingrisoft.exception;

public class CatchException {
    public static void main(String[] args) {
        try {// ����try��
            System.out.println("����try��");
            @SuppressWarnings("unused")
            Class<?> clazz = Class.forName("");// �õ�һ���յ�Class����
            System.out.println("�뿪try��");
        } catch (ClassNotFoundException e) {// ����catch��
            System.out.println("����catch��");
            e.printStackTrace();
            System.out.println("�뿪catch��");
        } finally {// ����finally��
            System.out.println("����finally��");
        }
    }
}
