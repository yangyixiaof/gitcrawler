package com.mingrisoft;

import java.util.Scanner;

public class CelsiusConverter {
    public double getFahrenheit(double celsius) {
        double fahrenheit = 1.8 * celsius + 32;// ���㻪���¶�
        return fahrenheit;// ���ػ����¶�
    }

    public static void main(String[] args) {
        System.out.println("������Ҫת�����¶ȣ���λ�����϶ȣ�");
        Scanner in = new Scanner(System.in);// ��ÿ���̨����
        double celsius = in.nextDouble();// ����û�����������¶�
        CelsiusConverter converter = new CelsiusConverter(); // ������Ķ���
        double fahrenheit = converter.getFahrenheit(celsius); // ת���¶�Ϊ���϶�
        System.out.println("ת����ɵ��¶ȣ���λ�����϶ȣ���" + fahrenheit);// ���ת�����
    }
}
