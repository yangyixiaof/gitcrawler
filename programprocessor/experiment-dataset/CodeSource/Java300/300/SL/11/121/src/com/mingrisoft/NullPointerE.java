package com.mingrisoft;
public class NullPointerE {
    @SuppressWarnings("null")
    public static void main(String[] args) {
        String string = null;// ���ַ�������Ϊnull
        System.out.println(string.toLowerCase());// ���ַ���ת����Сд
    }
}
