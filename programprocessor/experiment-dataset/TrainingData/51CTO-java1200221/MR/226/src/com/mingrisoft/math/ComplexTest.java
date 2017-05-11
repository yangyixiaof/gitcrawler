package com.mingrisoft.math;

import org.apache.commons.math.complex.Complex;

public class ComplexTest {
    public static void main(String[] args) {
        Complex complex1 = new Complex(1.0, 3.0); // �����ĳ�ʼ��
        System.out.println("����complex1�ǣ�" + getComplex(complex1));
        Complex complex2 = new Complex(2.0, 5.0); // �����ĳ�ʼ��
        System.out.println("����complex2�ǣ�" + getComplex(complex2));
        Complex addition = complex1.add(complex2); // �����ļӷ�����
        System.out.println("�ӷ�����Ľ���ǣ�" + getComplex(addition));
        Complex subtraction = complex1.subtract(complex2); // �����ļ�������
        System.out.println("��������Ľ���ǣ�" + getComplex(subtraction));
        Complex multiplication = complex1.multiply(complex2); // �����ĳ˷�����
        System.out.println("�˷�����Ľ���ǣ�" + getComplex(multiplication));
        Complex division = complex1.divide(complex2); // �����ĳ�������
        System.out.println("��������Ľ���ǣ�" + getComplex(division));
    }
    
    public static String getComplex(Complex complex) {// �Զ�����������ķ���
        return complex.getReal() + "+" + complex.getImaginary() + "i";
    }
}
