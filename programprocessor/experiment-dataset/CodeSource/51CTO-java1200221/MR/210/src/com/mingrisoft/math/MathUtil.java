package com.mingrisoft.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
    public static void main(String[] args) {
        BigDecimal number1 = new BigDecimal(1.2345); // �����߾��ȸ�����number1
        BigDecimal number2 = new BigDecimal(5.4321); // �����߾��ȸ�����number2
        BigDecimal addition = number1.add(number2); // ����number1��number2
        BigDecimal subtraction = number1.subtract(number2); // ����number1��number2
        BigDecimal multiplication = number1.multiply(number2); // ����number1��number2
        // ����������ķ�ʽ��ø߾��ȳ�������Ľ��
        BigDecimal division = number1.divide(number2, RoundingMode.HALF_UP);
        System.out.println("�߾��ȸ�����number1��" + number1);
        System.out.println("�߾��ȸ�����number2��" + number2);
        System.out.println("�߾��ȸ������ӷ���" + addition);
        System.out.println("�߾��ȸ�����������" + subtraction);
        System.out.println("�߾��ȸ������˷���" + multiplication);
        System.out.println("�߾��ȸ�����������" + division);
    }
}
