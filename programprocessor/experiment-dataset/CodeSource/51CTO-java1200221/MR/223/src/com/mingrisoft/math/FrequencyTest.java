package com.mingrisoft.math;

import java.util.Random;

import org.apache.commons.math.stat.Frequency;

public class FrequencyTest {
    public static void main(String[] args) {
        Frequency frequency = new Frequency();
        for (int i = 0; i < 100; i++) {
            frequency.addValue(new Random().nextInt(10));// ����100��С��10�������
        }
        System.out.println("Ƶ�ȷֲ�ֱ��ͼ");
        for (int i = 0; i < 10; i++) {// ����0~9ÿ����ֵ����ֱ��ͼ
            System.out.print("��ֵ" + i + "��Ƶ�ȣ�");
            for (int j = 0; j < frequency.getCount(i); j++) {// ���벻ͬ���Ǻ�����ʾ��ͬ��Ƶ��
                System.out.print("*");
            }
            System.out.println("\t" + frequency.getCumFreq(i));// ����ۼ�Ƶ��
        }
    }
}
