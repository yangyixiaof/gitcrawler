package com.mingrisoft;
public class ThrowsException {
    public static void throwsException() throws ClassNotFoundException {// �׳��쳣
        Class.forName("com.mysql.jdbc.Driver");
    }
    
    public static void main(String[] args) {
        try {// �����쳣
            ThrowsException.throwsException();// �����׳��쳣�ķ���
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}