package com.mingrisoft;
public class ClassNotFoundE {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");// ����MySQL��������
        } catch (ClassNotFoundException e) {// �����쳣
            e.printStackTrace();// ��ӡ��ջ��Ϣ
        }
    }
}
