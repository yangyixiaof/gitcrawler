package com.mingrisoft;

public class ExceptionTest {
    public static void main(String[] args) {
        System.out.println("-1.0 / 0 = " + (-1.0 / 0));// ��ʾ����������0
        System.out.println("+1.0 / 0 = " + (+1.0 / 0));// ��ʾ����������0
        try{
        	System.out.println("-1 / 0 = " + (-1 / 0));// ��ʾ��������0
        }catch(Exception e){
        	System.out.println("�׳��쳣��"+e.getMessage());
        }
        System.out.println("+1 / 0 = " + (+1 / 0));// ��ʾ��������0
        System.out.println("���������");
    }
}
