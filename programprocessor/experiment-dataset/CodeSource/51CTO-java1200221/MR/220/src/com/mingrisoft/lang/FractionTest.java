package com.mingrisoft.lang;

import org.apache.commons.lang.math.Fraction;

public class FractionTest {
    public static void main(String[] args) {
        Fraction fraction1 = Fraction.getFraction(1, 3);// ����С��1/3
        Fraction fraction2 = Fraction.getFraction(1, 5);// ����С��1/5
        Fraction addition = fraction1.add(fraction2);// ����1/3 + 1/5
        System.out.println("1/3 + 1/5 = " + addition);
        Fraction subtraction = fraction1.subtract(fraction2);// ����1/3 - 1/5
        System.out.println("1/3 - 1/5 = " + subtraction);
        Fraction multiplication = fraction1.multiplyBy(fraction2);// ����1/3 * 1/5
        System.out.println("1/3 * 1/5 = " + multiplication);
        Fraction division = fraction1.divideBy(fraction2);// ����1/3 / 1/5
        System.out.println("1/3 / 1/5 = " + division);
        Fraction invert = fraction1.invert();// ����1/3�ĵ���
        System.out.println("1/3�ĵ����ǣ�" + invert);
        Fraction pow = fraction1.pow(2); // ����1/3��ƽ��
        System.out.println("1/3��ƽ���ǣ�" + pow);
    }
}
