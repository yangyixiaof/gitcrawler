package com.mingrisoft.math;

import java.math.BigInteger;

public class MathUtil {
    public static void main(String[] args) {
        BigInteger number1 = new BigInteger("12345"); // �����߾�������number1
        BigInteger number2 = new BigInteger("54321"); // �����߾�������number2
        BigInteger addition = number1.add(number2); // ����number1��number2
        BigInteger subtraction = number1.subtract(number2); // ����number1��number2
        BigInteger multiplication = number1.multiply(number2); // ����number1��number2
        BigInteger division = number1.divide(number2); // ����number1��number2
        System.out.println("�߾�������number1��" + number1);
        System.out.println("�߾�������number2��" + number2);
        System.out.println("�߾��������ӷ���" + addition);
        System.out.println("�߾�������������" + subtraction);
        System.out.println("�߾��������˷���" + multiplication);
        System.out.println("�߾�������������" + division);
    }
}
