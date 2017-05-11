package com.mingrisoft.system;

public class Timing {
    public static double round(double value) {
        return Math.round(value * 10.0) / 10.0;// ����Math���round�������������������
    }
    
    public static String getElapsedText(long elapsedMillis) {
        if (elapsedMillis < 60000) {
            double unit = round(elapsedMillis / 1000.0);// ��ʱ��ת������
            return unit + "��";// ��ת�����ʱ������ӵ�λ
        } else if (elapsedMillis < 60000 * 60) {
            double unit = round(elapsedMillis / 60000.0);// ��ʱ��ת���ɷ�
            return unit + "��";// ��ת�����ʱ������ӵ�λ
        } else if (elapsedMillis < 60000 * 60 * 24) {
            double unit = round(elapsedMillis / (60000.0 * 60));// ��ʱ��ת����ʱ
            return unit + "ʱ";// ��ת�����ʱ������ӵ�λ
        } else {
            double unit = round(elapsedMillis / (60000.0 * 60 * 24));// ��ʱ��ת������
            return unit + "��";// ��ת�����ʱ������ӵ�λ
        }
    }
    
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println("����ʼ����ʱ�䣺" + begin);
        for (int i = 0; i < 1000000000; i++) {
            Math.random();
        }
        long end = System.currentTimeMillis();
        System.out.println("�����������ʱ�䣺" + end);
        System.out.println("��������ʱ�䣺" + getElapsedText(end - begin));
    }
}
